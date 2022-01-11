package com.www.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.www.cmn.client.DictFeignClient;
import com.www.yygh.hosp.repository.HospitalRespository;
import com.www.yygh.hosp.service.HospitalService;
import com.www.yygh.model.hosp.Hospital;
import com.www.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author www
 * @date 2022年01月02日 23:56
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRespository hospitalRespository;

    @Autowired
    private DictFeignClient dictFeignClient;


    @Override
    public void save(Map<String, Object> paramMap) {
        //先把参数map集合转换对象 Hospital
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);
        //判断是否存在数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist = hospitalRespository.getHospitalByHoscode(hoscode);
        //如果不存在，进行添加
        if (hospitalExist != null) { //如果存在，进行修改
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospitalRespository.save(hospital);
        } else {//如果不存在，进行添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRespository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRespository.getHospitalByHoscode(hoscode);
    }

    /**
     * 医院列表（条件查询分页）
     *
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    @Override
    public Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        //创建pageable对象
        Pageable pageable = PageRequest.of(page - 1, limit);

        //创建条件匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        //hospitalSetQueryVo转换Hospital对象
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);

        //创建对象
        Example<Hospital> example = Example.of(hospital, matcher);

        //调用方法实现查询
        Page<Hospital> pages = hospitalRespository.findAll(example, pageable);

        //获取查询list集合，遍历进行医院等级封装
        pages.getContent().stream().forEach(item -> {
            this.setHospitalHosType(item);
        });
        return pages;
    }

    //获取查询list集合，遍历进行医院等级封装
    private Hospital setHospitalHosType(Hospital hospital) {
        //根据dictCode和value获取医院等级名称
        String hostypeString = dictFeignClient.getName("Hostype", hospital.getHostype());
        //查询省 市 区
//        String provinceString = dictFeignClient.getName("Province", hospital.getProvinceCode());
//        String cityString = dictFeignClient.getName("Province", hospital.getCityCode());
//        String districtString = dictFeignClient.getName("Province", hospital.getDistrictCode());

        hospital.getParam().put("hostypeString", hostypeString);
//        hospital.getParam().put("provinceString", provinceString);
//        hospital.getParam().put("cityString", cityString);
//        hospital.getParam().put("districtString", districtString);
        return hospital;
    }
}

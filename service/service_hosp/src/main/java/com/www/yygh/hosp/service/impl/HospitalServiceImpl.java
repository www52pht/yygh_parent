package com.www.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.www.yygh.hosp.repository.HospitalRespository;
import com.www.yygh.hosp.service.HospitalService;
import com.www.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author www
 * @date 2022年01月02日 23:56
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRespository hospitalRespository;


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

}

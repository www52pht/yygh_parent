package com.www.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.www.yygh.hosp.mapper.HospitalSetMapper;
import com.www.yygh.hosp.service.HospitalSetService;
import com.www.yygh.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * @author www
 * @version 1.0
 * @create 2021/5/7 14:37
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    //2、根据传递过来医院编码，查询数据库，查询签名
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode", hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }
}

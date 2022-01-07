package com.www.yygh.hosp.service;

import com.www.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @author www
 * @date 2022年01月02日 23:56
 */
public interface HospitalService {
    void save(Map<String, Object> paramMap);

    /**
     * 查询医院
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);
}

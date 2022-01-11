package com.www.yygh.hosp.service;

import com.www.yygh.model.hosp.Hospital;
import com.www.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author www
 * @date 2022年01月02日 23:56
 */
public interface HospitalService {
    /**
     * 上传医院接口
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 查询医院
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);

    /**
     * 医院列表（条件查询分页）
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);
}

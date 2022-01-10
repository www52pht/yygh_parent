package com.www.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.www.yygh.model.hosp.HospitalSet;
/**
 * @author www
 * @version 1.0
 * @create 2021/5/7 14:35
 */
public interface HospitalSetService extends IService<HospitalSet> {


    String getSignKey(String hoscode);
}

package com.www.yygh.hosp.repository;

import com.www.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author www
 * @date 2022年01月02日 23:53
 */
@Repository
public interface HospitalRespository extends MongoRepository<Hospital, String> {

    //判断是否存在数据
    Hospital getHospitalByHoscode(String hoscode);
}

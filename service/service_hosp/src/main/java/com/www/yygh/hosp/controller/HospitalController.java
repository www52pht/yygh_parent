package com.www.yygh.hosp.controller;

import com.www.yygh.common.result.Result;
import com.www.yygh.hosp.service.HospitalService;
import com.www.yygh.model.hosp.Hospital;
import com.www.yygh.vo.hosp.HospitalQueryVo;
import javafx.application.HostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author www
 * @date 2022年01月11日 10:35
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
@CrossOrigin
public class HospitalController {
    //医院列表(条件分页查询)
    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> hospitals = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        return Result.ok(hospitals);
    }
}

package com.www.yygh.hosp.controller;

import com.www.yygh.hosp.service.HospitalSetService;
import com.www.yygh.model.hosp.HospitalSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.application.HostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author www
 * @version 1.0
 * @create 2021/5/7 14:41
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation(value = "获取所有的医院设置")
    @GetMapping("findall")
    public List<HospitalSet> findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return list;
    }

    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public boolean removeHospital(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        return flag;
    }
}

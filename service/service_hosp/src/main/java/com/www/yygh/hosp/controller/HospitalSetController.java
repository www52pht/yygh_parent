package com.www.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.www.yygh.common.result.Result;
import com.www.yygh.common.utils.MD5;
import com.www.yygh.hosp.service.HospitalSetService;
import com.www.yygh.model.hosp.HospitalSet;
import com.www.yygh.vo.hosp.HospitalQueryVo;
import com.www.yygh.vo.hosp.HospitalSetQueryVo;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.application.HostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.Random;

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
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospital(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //条件查询
    @ApiOperation(value = "条件查询")
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        //mp中创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);

        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();

        String hosname = hospitalSetQueryVo.getHosname(); //医院名称
        String hoscode = hospitalSetQueryVo.getHoscode(); //医院编号
        if (!StringUtils.isEmpty(hosname)) {
            wrapper.like("hosname", hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)) {
            wrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }

        //调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);

        //返回结果
        return Result.ok(pageHospitalSet);
    }


    //添加医院设置
    @ApiOperation(value = "添加医院设置")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //设置状态 1 使用 0 不能使用
        hospitalSet.setStatus(1);
        //签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        //调用service
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }


    //根据id获取医院设置
    @ApiOperation(value = "根据id获取医院设置")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }


    //修改医院设置
    @ApiOperation(value = "修改医院设置")
    @PutMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }


    //批量删除医院设置
    @ApiOperation(value = "批量删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }
}

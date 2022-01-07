package com.www.yygh.cmn.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.www.yygh.cmn.service.DictService;
import com.www.yygh.common.result.Result;
import com.www.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ""
 *
 * @author www
 * @version 1.0
 * @date 2021/12/29 0029 1:08
 */
@Api(value = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {
    @Autowired
    private DictService dictService;

    //根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result FindChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

}

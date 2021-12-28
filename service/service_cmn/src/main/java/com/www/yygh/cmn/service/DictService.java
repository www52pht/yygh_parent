package com.www.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.www.yygh.model.cmn.Dict;

import java.util.List;

/**
 * ""
 *
 * @author www
 * @version 1.0
 * @date 2021/12/29 0029 1:05
 */
public interface DictService extends IService<Dict> {
    List<Dict> findChildData(Long id);
}

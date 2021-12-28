package com.www.yygh.cmn.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ""
 *
 * @author www
 * @version 1.0
 * @date 2021/12/29 0029 0:45
 */
@Configuration
@MapperScan({"com.www.yygh.cmn.mapper"})
public class CmnConfig {
    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

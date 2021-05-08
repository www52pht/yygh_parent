package com.www.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author www
 * @version 1.0
 * @create 2021/5/7 10:46
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.www")
public class ServiceHospApplication {
     public static void main(String[] args) {
           SpringApplication.run(ServiceHospApplication.class, args);
      }

}

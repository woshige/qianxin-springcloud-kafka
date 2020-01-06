package com.qianxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.qianxin.mapper")
public class RestFulApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestFulApplication.class, args);
    }
}

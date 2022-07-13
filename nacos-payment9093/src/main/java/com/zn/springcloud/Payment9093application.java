package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/1
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment9093application {
    public static void main(String[] args) {
        SpringApplication.run(Payment9093application.class,args);
    }
}

package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/6/27
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixServer9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixServer9001.class,args);
    }
}

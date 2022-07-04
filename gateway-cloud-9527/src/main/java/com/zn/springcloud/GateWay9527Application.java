package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/6/28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class GateWay9527Application {
    public static void main(String[] args) {
        SpringApplication.run(GateWay9527Application.class, args);
    }
}

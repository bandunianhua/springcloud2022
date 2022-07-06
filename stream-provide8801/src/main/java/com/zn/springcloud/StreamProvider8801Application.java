package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/6
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamProvider8801Application {
    public static void main(String[] args) {
        SpringApplication.run(StreamProvider8801Application.class, args);
    }
}

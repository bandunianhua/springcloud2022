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
@EnableEurekaClient
@SpringBootApplication
public class StreamConsumer8802Application {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumer8802Application.class, args);
    }
}

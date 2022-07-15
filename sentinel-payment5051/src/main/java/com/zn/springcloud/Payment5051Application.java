package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zn
 * @time
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment5051Application {

    public static void main(String[] args) {
        SpringApplication.run(Payment5051Application.class,args);
    }
}

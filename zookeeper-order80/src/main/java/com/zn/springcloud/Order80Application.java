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
public class Order80Application {

    public static void main(String[] args) {

        SpringApplication.run(Order80Application.class,args);
    }
}

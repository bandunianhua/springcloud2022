package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zn
 * @time
 */
@SpringBootApplication
@EnableEurekaClient
public class Payment8081Application {

    public static void main(String[] args) {
        SpringApplication.run(Payment8081Application.class,args);
    }
}

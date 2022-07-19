package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zn
 * @time
 */
@SpringBootApplication
@EnableFeignClients
public class SentinelOrder80Application {

    public static void main(String[] args) {

        SpringApplication.run(SentinelOrder80Application.class,args);
    }
}

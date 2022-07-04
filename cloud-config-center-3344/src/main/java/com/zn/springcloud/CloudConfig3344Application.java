package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/1
 */
@SpringBootApplication
@EnableConfigServer
public class CloudConfig3344Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfig3344Application.class,args);
    }
}

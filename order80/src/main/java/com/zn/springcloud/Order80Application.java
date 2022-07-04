package com.zn.springcloud;

import com.zn.ribbonRule.RibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author zn
 * @time
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name="CLOUD-APPLYMENT-SERVICE",configuration = RibbonRule.class)
public class Order80Application {

    public static void main(String[] args) {

        SpringApplication.run(Order80Application.class,args);
    }
}

package com.zn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zn
 * @time
 */
@SpringBootApplication
//@RibbonClient(name="CLOUD-APPLYMENT-SERVICE",configuration = RibbonRule.class)
public class NacosOrder80Application {

    public static void main(String[] args) {

        SpringApplication.run(NacosOrder80Application.class,args);
    }
}

package com.zn.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/6/22
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}

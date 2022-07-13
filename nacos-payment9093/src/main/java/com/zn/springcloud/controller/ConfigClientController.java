package com.zn.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/1
 */
@RestController
@RefreshScope//动态刷新参数
public class ConfigClientController {
    @Value("${config.info}")
    private String info;

    @GetMapping("/getInfo")
    public String getInfo() {
        return info;
    }
}

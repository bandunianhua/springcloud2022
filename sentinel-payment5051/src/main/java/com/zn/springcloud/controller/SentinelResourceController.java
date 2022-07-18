package com.zn.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zn.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述 @SentinelResource注解测试
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/15
 */
@RestController
@RequestMapping("zn/resource")
public class SentinelResourceController {

    @Value("${server.port}")
    private String port;

    /**
     * @date: 2022/7/15 18:16
     * @user: Administrator
     * @param:
     * @description: 自定义限流
     */
    @GetMapping("/limitBySelf")
    @SentinelResource(value = "limitBySelf", blockHandler = "fallBack")
    public String limitBySelf() {

        return "服务" + port + "：SUC  " + IdUtil.randomUUID();
    }

    /**
     * @date: 2022/7/18 16:19
     * @user: Administrator
     * @param:
     * @description: 自定义降级方法
     */
    public String fallBack(String k1, String k2, BlockException exception) {
        return "服务" + port + "：FAIL  " + IdUtil.randomUUID();
    }

    /**
     * @date: 2022/7/15 18:16
     * @user: Administrator
     * @param:
     * @description: 自定义限流
     */
    @GetMapping("/limitByConsumer")
    @SentinelResource(value = "limitByConsumer",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "fallBack1")
    public String limitByConsumer() {

        return "服务" + port + "：SUC  " + IdUtil.randomUUID();
    }

}

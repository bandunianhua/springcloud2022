package com.zn.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/15
 */
@RestController
@RequestMapping("zn/payment")
public class HostKeyController {

    @Value("${server.port}")
    private String port;


    /**
     *@date: 2022/7/15 18:16
     *@user: Administrator
     *@param:
     *@description: 热点key
     */
    @GetMapping("/hostKey")
    @SentinelResource(value = "hostKey", blockHandler = "fallBack")
    public String hostKey(@RequestParam(value = "k1", required = false) String k1,
                          @RequestParam(value = "k2", required = false) String k2) {

        int a=1/0;
        return "服务" + port + "：SUC" + IdUtil.randomUUID();
    }

    public String fallBack(String k1, String k2, BlockException exception){
        return "服务" + port + "：FAIL" + IdUtil.randomUUID();
    }
}

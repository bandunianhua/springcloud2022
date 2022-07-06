package com.zn.springcloud.controller;

import com.zn.springcloud.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/6
 */
@RestController
@RequestMapping("zn/message")
public class MessageController {

    @Autowired
    private IMessageService IMessageService;

    @GetMapping("/send")
    public String send() {
        return IMessageService.send();
    }
}

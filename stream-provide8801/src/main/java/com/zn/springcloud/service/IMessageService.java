package com.zn.springcloud.service;

import com.zn.springcloud.manager.MessageManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/6
 */
public interface IMessageService {
    public String send();
}

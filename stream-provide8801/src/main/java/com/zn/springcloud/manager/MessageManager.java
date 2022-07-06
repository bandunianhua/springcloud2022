package com.zn.springcloud.manager;

import cn.hutool.core.util.IdUtil;
import com.zn.springcloud.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/6
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageManager implements IMessageService {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String randomUUID = IdUtil.randomUUID();
        output.send(MessageBuilder.withPayload(randomUUID).build());
        log.info("消息已经发送到中间件"+randomUUID);
        return randomUUID;
    }
}

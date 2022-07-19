package com.zn.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.myHandler.GlobalBlockHandler;
import com.zn.springcloud.myHandler.GlobalFallBack;
import com.zn.springcloud.service.IPaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @date: 2022/7/19 12:07
 * @user: Administrator
 * @param:
 * @description: sentinel+openfeign+nacos+ribbon整合
 */
@RestController
@RequestMapping("zn/order")
public class OrderController {

    @Resource
    private IPaymentService iPaymentService;


    /**
     * @date: 2022/7/19 12:07
     * @user: Administrator
     * @param:
     * @description:
     */
    @GetMapping("/query/{id}")
    @SentinelResource(value = "fallback"
            , blockHandlerClass = GlobalBlockHandler.class
            , fallbackClass = GlobalFallBack.class
            , blockHandler = "globalBlockHandler"
            ,fallback = "globalFallback"
            ,exceptionsToIgnore = {IllegalArgumentException.class}//数据异常就不走自定义兜底方法了
    )
    public CommentResult<Payment> query(@PathVariable("id") int id) {
        CommentResult commentResult = iPaymentService.query(id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常.………");
        } else if (commentResult.getDate() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return commentResult;
    }

    @GetMapping("/timeOut")
    @SentinelResource(value = "fallBackTimeOut"
            , blockHandlerClass = GlobalBlockHandler.class
            , fallbackClass = GlobalFallBack.class
            , blockHandler = "timeOut"
            ,fallback = "timeOut"
    )
    public String timeOut() {
        String s = iPaymentService.timeOut();
        throw new IllegalArgumentException("IllegalArgumentException,非法参数异常.………");
    }


}

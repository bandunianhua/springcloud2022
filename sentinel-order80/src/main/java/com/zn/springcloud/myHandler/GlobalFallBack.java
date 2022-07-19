package com.zn.springcloud.myHandler;

import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @描述 全局异常处理
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/19
 */
public class GlobalFallBack {

    /**
     * @date: 2022/7/19 10:28
     * @user: Administrator
     * @param:
     * @description: 运行时异常限流降级配置方法兜底
     */
    public static CommentResult<Payment> globalFallback(@PathVariable int id,
                                                        Throwable e) {
        return new CommentResult<Payment>("501", "运行异常兜底处理"
                , new Payment(id, e.getMessage()));
    }

    public static String timeOut(Throwable e) {
        return "运行异常兜底处理";
    }

}

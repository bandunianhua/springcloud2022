package com.zn.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @描述 全局限流处理
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/19
 */
public class GlobalBlockHandler {

    /**
     * @date: 2022/7/19 10:28
     * @user: Administrator
     * @param:
     * @description: sentinel限流降级配置方法兜底
     */
    public static CommentResult<Payment> globalBlockHandler(@PathVariable int id,
                                                            BlockException e) {
        return new CommentResult("502", "界面配置限流降级兜底处理"
                , new Payment(id, e.getMessage()));
    }

    public String timeOut(BlockException e) {
        return "界面配置限流降级兜底处理";
    }
}

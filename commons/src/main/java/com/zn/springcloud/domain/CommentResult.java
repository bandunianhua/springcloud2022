package com.zn.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResult<T> {

    private String code;
    private String message;
    private T date;

    public CommentResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommentResult(T date) {
        this.code = "500";
        this.message = "内部业务处理失败";
        this.date=date;
    }
}

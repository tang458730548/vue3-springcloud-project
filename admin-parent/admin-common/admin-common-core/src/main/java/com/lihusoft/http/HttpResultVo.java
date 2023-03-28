package com.lihusoft.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述：
 *      http统一返回
 * @author tsc
 * @since 2023/2/9 16:32
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HttpResultVo<T> {

    private int code;

    private String msg;

    private T data;

    public HttpResultVo(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public HttpResultVo(int code, String message) {
        this.code = code;
        this.msg = message;
    }
}

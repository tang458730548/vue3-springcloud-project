package com.lihusoft.http;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/2/9 16:40
 **/
public class HttpResultUtil {

    private static final int HTTP_200 = 200;

    public static <T> HttpResultVo<T> build(T data) {
        return new HttpResultVo<>(HTTP_200, data);
    }
    public static <T> HttpResultVo<T> build(int code , String message) {
        return new HttpResultVo<>(code, message);
    }

    public static <T> HttpResultVo<T> build(int code, T data) {
        return new HttpResultVo<>(code, data);
    }

    public static <T> HttpResultVo<T> build(int code, String msg, T data) {
        return new HttpResultVo<>(code, msg, data);
    }
}

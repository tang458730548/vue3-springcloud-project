package com.lihusoft.util;


import java.io.Serializable;

public class RequestContext implements Serializable {

    private static final ThreadLocal<String> REMOTE_ADDR = new ThreadLocal<>();

    public static String getRemoteAddr() {
        return REMOTE_ADDR.get();
    }

    public static void setRemoteAddr(String remoteAddr) {
        RequestContext.REMOTE_ADDR.set(remoteAddr);
    }

}

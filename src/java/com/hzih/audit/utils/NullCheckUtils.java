package com.hzih.audit.utils;

/**
 * Created by Administrator on 15-7-16.
 */
public class NullCheckUtils {

    public static Object nullCheck(Object o){
        if(o==null){
           return "";
        }
        return o;
    }
}

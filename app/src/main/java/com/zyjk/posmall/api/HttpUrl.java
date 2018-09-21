package com.zyjk.posmall.api;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description：服务器接口定义地址
 *
 * Created by Sword God on 2018/9/6.
 */
public class HttpUrl {

    public static final int MAIN_SERVICE = 1;
    public static final int MAIN_SERVICE2 = 2;

//    public static final String BASICS_SERVICE = "https://api.douban.com/v2/";
    public static final String BASICS_SERVICE = "http://192.168.29.49:8082/ecMall/";
    public static final String BASICS_TWO = "http://192.168.29.49:8082/ecMall/";


    @IntDef({MAIN_SERVICE, MAIN_SERVICE2})
    @Retention(RetentionPolicy.SOURCE)
    public @interface isChekout {

    }
}

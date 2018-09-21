package com.zyjk.posmall.api;

/**
 * Created by Sword God on 2018/8/28.
 */

public class Api {
    public static final String BASE_URL = "http://192.168.29.49:8082/ecMall/";

    public static final String URL_SEND_CODE = BASE_URL + "app.php/index/coupon/center"; //发验证码
    public static final String URL_REGISTER = BASE_URL + "app.php/reg"; //注册
    public static final String URL_LOGIN = BASE_URL + "app.php/login"; //登录
    public static final String URL_HOME = BASE_URL + "main/mainPageLoad" ; //登录
    public static final String URL_MEDLIST = BASE_URL + "main/enquiryOrderInfo"+"?"; //登录
}

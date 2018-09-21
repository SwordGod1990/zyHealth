package com.zyjk.posmall.api;

import com.zyjk.posmall.request.HttpResult;
import com.zyjk.posmall.bean.Subjects;

import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * =======================================
 * 作    者：SwordGod
 * 创建日期：2018/9/16
 * 项目描述：OKHttp
 * 邮    箱：156690858@qq.com
 * =======================================
 */

public interface HttpService2 {

    @FormUrlEncoded
    @POST("接口名")
    Observable<HttpResult<List<Subjects>>> login(String s, String s1, String s2);
}

package com.zyjk.posmall.api;


import com.zyjk.posmall.bean.AllDiscountModel;
import com.zyjk.posmall.request.HttpResult;
import com.zyjk.posmall.bean.Subjects;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description：服务器接口定义Api
 * <p>
 * Created by Sword God on 2018/9/6.
 */

public interface HttpService {
    /**
     * 用户登录
     *
     * @param pageIndex base编码
     * @return obserable
     */
    @FormUrlEncoded
    @POST("movie/top250")
    Observable<HttpResult<List<Subjects>>> login(@Field("pageIndex") String pageIndex, @Field("catalog") String catalog, @Field("pageSize") String pageSize);

    /**
     *
     * @param provinceCode
     * @param page
     * @param CityCode
     * @param areaCode
     * @return
     */
    @FormUrlEncoded
    @POST("main/allDiscountList")
    Observable<HttpResult<List<AllDiscountModel.DataBean>>> AllDiscountList(@Field("provinceCode") String provinceCode, @Field("page") String page, @Field("CityCode") String CityCode, @Field("areaCode") String areaCode);
}

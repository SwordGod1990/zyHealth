package com.zyjk.posmall.request;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.zyjk.posmall.api.HttpService;
import com.zyjk.posmall.api.HttpService2;
import com.zyjk.posmall.api.HttpUrl;
import com.zyjk.posmall.tools.GsonUtils;
import com.zyjk.posmall.tools.LogUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static java.util.Objects.requireNonNull;

/**
 * Description：Retrofit管理器（Retrofit + RxJava + Okhttp）
 *
 * Created by Sword God on 2018/9/6.
 */
public class RetrofitManager {

    private HttpService httpService;
    private HttpService2 httpService2;
    private static SparseArray<Retrofit> sRetrofitManager = new SparseArray<>();


    private static volatile RetrofitManager instance = null;

    private RetrofitManager() {
    }

    public static RetrofitManager getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (RetrofitManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }


    public HttpService baseService() {
        initRetrofit(HttpUrl.MAIN_SERVICE);
        return httpService;
    }

    public HttpService2 baseService2() {
        initRetrofit(HttpUrl.MAIN_SERVICE2);
        return httpService2;
    }

    /**
     * 初始化Retrofit管理器配置
     *
     * @param hostType 服务器连接类型
     */
    private void initRetrofit(int hostType) {
        Retrofit retrofit = sRetrofitManager.get(hostType);
        if (retrofit == null) {
            // 初始化OkHttp配置
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(new LogInterceptor())
                    .addInterceptor(new CodeInterceptor())
                    .build();
            // 初始化Retrofit配置
            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(getHostType(hostType))
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            sRetrofitManager.put(hostType, mRetrofit);
            switch (hostType) {
                // 主服务器地址接口信息配置
                case HttpUrl.MAIN_SERVICE:
                    httpService = mRetrofit.create(HttpService.class);
                    break;
                case HttpUrl.MAIN_SERVICE2:
                    httpService2 = mRetrofit.create(HttpService2.class);
                default:
                    httpService2 = mRetrofit.create(HttpService2.class);
                    break;
            }
        }

    }

    /**
     * 获取连接服务器地址
     *
     * @param hostType 服务器地址类型
     * @return hostUrl
     */
    private String getHostType(@HttpUrl.isChekout int hostType) {
        String hostUrl;
        switch (hostType) {
            case HttpUrl.MAIN_SERVICE:
                hostUrl = HttpUrl.BASICS_SERVICE;
                break;
            default:
                hostUrl = HttpUrl.BASICS_SERVICE;
                break;
        }
        return hostUrl;
    }

    /**
     * Log日志拦截器
     */
    private static class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            String bodyStr = bodyToString(request);
            Log.i(this.getClass().getName(), String.format("请求接口 %s: body=   %s", request.url(), bodyStr));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            if (response.body() != null) {
                ResponseBody body = response.peekBody(1024 * 1024);
                LogUtils.i(String.format(Locale.getDefault(), "返回数据 %s in %.1fms%n   %s", response.request().url(), (t2 - t1) / 1e6d, body.string()));
            } else {
                Log.i(this.getClass().getName(), "body null");

            }
            return response;

        }

        /**
         * 字符串输出
         *
         * @param request 请求数据
         * @return buffer
         */
        @SuppressLint("NewApi")
        private static String bodyToString(final Request request) {

            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                if (copy == null || copy.body() == null) {
                    return "";
                }
                requireNonNull(copy.body()).writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                return "did not work";
            }
        }
    }

    /**
     * 服务器返回code编码拦截器
     * 根据需求处理统一拦截
     * 比如token异常处理 与服务器定义好统一code编码
     */
    public static class CodeInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();

            Response response = chain.proceed(request);
            ResponseBody body = response.peekBody(1024 * 1024);

            String bodyStr = body.string();
            int code;
            String msg;

            if (!TextUtils.isEmpty(bodyStr)) {
                HttpResult httpResult = GsonUtils.GsonToBean(bodyStr, HttpResult.class);
                if (null == httpResult) {
                    throw new Transformer.ServerException();
                }
                code = httpResult.getCode();
                msg = httpResult.getMsg();
            } else {
                throw new Transformer.ServerException();
            }

            switch (code) {
                case 0:
                    //code编码主要根据服务器返回识别
                    return response;
                case 200:
                    return response;
                case -7:
                    // 可以通过发送RxBus通知进入登录页面
                    throw new Transformer.TokenException(code, msg);
                default:
                    throw new Transformer.ServerException(code, msg);
            }
        }


    }

}

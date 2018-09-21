package com.zyjk.posmall.request;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description：RxJava线程调度器
 *
 * Created by Sword God on 2018/9/6.
 */
public class Transformer {

    /**
     * RxJava线程调度器（线程切换）
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<HttpResult<T>, T> defaultSchedulers() {
        return baseResultObservable -> baseResultObservable
                .map(new ServerResultFunc<T>())
                .onErrorResumeNext(new BaseFunction<T>())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 异常信息抓取器
     *
     * @param <T>
     */
    private static class BaseFunction<T> implements Func1<Throwable, Observable<T>> {

        @Override
        public Observable<T> call(Throwable throwable) {
            return Observable.error(LogException.getInstance().throwableUtils(throwable));
        }
    }

    /**
     * 服务器HttpCode编码识别器
     *
     * @param <T>
     */
    private static class ServerResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            // 判断服务器code编码负数统一处理
            if (httpResult.getCode() < 0) {
                throw new ServerException(httpResult.getCode(), httpResult.getMsg());
            }
            return httpResult.getData();
        }


    }

    /**
     * 服务器异常信息
     */
    public static class ServerException extends RuntimeException {

        private int code;
        private String msg;

        ServerException() {

        }

        ServerException(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class TokenException extends RuntimeException {

        private int code;
        private String msg;

        TokenException(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }
}

package com.zyjk.posmall.request;

import android.content.Context;

import rx.Subscriber;

/**
 * Description：网络请求返回结果回调
 *
 * Created by Sword God on 2018/9/6.
 */
public abstract class RequestResult<T> extends Subscriber<T> {

    private static final int ERROR = 10000;
    private Context context;

    protected RequestResult() {

    }

    protected RequestResult(Context context) {
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != context) {
//            LoadingDialogUtils.getInstance().init((Activity) context,"");
        }
    }

    @Override
    public void onCompleted() {
        onCompletedListener();
        if (null != context) {
//            LoadingDialogUtils.getInstance().dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (null != context) {
//            LoadingDialogUtils.getInstance().dismiss();
        }
        if (e instanceof LogException.ApiException) {
            onErrorListener((LogException.ApiException) e);
        } else {
            onErrorListener(new LogException.ApiException(e, ERROR, "未知错误"));
        }
    }

    @Override
    public void onNext(T t) {
        if (null != context) {
//            LoadingDialogUtils.getInstance().dismiss();
        }
        onNextListener(t);
    }

    /**
     * 请求完成回调
     */
    protected abstract void onCompletedListener();

    /**
     * 请求异常回调
     *
     * @param e 异常信息
     */
    protected abstract void onErrorListener(LogException.ApiException e);

    /**
     * 请求成功回调
     *
     * @param t 返回泛型Model
     */
    protected abstract void onNextListener(T t);

}

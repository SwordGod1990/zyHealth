package com.zyjk.posmall.base;

import android.content.Context;

import rx.subscriptions.CompositeSubscription;
/**
 * author : Sword God
 * e-mail : 156690858@qq.com
 * date   : 2018/9/2516:08
 * desc   : 请求取消及Context获取
 * version: 1.0
 */
public abstract class BaseSubscription implements BasePresenter {

    protected CompositeSubscription subscriptions = new CompositeSubscription();

    private Context context;

    @Override
    public void onDestroy() {
        if (subscriptions != null) {
            subscriptions.clear();
        }
        subscriptions = null;
    }

    /**
     * 这里的Context根据自己项目需求调用，由于该项目将请求结果回调类（RequestResult）当中统一添加了Loading加载
     * @param context 上下文
     * @return
     */
    public BaseSubscription Bulider(Context context) {
        this.context = context;
        return this;
    }


    public Context getContext() {
        return context;
    }

}

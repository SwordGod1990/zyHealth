package com.zyjk.posmall.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;

import com.zyjk.posmall.utils.ActivityManager;
import com.zyjk.posmall.utils.SpUtil;

import butterknife.ButterKnife;

/**
 * Created by Sword God on 2018/8/28.
 * activity基础类
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    private SharedPreferences spUtils;
    private SparseArray<Object> mViews;
    boolean isCancelable = false;

    protected abstract int getContentView();

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View view);

    public BaseActivity() {
        mContext = this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils = SpUtil.getSharePerference(this);
        mViews = new SparseArray<>();
        setContentView(getContentView());
        ButterKnife.bind(this);
        initViews();
        initListener();
        initData();

        // 禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected <E extends View> E findView(int viewId) {
        E view = (E) mViews.get(viewId);

        if (view == null) {
            view = (E) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    protected <E extends View> void setOnClickListener(E view) {
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        processClick(v);
    }

    public void setPageCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }

}

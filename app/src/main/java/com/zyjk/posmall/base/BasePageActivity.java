package com.zyjk.posmall.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;

import com.zyjk.posmall.MyApplication;
import com.zyjk.posmall.tools.SpUtil;

import butterknife.ButterKnife;

/**
 * author : Sword God
 * e-mail : 156690858@qq.com
 * date   : 2018/9/2516:08
 * desc   : 继承于FragmentActivity，实现View.OnClickListener监听事件
 *          用一个SparseArray<View> 来管理控件的findView
 *          findView(int viewId)方法提取出来，强制转换等
 *          setOnclick(E view)设置监听事件
 * version: 1.0
 */

public abstract class BasePageActivity extends AppCompatActivity implements View.OnClickListener {
    //管理控件的的findView
    private SparseArray<View> mViews;
    protected Context mContext;
    private SharedPreferences spUtils;

    //获取布局资源文件
    public abstract int getLayoutID();

    //初始化控件
    public abstract void initViews();

    //注册监听事件
    public abstract void registerListener();

    //初始化数据，如：网络请求获取数据
    public abstract void initData();

    //控件的点击事件
    public abstract void viewsClick(View view);
    public BasePageActivity() {
        mContext = this;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils = SpUtil.getSharePerference(this);
        MyApplication.getInstance().getActivityManager().addActivity(this);
        mViews = new SparseArray<View>();//初始化集合
        setContentView(getLayoutID());   //设置ContentView
        ButterKnife.bind(this);
        initViews();                     //初始化控件
        registerListener();              //注册监听事件
        initData();                      //初始化数据
    }

    /**
     * 将控件的点击事件传递给抽象方法viewsClick,
     * 让子类去实现
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        viewsClick(v);
    }

    /**
     * findVeiwById
     *
     * @param viewId 控件的id
     * @param <E>    控件的类型
     * @return view
     */
    protected <E extends View> E findView(int viewId) {
        E view = (E) mViews.get(viewId);
        if (null == view) {
            view = (E) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * 给控件设置点击事件，最后传递给抽象方法viewsClick()
     *
     * @param view 需要设置点击事件的控件
     * @param <E>  控件的类型
     */
    protected <E extends View> void setOnclick(E view) {
        view.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().getActivityManager().killActivity(this);
    }
}

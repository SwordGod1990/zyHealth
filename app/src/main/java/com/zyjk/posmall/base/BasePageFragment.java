package com.zyjk.posmall.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : Sword God
 * e-mail : 156690858@qq.com
 * date   : 2018/9/2516:06
 * desc   : 继承Fragment，实现View.OnClickListener监听事件
 *          懒加载模式:界面对用户可见的时候加载，懒嘛~懒，是节省流量的一种方式
 *          用了几个boolean值，为了判断对用户可见的时候才加载
 * version: 1.0
 */

public abstract class BasePageFragment extends Fragment implements View.OnClickListener {
    private boolean isFirstLoad;          //是否是第一次加载
    private boolean isVisible;            //是否对用户可见
    private boolean isInitView;           //是否初始化控件
    protected View convertView;           //显示的converView
    private SparseArray<View> mViews;     //管理View的集合
    private Unbinder unbinder;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViews = new SparseArray<View>();//初始化集合
        convertView = inflater.inflate(getLayoutID(), container, false);//用布局填充器填充布局
        unbinder = ButterKnife.bind(this, convertView);
        initViews();//初始化控件
        isInitView = true;//已经初始化
        lazyLoad();//懒加载
        return convertView;
    }

    /**
     * 懒加载
     * 如果不是第一次加载、没有初始化控件、不对用户可见就不加载
     */
    protected void lazyLoad() {
        registerListener();//注册监听事件
        if (!isFirstLoad || !isInitView || !isVisible) {
            return;
        }
        initData();//初始化数据
        isFirstLoad = false;//已经不是第一次加载
    }

    /**
     * 是否对用户可见
     *
     * @param isVisibleToUser true:表示对用户可见，反之则不可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            //这里根据需求，如果不要每次对用户可见的时候就加载就不要调用lazyLoad()这个方法，根据个人需求
            lazyLoad();
        } else {
            isVisible = false;
        }
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
        if (null != convertView) {
            E view = (E) mViews.get(viewId);
            if (null == view) {
                view = (E) convertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

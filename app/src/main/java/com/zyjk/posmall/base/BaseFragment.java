package com.zyjk.posmall.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Sword God on 2018/8/28.
 * fragment基础类
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    public static final int XRECYCLER_HEAD_DEFAULT_COUNT = 1;

    private SparseArray<View> mViews;
    private View convertView;
    private Unbinder unbinder;
    boolean isCancelable = false;

    protected abstract int getContentView();

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View view);

    public BaseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mViews = new SparseArray<>();
        convertView = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, convertView);

        initViews();
        initListener();
        initData();
        return convertView;
    }

    protected <E extends View> E findView(int viewId) {
        if (convertView != null) {
            E view = (E) mViews.get(viewId);

            if (view == null) {
                view = (E) convertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    protected <E extends View> void setOnClickListener(E view) {
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        processClick(view);
    }


    public void setPageCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }

}

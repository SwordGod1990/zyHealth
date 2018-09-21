package com.zyjk.posmall.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseFragment;
import com.zyjk.posmall.page.LoadPage;
import com.zyjk.posmall.utils.ToastUtil;

import butterknife.BindView;

/**
 * Created by Sword God on 2018/8/28.
 */

public class TabDetailsFragment extends BaseFragment {

    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;
    @BindView(R.id.titleBar_left_iv)
    ImageView titleBar_left_iv;
    @BindView(R.id.mLoadPage)
    LoadPage mLoadPage;

    @Override
    protected int getContentView() {
        return R.layout.fragment_goods_details;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("详情");
        titleBar_left_iv.setVisibility(View.GONE);
        mLoadPage.setGetDataListener(new LoadPage.GetDataListener() {
            @Override
            public void onGetData() {
                mLoadPage.switchPage(LoadPage.STATE_NO_NET);
                initData();
                ToastUtil.toast("网络");
            }
        });
        mLoadPage.switchPage(LoadPage.STATE_NO_NET);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View view) {

    }
}

package com.zyjk.posmall.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/13.
 * 订单详情
 */

public class Close_OrderDetailsFragment extends BaseFragment {
    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;

    @Override
    protected int getContentView() {
        return R.layout.fragment_send_orderdetails;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("订单详情");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.titleBar_left_iv})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_iv:
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}

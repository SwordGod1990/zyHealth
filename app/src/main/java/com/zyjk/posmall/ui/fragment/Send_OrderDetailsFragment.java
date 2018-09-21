package com.zyjk.posmall.ui.fragment;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseFragment;
import com.zyjk.posmall.window.RefundPopWindow;
import com.zyjk.posmall.ui.activity.RefundNoticeActivity;
import com.zyjk.posmall.utils.CommonUtils;
import com.zyjk.posmall.view.MyScrollview;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/13.
 * 订单详情(待发货)
 */

public class Send_OrderDetailsFragment extends BaseFragment implements RefundPopWindow.OnItemClickListener {

    private RefundPopWindow popWindow;

    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;
    @BindView(R.id.orderDetails_myScrollview)
    MyScrollview mScrollView;

    @Override
    protected int getContentView() {
        return R.layout.fragment_send_orderdetails;
    }

    @Override
    public void initViews() {
        popWindow = new RefundPopWindow(getActivity());
        titleBar_center_tv.setText("订单详情");
    }

    @Override
    public void initListener() {
        popWindow.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.titleBar_left_iv, R.id.orderDetails_refund_tv})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_iv:
                getActivity().finish();
                break;
            case R.id.orderDetails_refund_tv:
                popWindow.showAtLocation(mScrollView, Gravity.CENTER | Gravity.CENTER, 0, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//隐藏自动软键盘
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.window_refund_cancel_tv:
                popWindow.dismiss();
                break;
            case R.id.window_refund_true_tv:
                //确定
                CommonUtils.startAct(getActivity(), RefundNoticeActivity.class);
                popWindow.dismiss();
                break;
            default:
                break;
        }
    }
}

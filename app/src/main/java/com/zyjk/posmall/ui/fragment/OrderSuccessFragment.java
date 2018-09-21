package com.zyjk.posmall.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseFragment;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/8.
 * 订单提交成功
 */

public class OrderSuccessFragment extends BaseFragment {

    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;

    @Override
    protected int getContentView() {
        return R.layout.fragment_ordersuccess;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("订单提交成功");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.titleBar_left_iv, R.id.frg_orderSuccess_lookOrder_iv})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_iv:
                getActivity().finish();
                break;
            case R.id.frg_orderSuccess_lookOrder_iv:
                //待发货
                CommonUtils.startAct(getContext(), CommonFragmentActivity.FRAGMENT_SEND_ORDER_DETAILS);
                break;
            default:
                break;
        }
    }
}

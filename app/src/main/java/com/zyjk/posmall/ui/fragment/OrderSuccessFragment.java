package com.zyjk.posmall.ui.fragment;

import android.view.View;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.tools.CommonUtils;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/8.
 * 订单提交成功
 */

public class OrderSuccessFragment extends BasePageFragment {

    @Override
    public int getLayoutID() {
        return R.layout.fragment_ordersuccess;
    }

    @Override
    public void initViews() {
        TitleSet();
    }

    /**
     * 标题设置
     */
    private void TitleSet() {

    }

    @Override
    public void registerListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.frg_orderSuccess_lookOrder_iv})
    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.frg_orderSuccess_lookOrder_iv:
                //待发货
                CommonUtils.startAct(getContext(), CommonFragmentActivity.FRAGMENT_SEND_ORDER_DETAILS);
                break;
            default:
                break;
        }
    }
}

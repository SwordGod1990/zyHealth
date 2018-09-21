package com.zyjk.posmall.ui.fragment;

import android.view.View;
import android.view.WindowManager;

import com.zyjk.posmall.R;
import com.zyjk.posmall.ui.activity.ReceiveGoodsMsgActivity;
import com.zyjk.posmall.base.BaseFragment;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.utils.CommonUtils;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/6.
 * 确认采购
 */

public class ConfirmPurchaseFragment extends BaseFragment {


    @Override
    protected int getContentView() {
        return R.layout.fragment_confirmpurchase;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.frg_confirmPurchase_modifyAddress_iv, R.id.frg_confirmPurchase_tv})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.frg_confirmPurchase_modifyAddress_iv:
                //修改地址
                CommonUtils.startAct(getContext(), ReceiveGoodsMsgActivity.class);
                break;
            case R.id.frg_confirmPurchase_tv:
                CommonUtils.startAct(getActivity(), CommonFragmentActivity.FRAGMENT_ORDER_SUCCESS);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//隐藏自动软键盘
    }
}

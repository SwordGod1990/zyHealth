package com.zyjk.posmall.ui.fragment;

import android.view.View;
import android.view.WindowManager;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.ui.activity.ReceiptInformationActivity;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.tools.CommonUtils;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/6.
 * 确认采购
 */

public class ConfirmPurchaseFragment extends BasePageFragment {


    @Override
    public int getLayoutID() {
        return R.layout.fragment_confirmpurchase;
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

    @OnClick({R.id.frg_confirmPurchase_modifyAddress_iv, R.id.frg_confirmPurchase_tv})
    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.frg_confirmPurchase_modifyAddress_iv:
                //修改地址
                CommonUtils.startAct(getContext(), ReceiptInformationActivity.class);
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

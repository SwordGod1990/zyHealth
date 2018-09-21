package com.zyjk.posmall.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.zyjk.posmall.R;
import com.zyjk.posmall.ui.fragment.Already_OrderDetailsFragment;
import com.zyjk.posmall.ui.fragment.Close_OrderDetailsFragment;
import com.zyjk.posmall.ui.fragment.ConfirmPurchaseFragment;
import com.zyjk.posmall.ui.fragment.DiscountDetailsFragment;
import com.zyjk.posmall.ui.fragment.FullGiftDetailsFragment;
import com.zyjk.posmall.ui.fragment.Get_OrderDetailsFragment;
import com.zyjk.posmall.ui.fragment.OrderSuccessFragment;
import com.zyjk.posmall.ui.fragment.Pay_OrderDetailsFragment;
import com.zyjk.posmall.ui.fragment.Send_OrderDetailsFragment;

/**
 * Created by Sword God on 2018/8/28.
 * 单个fragment页面 可以统一走这里
 */

public class CommonFragmentActivity extends BaseActivity {
    public static final String TARGET = "target";
    public static final int FRAGMENT_MODIFY_PHONE = 1; // 修改手机
    public static final int FRAGMENT_MODIFY_PWD = 2;   //修改密码
    public static final int FRAGMENT_FORGOT_PWD = 3;   //忘记密码
    public static final int FRAGMENT_SCAVENGERS = 5;   //扫码购药
    public static final int FRAGMENT_DISCOUNT_DETAILS = 6;          //折扣性情
    public static final int FRAGMENT_GIFT_DETAILS = 7;              //满赠详情
    public static final int FRAGMENT_CONFIRM_PURCHASE = 8;          //确认采购
    public static final int FRAGMENT_ORDER_SUCCESS = 9;             //采购成功
    public static final int FRAGMENT_PAY_ORDER_DETAILS = 10;        //待支付
    public static final int FRAGMENT_SEND_ORDER_DETAILS = 11;       //待发货
    public static final int FRAGMENT_GET_ORDER_DETAILS = 12;        //待收货
    public static final int FRAGMENT_already_ORDER_DETAILS = 13;   //已发货
    public static final int FRAGMENT_CLOSE_ORDER_DETAILS = 14;     //交易关闭

    private FragmentManager fragmentManager;

    @Override
    protected int getContentView() {
        return R.layout.activity_common;
    }

    @Override
    public void initViews() {
        fragmentManager = getSupportFragmentManager();
        int target = getIntent().getIntExtra(TARGET, -1);
        if (target < 0) finish();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (target) {
            case FRAGMENT_MODIFY_PHONE:
                //
                break;
            case FRAGMENT_MODIFY_PWD:
                //
                break;
            case FRAGMENT_FORGOT_PWD:
                //
                break;
            case FRAGMENT_SCAVENGERS:
                //
                break;
            case FRAGMENT_DISCOUNT_DETAILS:
                //折扣详情
                fragment = new DiscountDetailsFragment();
                break;
            case FRAGMENT_GIFT_DETAILS:
                //满赠详情
                fragment = new FullGiftDetailsFragment();
                break;
            case FRAGMENT_CONFIRM_PURCHASE:
                //确认采购
                fragment = new ConfirmPurchaseFragment();
                break;
            case FRAGMENT_ORDER_SUCCESS:
                //订单提交成功
                fragment = new OrderSuccessFragment();
                break;
            case FRAGMENT_PAY_ORDER_DETAILS:
                //待支付
                fragment = new Pay_OrderDetailsFragment();
                break;
            case FRAGMENT_SEND_ORDER_DETAILS:
                //待发货
                fragment = new Send_OrderDetailsFragment();
                break;
            case FRAGMENT_GET_ORDER_DETAILS:
                //待收货
                fragment = new Get_OrderDetailsFragment();
                break;
            case FRAGMENT_already_ORDER_DETAILS:
                //已发货
                fragment = new Already_OrderDetailsFragment();
                break;
            case FRAGMENT_CLOSE_ORDER_DETAILS:
                //交易关闭
                fragment = new Close_OrderDetailsFragment();
                break;
            default:
                break;
        }
        transaction.add(R.id.common_fragment_activity, fragment);
        transaction.commit();
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

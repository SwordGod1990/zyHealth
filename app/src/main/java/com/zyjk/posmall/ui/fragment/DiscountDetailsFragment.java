package com.zyjk.posmall.ui.fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.base.Constant;
import com.zyjk.posmall.ui.activity.OrderListActivity;
import com.zyjk.posmall.tools.CommonUtils;
import com.zyjk.posmall.view.TitleBar;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/6.
 * 商品详情(促销)
 */

public class DiscountDetailsFragment extends BasePageFragment {

    private CountDownTimer timer;
    private long timeStemp = 0000;//24h时间戳

    @BindView(R.id.discountDetails_D)
    TextView discount_M_tv;
    @BindView(R.id.discountDetails_H)
    TextView discount_D_tv;
    @BindView(R.id.discountDetails_M)
    TextView discount_H_tv;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_discountdeatils;
    }

    @Override
    public void initViews() {
        TitleSet();
    }

    /**
     * 标题设置
     */
    private void TitleSet() {
        mTitleBar.setBackFinish(getActivity());
    }

    @Override
    public void registerListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.view_bottom_rl:
                //清单列表
                CommonUtils.startAct(getContext(), OrderListActivity.class);
                break;
            default:
                break;

        }
    }

    /**
     * 开始倒计时
     */
    public void startTimerTask() {
        if (timer == null) {
            timer = new CountDownTimer(timeStemp, Constant.TIMER) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long day = millisUntilFinished / (1000 * 60 * 60 * 24);
                    long hour = (millisUntilFinished - day * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                    long minute = (millisUntilFinished - day * (1000 * 60 * 60 * 24) - hour * (1000 * 60 * 60)) / (1000 * 60);
                    long second = (millisUntilFinished - day * (1000 * 60 * 60 * 24) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;
                    discount_D_tv.setText(day + "");
                    discount_H_tv.setText(hour + "");
                    discount_M_tv.setText(minute + "");
                    Log.i("-------TAG------", "onTick: " + day + "天，" + hour + "时，" + minute + "分，" + second + "秒");
                }

                @Override
                public void onFinish() {
                    //倒计时为0时自动进入此方法
                    discount_D_tv.setText(00 + "");
                    discount_H_tv.setText(00 + "");
                    discount_M_tv.setText(00 + "");
                }
            };
        }
        timer.start();
    }

    /**
     * 停止倒计时
     */
    public void stopTimerTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("-------TAG--------", "onStart: " + "开始");
        startTimerTask();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("-------TAG--------", "onPause: " + "暂停");
        stopTimerTask();
    }
}

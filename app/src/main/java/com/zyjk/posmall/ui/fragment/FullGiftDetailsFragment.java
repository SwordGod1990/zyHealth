package com.zyjk.posmall.ui.fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.ui.activity.ScavengersActivity;
import com.zyjk.posmall.base.BaseFragment;
import com.zyjk.posmall.base.Constant;
import com.zyjk.posmall.window.FullGiftPopupWindow;
import com.zyjk.posmall.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/6.
 * 商品详情(满赠)
 */

public class FullGiftDetailsFragment extends BaseFragment implements FullGiftPopupWindow.OnItemClickListener {

    FullGiftPopupWindow popupWindow;
    private CountDownTimer timer;
    private long timeStemp = 0000;//24h时间戳

    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;
    @BindView(R.id.titleBar_left_iv)
    ImageView titleBar_left_iv;
    @BindView(R.id.gift_details_scrollView)
    ScrollView mScrollView;
    @BindView(R.id.gift_details_D)
    TextView gift_D_tv;
    @BindView(R.id.gift_details_H)
    TextView gift_H_tv;
    @BindView(R.id.gift_details_M)
    TextView gift_M_tv;

    @Override
    protected int getContentView() {
        return R.layout.fragment_gift_deatils;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("商品详情");
        popupWindow = new FullGiftPopupWindow(getContext());
    }

    @Override
    public void initListener() {
        popupWindow.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.titleBar_left_ll, R.id.gift_details_gift_rl, R.id.view_bottom_rl})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_ll:
                getActivity().finish();
                break;
            case R.id.gift_details_gift_rl:
                //赠品信息弹窗
                popupWindow.showAtLocation(mScrollView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.view_bottom_rl:
                CommonUtils.startAct(getContext(), ScavengersActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.window_fullGift_close_iv:
                popupWindow.dismiss();
                break;
        }
    }

    public void startTimerTask() {
        if (timer == null) {
            timer = new CountDownTimer(timeStemp, Constant.TIMER) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long day = millisUntilFinished / (1000 * 60 * 60 * 24);
                    long hour = (millisUntilFinished - day * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                    long minute = (millisUntilFinished - day * (1000 * 60 * 60 * 24) - hour * (1000 * 60 * 60)) / (1000 * 60);
                    long second = (millisUntilFinished - day * (1000 * 60 * 60 * 24) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;
                    gift_D_tv.setText(day + "");
                    gift_H_tv.setText(hour + "");
                    gift_M_tv.setText(minute + "");
                    Log.i("-------TAG------", "onTick: " + day + "天，" + hour + "时，" + minute + "分，" + second + "秒");
                }

                @Override
                public void onFinish() {
                    //倒计时为0时自动进入此方法
                    gift_D_tv.setText(00 + "");
                    gift_H_tv.setText(00 + "");
                    gift_M_tv.setText(00 + "");
                }
            };
        }
        timer.start();
    }

    public void stopTimerTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        startTimerTask();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("-------TAG--------", "onPause: " + "暂停");
        stopTimerTask();
    }
}

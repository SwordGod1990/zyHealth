package com.zyjk.posmall.ui.fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.base.Constant;
import com.zyjk.posmall.tools.CommonUtils;
import com.zyjk.posmall.ui.activity.OrderListActivity;
import com.zyjk.posmall.view.TitleBar;
import com.zyjk.posmall.window.PresentPopWindow;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/6.
 * 商品详情(满赠)
 */

public class PresentDetailsFragment extends BasePageFragment implements PresentPopWindow.OnPopWindowClickListener {
    private PresentPopWindow popWindow;
    private CountDownTimer timer;
    private long timeStemp = 1000;//24h时间戳

    @BindView(R.id.presentDetails_scrollView)
    ScrollView mScrollView;
    @BindView(R.id.presentDetails_D)
    TextView gift_D_tv;
    @BindView(R.id.presentDetails_H)
    TextView gift_H_tv;
    @BindView(R.id.presentDetails_M)
    TextView gift_M_tv;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_presentdetails;
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

    @OnClick({R.id.presentDetails_gift_rl, R.id.view_bottom_rl})
    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.presentDetails_gift_rl:
                //赠品信息弹窗
                popWindow = new PresentPopWindow(getActivity(), this);
                popWindow.show();
                break;
            case R.id.view_bottom_rl:
                CommonUtils.startAct(getContext(), OrderListActivity.class);
                break;
            default:
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

    @Override
    public void onPopWindowClickListener(View view) {
        switch (view.getId()) {
            case R.id.presentMessage_close_iv:
                popWindow.dismiss();
                break;
        }
    }
}

package com.zyjk.posmall.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/8.
 * 退款通知
 */

public class RefundNoticeActivity extends BaseActivity {
    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;

    @Override
    protected int getContentView() {
        return R.layout.activity_refundnotice;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("退款通知");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.titleBar_left_ll})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_ll:
                finish();
                break;
        }
    }
}

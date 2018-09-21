package com.zyjk.posmall.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Sword God on 2018/8/28.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.titleBar_left_iv)
    ImageView titleBar_left_iv;
    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("登陆");
        titleBar_left_iv.setVisibility(View.GONE);
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

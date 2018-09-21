package com.zyjk.posmall.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.zyjk.posmall.MyApplication;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseActivity;
import com.zyjk.posmall.base.Constant;
import com.zyjk.posmall.utils.CommonUtils;

import butterknife.BindView;

/**
 * Created by Sword God on 2018/8/28.
 * 欢迎页面
 */

public class SplashActivity extends BaseActivity {

    private SharedPreferences preferences;
    private int goNumbers;//进入次数

    @BindView(R.id.splash_imageView_id)
    ImageView splash_imageView_id;

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViews() {
        splash_imageView_id.setBackgroundResource(R.drawable.ic_splash11);
        //读取SharedPreferences中需要的数据
        preferences = getSharedPreferences("goNumbers", MODE_WORLD_READABLE);
        goNumbers = preferences.getInt("goNumbers", 0);
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putInt("goNumbers", ++goNumbers);
        Log.i("TAG", "initViews: " + goNumbers);
        //提交修改
        editor.commit();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //解决app每次都启动欢迎页面的问题
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
    }

    @Override
    public void processClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            MyApplication.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //判断程序与第几次运行，如果是第一次运行则跳转到引导页面
                    if (goNumbers == 1) {
                        CommonUtils.startAct(getApplicationContext(), GuideActivity.class);
                        finish();
                    } else {
                        CommonUtils.startAct(getApplicationContext(), MainActivity.class);
                        finish();
                    }

                }
            }, Constant.GO_TIME);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyApplication.getHandler().removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getHandler().removeCallbacksAndMessages(null);
    }
}

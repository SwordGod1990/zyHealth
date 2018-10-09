package com.zyjk.posmall;

import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.analytics.MobclickAgent;
import com.zyjk.posmall.tools.ActivityManager;
import com.zyjk.posmall.tools.SpUtil;

/**
 * Created by Administrator on 2018/9/3.
 */

public class MyApplication extends MultiDexApplication {
    protected static final String TAG = "MyApplication";
    private static ActivityManager activityManager = null; // activity管理类
    public static boolean SKIP_WELCOME;
    private static MyApplication instance = new MyApplication();
    private static Handler handler;
    public static String phone;//用户手机号
    private String loginKey;

    public static MyApplication getInstance() {
        return instance;
    }


    public MyApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        activityManager = ActivityManager.getInstance(); // 获得实例
        MultiDex.install(getInstance());
        handler = new Handler();
        // 首次启动不跳过欢迎界面；
        SKIP_WELCOME = false;
        Fresco.initialize(getInstance());
        initUMeng();
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public static Handler getHandler() {
        return handler;
    }

    public void setPhone(String phone) {
        SpUtil.setString(this, "mobilePhone", phone);
    }

    public String getPhone() {
        String loginKey = SpUtil.getString(this, "mobilePhone");
        return loginKey;
    }

    public void logout() {
        setLoginKey("");
    }

    public void setLoginKey(String loginKey) {
        SpUtil.setString(this, "loginKey", loginKey);
    }

    public String getLoginKey() {
        String loginKey = SpUtil.getString(this, "loginKey");
        return loginKey;
    }
    public void initUMeng(){
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType. E_UM_NORMAL);
        MobclickAgent.openActivityDurationTrack(false);
    }
}

package com.zyjk.posmall.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.zyjk.posmall.MyApplication;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageActivity;
import com.zyjk.posmall.ui.fragment.TabHomeFragment;
import com.zyjk.posmall.ui.fragment.TabOrderFragment;
import com.zyjk.posmall.ui.fragment.TabUserFragment;
import com.zyjk.posmall.tools.CommonUtils;
import com.zyjk.posmall.tools.SpUtil;
import com.zyjk.posmall.tools.ToastUtil;
import com.zyjk.posmall.view.BottomBar;

import butterknife.BindView;

/**
 * author : Sword God
 * e-mail : 156690858@qq.com
 * date   : 2018/9/2514:59
 * desc   : 主页
 * version: 1.0
 */

public class MainActivity extends BasePageActivity {

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    @BindView(R.id.bottom_bar)
    BottomBar bottom_bar;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void registerListener() {
        bottom_bar.setContainer(R.id.container_fl).setTitleBeforeAndAfterColor("#333333", "#EB3E2D")
                .addItem(TabHomeFragment.class, "我要采购", R.drawable.purchase, R.drawable.purchase_select)
                .addItem(TabOrderFragment.class, "订单", R.drawable.order, R.drawable.order_select)
                .addItem(TabUserFragment.class, "我的", R.drawable.item3_before, R.drawable.item3_after).build();
    }

    @Override
    public void initData() {
        AppVersionUpdate();
    }

    @Override
    public void viewsClick(View view) {

    }

    /**
     * 版本升级
     */
    private void AppVersionUpdate() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            ToastUtil.toast(R.string.again_exit_text);
            // 利用handler延迟发送更改状态信息
            MyApplication.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            SpUtil.setBoolean(mContext, "isout", false);
            Log.d("ldx", "-----coded--" + SpUtil.getString(mContext, "coded"));
            if (!CommonUtils.isEmpty(SpUtil.getString(mContext, "coded"))) {
                SpUtil.setString(mContext, "coded", "");
            }
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}

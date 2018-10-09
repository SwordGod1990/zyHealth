package com.zyjk.posmall.ui.activity;

import android.view.View;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageActivity;
import com.zyjk.posmall.view.TitleBar;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/8.
 * 退款通知
 */

public class RefundNoticeActivity extends BasePageActivity {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    @Override
    public int getLayoutID() {
        return R.layout.activity_refundnotice;
    }

    @Override
    public void initViews() {
        TitleSet();
    }

    /**
     * 标题设置
     */
    private void TitleSet() {
        mTitleBar.setBackFinish(this);
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

        }
    }
}

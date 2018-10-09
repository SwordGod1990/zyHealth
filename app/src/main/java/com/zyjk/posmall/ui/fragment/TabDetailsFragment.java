package com.zyjk.posmall.ui.fragment;

import android.view.View;

import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.page.LoadPage;
import com.zyjk.posmall.tools.ToastUtil;

import butterknife.BindView;

/**
 * Created by Sword God on 2018/8/28.
 */

public class TabDetailsFragment extends BasePageFragment {
    @BindView(R.id.mLoadPage)
    LoadPage mLoadPage;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_goods_details;
    }

    @Override
    public void initViews() {
        TitleSet();
        mLoadPage.setGetDataListener(new LoadPage.GetDataListener() {
            @Override
            public void onGetData() {
                mLoadPage.switchPage(LoadPage.STATE_NO_NET);
                initData();
                ToastUtil.toast("网络");
            }
        });
        mLoadPage.switchPage(LoadPage.STATE_NO_NET);
    }

    /**
     * 标题设置
     */
    private void TitleSet() {

    }

    @Override
    public void registerListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void viewsClick(View view) {

    }
}

package com.zyjk.posmall.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;
import com.zyjk.posmall.R;
import com.zyjk.posmall.adapter.SendAdapter;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.view.MyScrollview;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/13.
 * 订单详情(待发货)
 */

public class Send_OrderDetailsFragment extends BasePageFragment {

    private ArrayList<String> list = new ArrayList<>();
    @BindView(R.id.orderDetails_myScrollview)
    MyScrollview mScrollView;
    @BindView(R.id.frg_send_RecyclerView)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_send_orderdetails;
    }

    @Override
    public void initViews() {
        TitleSet();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SendAdapter sendAdapter = new SendAdapter(getContext(), R.layout.item_send, list);
        mRecyclerView.setAdapter(sendAdapter);
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
        list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("大连药业股份有限公司" + i);
        }
        Logger.d(list);
    }

    @OnClick({R.id.orderDetails_refund_tv})
    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.orderDetails_refund_tv:
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//隐藏自动软键盘
    }
}

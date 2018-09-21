package com.zyjk.posmall.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zyjk.posmall.R;
import com.zyjk.posmall.adapter.FullGiftListAdapter;
import com.zyjk.posmall.base.BaseActivity;
import com.zyjk.posmall.page.LoadPage;
import com.zyjk.posmall.utils.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/6.
 * 满赠优惠
 */

public class FullGiftListActivity extends BaseActivity {

    private View mHeaderView;
    private View mFooterView;
    private ArrayList<String> list = null;
    private int page = 1;
    private int num = 5;
    //添加商品是默认的最大添加数量
    private int max = 255;
    private final int MIN = 1;

    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;
    @BindView(R.id.aty_fullGiftList_xrv)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.aty_fullGiftList_rl)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.view_bottom_msgNum_tv)
    TextView mNum;
    @BindView(R.id.mLoadPage)
    LoadPage mLoadPage;

    @Override
    protected int getContentView() {
        return R.layout.activity_fullgift;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("满赠优惠");
        initData();
        // 添加下拉刷新的头部 和 加载更多的底部，如果不加，默认含有下拉刷新的头部，而没有加载更多的底部
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        mFooterView = LayoutInflater.from(this).inflate(R.layout.view_footer, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        mXRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        FullGiftListAdapter listAdapter = new FullGiftListAdapter(this, R.layout.item_fullgift_list, list, mListener);
        mXRecyclerView.setAdapter(listAdapter);
    }

    @Override
    public void initListener() {
        //展示
        mLoadPage.setGetDataListener(new LoadPage.GetDataListener() {
            @Override
            public void onGetData() {
                initData();
                mLoadPage.switchPage(LoadPage.STATE_LOADING);
            }
        });
        mLoadPage.switchPage(LoadPage.STATE_LOADING);
        //加载刷新
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Log.i("---------TAG---------", "onRefresh: " + "刷新");
                list.clear();
                initData();
                //mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                Log.i("---------TAG---------", "onLoadMore: " + "加载");
                initData();
                //mXRecyclerView.loadMoreComplete();
            }
        });
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "阿莫西林胶囊");
        }
    }

    @OnClick({R.id.titleBar_left_ll, R.id.view_bottom_rl})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_ll:
                finish();
                break;
            case R.id.view_bottom_rl:
                CommonUtils.startAct(this, ScavengersActivity.class);
                this.finish();
                break;
        }
    }

    public FullGiftListAdapter.MyClickListener mListener = new FullGiftListAdapter.MyClickListener() {
        @Override
        protected void myOnClick(int tag, View v) {
            //添加购物车
            mRelativeLayout.setVisibility(View.VISIBLE);
        }
    };
}

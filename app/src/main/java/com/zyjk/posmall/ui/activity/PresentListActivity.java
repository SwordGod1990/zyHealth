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
import com.zyjk.posmall.adapter.PresentListAdapter;
import com.zyjk.posmall.base.BasePageActivity;
import com.zyjk.posmall.page.LoadPage;
import com.zyjk.posmall.tools.CommonUtils;
import com.zyjk.posmall.view.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/6.
 * 满赠优惠
 */

public class PresentListActivity extends BasePageActivity {

    private View mHeaderView;
    private View mFooterView;
    private ArrayList<String> list = null;
    private int page = 1;
    private int num = 5;
    //添加商品是默认的最大添加数量
    private int max = 255;
    private final int MIN = 1;

    @BindView(R.id.presentList_xrv)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.presentList_rl)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.view_bottom_msgNum_tv)
    TextView mNum;
    @BindView(R.id.mLoadPage)
    LoadPage mLoadPage;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    @Override
    public int getLayoutID() {
        return R.layout.activity_present;
    }

    @Override
    public void initViews() {
        TitleSet();
        initData();
        // 添加下拉刷新的头部 和 加载更多的底部，如果不加，默认含有下拉刷新的头部，而没有加载更多的底部
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        mFooterView = LayoutInflater.from(this).inflate(R.layout.view_footer, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        mXRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        PresentListAdapter listAdapter = new PresentListAdapter(this, R.layout.item_presentlist, list, mListener);
        mXRecyclerView.setAdapter(listAdapter);
    }

    /**
     * 标题设置
     */
    private void TitleSet() {
        mTitleBar.setBackFinish(this);
    }

    @Override
    public void registerListener() {
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

    @OnClick({R.id.view_bottom_rl})
    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.view_bottom_rl:
                CommonUtils.startAct(this, OrderListActivity.class);
                this.finish();
                break;
        }
    }

    public PresentListAdapter.MyClickListener mListener = new PresentListAdapter.MyClickListener() {
        @Override
        protected void myOnClick(int tag, View v) {
            //添加购物车
            mRelativeLayout.setVisibility(View.VISIBLE);
        }
    };
}

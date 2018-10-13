package com.zyjk.posmall.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zyjk.posmall.R;
import com.zyjk.posmall.adapter.DiscountListAdapter;
import com.zyjk.posmall.base.BasePageActivity;
import com.zyjk.posmall.bean.AllDiscountModel;
import com.zyjk.posmall.page.LoadPage;
import com.zyjk.posmall.request.LogException;
import com.zyjk.posmall.request.RequestResult;
import com.zyjk.posmall.request.RetrofitManager;
import com.zyjk.posmall.request.Transformer;
import com.zyjk.posmall.tools.CommonUtils;
import com.zyjk.posmall.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sword God on 2018/9/6.
 * 折扣优惠
 */
public class DiscountListActivity extends BasePageActivity {

    private ArrayList<String> list;
    private int page = 1;
    private View mFooterView;

    @BindView(R.id.discountList_xrv)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.mLoadPage)
    LoadPage mLoadPage;
    @BindView(R.id.discountList_rl)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    @Override
    public int getLayoutID() {
        return R.layout.activity_discountlist;
    }

    @Override
    public void initViews() {
        TitleSet();
        initData();
        loadData();
        mFooterView = LayoutInflater.from(this).inflate(R.layout.view_footer, null);
        mXRecyclerView.setFootView(mFooterView, new CustomFooterViewCallBack() {
            @Override
            public void onLoadingMore(View yourFooterView) {

            }

            @Override
            public void onLoadMoreComplete(View yourFooterView) {
                Log.i("TAG", "onSetNoMore: " + "无数据1");
                yourFooterView.setVisibility(View.GONE);
            }

            @Override
            public void onSetNoMore(View yourFooterView, boolean noMore) {
                Log.i("TAG", "onSetNoMore: " + "无数据2");
                yourFooterView.setVisibility(View.VISIBLE);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        mXRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        DiscountListAdapter listAdapter = new DiscountListAdapter(this, R.layout.item_discount_list, list, mListener);
        mXRecyclerView.setAdapter(listAdapter);
    }

    @Override
    public void registerListener() {
        //展示
        mLoadPage.setGetDataListener(new LoadPage.GetDataListener() {
            @Override
            public void onGetData() {
                mLoadPage.switchPage(LoadPage.STATE_LOADING);
//                loadData();
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

    /**
     * 标题设置
     */
    private void TitleSet() {
        mTitleBar.setBackFinish(this);
    }

    /**
     * 加载数据
     *
     * @param
     */
    private void loadData() {
        RetrofitManager.getInstance()
                .baseService().AllDiscountList("110000", "1", "110100", "110105")
                .compose(Transformer.defaultSchedulers())
                .subscribe(new RequestResult<List<AllDiscountModel.DataBean>>() {


                    @Override
                    protected void onCompletedListener() {

                    }

                    @Override
                    protected void onErrorListener(LogException.ApiException e) {

                    }

                    @Override
                    protected void onNextListener(List<AllDiscountModel.DataBean> dataBeans) {
                        for (int i = 0; i < dataBeans.size(); i++) {
                            Log.i("----TAG----", "onNextListener: " + dataBeans.get(i).getCommodityName());
                        }
                    }
                });
    }

    public DiscountListAdapter.MyClickListener mListener = new DiscountListAdapter.MyClickListener() {
        @Override
        protected void myOnClick(int tag, View v) {
            mRelativeLayout.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

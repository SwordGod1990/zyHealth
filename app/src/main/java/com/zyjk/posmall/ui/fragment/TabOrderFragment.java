package com.zyjk.posmall.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zyjk.posmall.R;
import com.zyjk.posmall.adapter.TabOrderAdapter;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.bean.TabModel;
import com.zyjk.posmall.window.GetGoodsPopWindow;
import com.zyjk.posmall.page.LoadPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Sword God on 2018/9/6.
 * 订单
 */

public class TabOrderFragment extends BasePageFragment implements TabLayout.OnTabSelectedListener, GetGoodsPopWindow.OnItemClickListener {

    private List<TabModel> tabs = new ArrayList<>();
    private TabModel tabModel;
    private String type;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> listDrugs = new ArrayList<>();
    private GetGoodsPopWindow popWindow;
    private TabOrderAdapter orderAdapter;
    private View mView;

    @BindView(R.id.tab_frg_order_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.tab_frg_order_xrv)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.mLoadPage)
    LoadPage mLoadPage;
    private LinearLayoutManager layoutManager;

    @Override
    public int getLayoutID() {
        return R.layout.tab_fragment_order;
    }

    @Override
    public void initViews() {
        TitleSet();

        popWindow = new GetGoodsPopWindow(getContext());
        loadDtata();
        layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        mXRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        orderAdapter = new TabOrderAdapter(getActivity(), R.layout.item_tab_order, list, mListener);
        mXRecyclerView.setAdapter(orderAdapter);
        //加载
        mLoadPage.setGetDataListener(new LoadPage.GetDataListener() {
            @Override
            public void onGetData() {
                initData();
                mLoadPage.switchPage(LoadPage.STATE_LOADING);
            }
        });
        mLoadPage.switchPage(LoadPage.STATE_LOADING);
    }

    /**
     * 标题设置
     */
    private void TitleSet() {

    }

    @Override
    public void registerListener() {
        //切换页面
        mTabLayout.addOnTabSelectedListener(this);
        popWindow.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        tabs.add(new TabModel("0", "全部", R.drawable.tab_drawable_all));
        tabs.add(new TabModel("1", "待支付", R.drawable.tab_drawable_pay));
        tabs.add(new TabModel("2", "待发货", R.drawable.tab_drawable_send));
        tabs.add(new TabModel("3", "待收货", R.drawable.tab_drawable_receive));
        for (TabModel tab : tabs) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tab.title).setIcon(tab.img));
        }
        if (type.equals("0")) {
        } else if (type.equals("1")) {
        } else if (type.equals("2")) {
        } else if (type.equals("3")) {
        }
    }

    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }

    private void loadDtata() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "大连益邦药业有限公司");
        }
        listDrugs = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listDrugs.add(i + "阿莫西林胶囊");
        }
    }

    /**
     * XRecyclerView条目中按钮的点击事件
     */
    private TabOrderAdapter.MyClickListener mListener = new TabOrderAdapter.MyClickListener() {
        @Override
        protected void myOnClick(int position, View view) {
            Log.i("------TAG------", "位置：" + position + ",  内容：" + view);
            mView = view;
            popWindow.showAtLocation(mTabLayout, Gravity.CENTER | Gravity.CENTER, 0, 0);
        }
    };

    /**
     * 订单切换
     *
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabModel = tabs.get(tab.getPosition());
        type = tabModel.id;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.window_getGoods_cancel_tv:
                popWindow.dismiss();
                break;
            case R.id.window_getGoods_true_tv:
                popWindow.dismiss();
                orderAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}

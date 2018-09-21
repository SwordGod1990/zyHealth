package com.zyjk.posmall.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.qrConstant;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zyjk.posmall.R;
import com.zyjk.posmall.adapter.ScavengersAdapter;
import com.zyjk.posmall.base.BaseActivity;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.bean.AppDatas;
import com.zyjk.posmall.bean.FoodModel;
import com.zyjk.posmall.page.LoadPage;
import com.zyjk.posmall.utils.CommonUtils;
import com.zyjk.posmall.utils.ToastUtil;
import com.zyjk.posmall.view.ShoppingCartAnimationView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sword God on 2018/9/7.
 * 清单列表
 */

public class ScavengersActivity extends BaseActivity implements ScavengersAdapter.ScavengersActionCallback, LoadPage.GetDataListener, XRecyclerView.LoadingListener {

    private ScavengersAdapter listAdapter = null;
    private List<FoodModel> list;
    private List<FoodModel> selectList = new ArrayList<>();

    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;
    @BindView(R.id.aty_scavengers_xrv)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.aty_scavengers_num_tv)
    TextView num_tv;
    @BindView(R.id.aty_scavengers_price_tv)
    TextView price_tv;
    @BindView(R.id.mLoadPage)
    LoadPage mLoadPage;
    @BindView(R.id.aty_scavengers_ll)
    LinearLayout scavengers_ll;

    @Override
    protected int getContentView() {
        return R.layout.activity_scavengers;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("清单列表");
        initData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        mXRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //底层页面监听事件
        mLoadPage.setGetDataListener(this);
        mLoadPage.switchPage(LoadPage.STATE_LOADING);
    }

    @Override
    public void initListener() {
        //上拉加载、下拉刷新
        mXRecyclerView.setLoadingListener(this);
    }

    @Override
    public void initData() {
        list = AppDatas.factoryFoods();
        listAdapter = new ScavengersAdapter(this, R.layout.item_scavengers, list, this);
        mXRecyclerView.setAdapter(listAdapter);
    }

    @OnClick({R.id.titleBar_left_iv, R.id.aty_scavengers_sweep_rl, R.id.aty_scavengers_purchase_rl, R.id.aty_scavengers_submit_tv})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_iv:
                this.finish();
                break;
            case R.id.aty_scavengers_sweep_rl:
                //扫一扫
                startQrCode();
                break;
            case R.id.aty_scavengers_purchase_rl:
                //立即采购
                CommonUtils.startAct(this, CommonFragmentActivity.FRAGMENT_CONFIRM_PURCHASE);
                this.finish();
                break;
            case R.id.aty_scavengers_submit_tv:
                //结算
                if (null != selectList && selectList.size() > 0) {
                    ToastUtil.toast("你一共加入购物车" + num_tv.getText() + "份商品,总价格为：" + price_tv.getText());
                } else {
                    ToastUtil.toast("你的购物车为空");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 扫一扫
     */
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(ScavengersActivity.this, new String[]{Manifest.permission.CAMERA}, qrConstant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(ScavengersActivity.this, CaptureActivity.class);
        startActivityForResult(intent, qrConstant.REQ_QR_CODE);
    }

    /**
     * 二维码回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == qrConstant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(qrConstant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
//            activity_scanning_result_tv.setText(scanResult);
            ToastUtil.toast(scanResult);
        }
    }

    /**
     * 请求权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case qrConstant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(ScavengersActivity.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    /**
     * 添加商品
     *
     * @param view
     * @param item
     */
    @Override
    public void addAction(View view, int item) {
        Log.i("----TAG----", "addAction: " + "++++++++++++++++++++++++++");
        //--------------动画start----------------
        ShoppingCartAnimationView cartAnimationView = new ShoppingCartAnimationView(this);
        int position[] = new int[2];
        view.getLocationInWindow(position);
        cartAnimationView.setStartPosition(new Point(position[0], position[1]));
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        rootView.addView(cartAnimationView);
        int endPosition[] = new int[2];
        num_tv.getLocationInWindow(endPosition);
        cartAnimationView.setEndPosition(new Point(endPosition[0], endPosition[1]));
        cartAnimationView.startBeizerAnimation();
        //--------------动画end----------------
        FoodModel model = list.get(item);
        model.setNum(model.getNum() + 5);
        listAdapter.notifyDataSetChanged();
        calculatePrice();
    }

    /**
     * 减少商品
     *
     * @param position
     */
    @Override
    public void reduceGood(int position) {
        Log.i("----TAG----", "reduceGood: " + "--------------------------");
        FoodModel model = list.get(position);
        model.setNum(model.getNum() - 1);
        listAdapter.notifyDataSetChanged();
        calculatePrice();
    }

    /**
     * 购物车份数+总价格计算
     */
    private void calculatePrice() {
        selectList.clear();
        double price = 0;
        int num = 0;
        Iterator<FoodModel> iterator = list.iterator();
        while (iterator.hasNext()) {
            FoodModel model = iterator.next();
            if (model.getNum() != 0) {
                selectList.add(model);
                price += model.getPrice() * model.getNum();
                num += model.getNum();
            }
        }
        price_tv.setText("￥" + listAdapter.priceResult(price) + "元");
        num_tv.setText(num + "");
    }

    /**
     * （无数据、无网络）页面展示
     */
    @Override
    public void onGetData() {
        mLoadPage.switchPage(LoadPage.STATE_LOADING);
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {

    }

    /**
     * 加载
     */
    @Override
    public void onLoadMore() {

    }

}

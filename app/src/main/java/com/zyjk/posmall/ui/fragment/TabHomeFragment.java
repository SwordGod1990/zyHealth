package com.zyjk.posmall.ui.fragment;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.zyjk.posmall.R;
import com.zyjk.posmall.ui.activity.DiscountListActivity;
import com.zyjk.posmall.ui.activity.FullGiftListActivity;
import com.zyjk.posmall.adapter.HomeDiscountAdapter;
import com.zyjk.posmall.adapter.HomeFullGiftAdapter;
import com.zyjk.posmall.base.BaseFragment;
import com.zyjk.posmall.window.GridSpacingItemDecoration;
import com.zyjk.posmall.utils.CommonUtils;
import com.zyjk.posmall.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sword God on 2018/9/6.
 * 我要采购
 */

public class TabHomeFragment extends BaseFragment {

    private CityPicker mCP;       // 城市选择
    private ArrayList<String> list;
    private LocationClient mLocationClient = null;
    int spanCount = 2; // 3 columns
    int spacing = 10; // 50px
    boolean includeEdge = false;
    private String province;

    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;
    @BindView(R.id.titleBar_left_iv)
    ImageView titleBar_left_iv;
    @BindView(R.id.goodsEnquiry_default_iv)
    ImageView mDefault_iv;
    @BindView(R.id.view_address_name_tv)
    TextView address;
    @BindView(R.id.goodsEnquiry_discount_rv)
    RecyclerView mDiscount_rv;
    @BindView(R.id.goodsEnquiry_gift_rv)
    RecyclerView mGift_rv;

    @Override
    protected int getContentView() {
        return R.layout.tab_fragment_goodsenquiry;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("我要采购");
        titleBar_left_iv.setVisibility(View.GONE);
        BaiduLocation();//定位
        initData();
        mDiscount_rv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mGift_rv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        //设置布局管理器
        mDiscount_rv.setLayoutManager(gridLayoutManager);
        mGift_rv.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
    }

    /**
     * 百度定位初始化
     */
    private void BaiduLocation() {
        MyLocationListener myListener = new MyLocationListener();
        mLocationClient = new LocationClient(getContext());
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setAddrType("all");
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Glide.with(getActivity()).load(R.drawable.rectangle).error(R.drawable.rectangle).into(mDefault_iv);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("第" + i + "个");
        }
        Log.i("TAG", "initData: " + list);
        //设置折扣Adapter
        HomeDiscountAdapter discountAdapter = new HomeDiscountAdapter(getContext(), R.layout.item_goodsenquiry_discount, list);
        mDiscount_rv.setAdapter(discountAdapter);
        //设置满赠Adapter
        HomeFullGiftAdapter giftAdapter = new HomeFullGiftAdapter(getContext(), R.layout.item_goodsenquiry_gift, list);
        mGift_rv.setAdapter(giftAdapter);
    }

    @OnClick({R.id.goodsEnquiry_address_rl, R.id.goodsEnquiry_giftMore_tv, R.id.goodsEnquiry_discountMore_tv})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.goodsEnquiry_address_rl:
                //地区选择
                if (province == null) {
                    mCityPicher();
                    mCP.show();
                } else {
                    BaiduLocation();
                }
                break;
            case R.id.goodsEnquiry_giftMore_tv:
                //满赠优惠
                CommonUtils.startAct(getActivity(), FullGiftListActivity.class);
                break;
            case R.id.goodsEnquiry_discountMore_tv:
                //折扣优惠
                CommonUtils.startAct(getActivity(), DiscountListActivity.class);
                break;
        }
    }

    /**
     * 城市选择
     */
    private void mCityPicher() {
        mCP = new CityPicker.Builder(getContext()).textSize(20)
                //地址选择
                .title("配送至")
                .backgroundPop(R.color.color_white)
                //文字的颜色
                .titleBackgroundColor("#EB3E2D").titleTextColor("#ffffff").backgroundPop(0xa0000000).confirTextColor("#ffffff").cancelTextColor("#ffffff").province("xx省").city("xx市").district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10).onlyShowProvinceAndCity(false).build();

        //监听
        mCP.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                String province = citySelected[0];                       // 省
                String city = citySelected[1];                           // 市
                String district = citySelected[2];                       // 区。县。（两级联动，必须返回空）
                String code = citySelected[3];                           // 邮证编码
                address.setText(province + "-" + city + "-" + district);  // 设置返回结果
            }

            @Override
            public void onCancel() {
//                ToastUtil.toast("ss");
            }
        });
    }

    /**
     * 度娘定位(注：定位可能出现返回null,首先检查时间是否正确)
     */
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {

            try {
                //当前设备位置所在的省
                province = location.getProvince();
                //当前设备位置所在的市
                String city = location.getCity();
                //当前设备位置所在的街道
                String street = location.getStreet();
                String streetNumber = location.getStreetNumber();
                Log.i("-------TAG------", "位置: " + province + " " + city + " " + street + " " + streetNumber);
                if (province != null) {
                    address.setText(province + " " + city + " " + street + " " + streetNumber);
                } else {
                    ToastUtil.toast("无法定位，请检查网络");
                    address.setText("暂无定位，请手动选择");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("TAG", "onStart: " + "开始定位");
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationClient.stop();
        Log.i("TAG", "onStart: " + "暂停定位");
    }

    /**
     * 权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            ToastUtil.toast("许可授权才能使用此功能");
                            getActivity().finish();
                            return;
                        }
                    }
                    mLocationClient.start();
                } else {
                    ToastUtil.toast("发生未知错误");
                    getActivity().finish();
                }
                break;
            default:
        }
    }
}

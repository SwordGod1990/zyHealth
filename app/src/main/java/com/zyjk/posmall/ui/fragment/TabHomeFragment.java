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
import com.orhanobut.logger.Logger;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.zyjk.posmall.R;
import com.zyjk.posmall.adapter.HomeDiscountAdapter;
import com.zyjk.posmall.adapter.HomePresentAdapter;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.ui.activity.DiscountListActivity;
import com.zyjk.posmall.ui.activity.PresentListActivity;
import com.zyjk.posmall.tools.CommonUtils;
import com.zyjk.posmall.tools.ToastUtil;
import com.zyjk.posmall.view.TitleBar;
import com.zyjk.posmall.window.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sword God on 2018/9/6.
 * 我要采购
 */

public class TabHomeFragment extends BasePageFragment {

    private CityPicker mCP;       // 城市选择
    private ArrayList<String> list;
    private LocationClient mLocationClient = null;
    int spanCount = 2; // 3 columns
    int spacing = 10; // 50px
    boolean includeEdge = false;
    private String province;
    List<String> imgesUrl = new ArrayList<>();

    @BindView(R.id.goodsEnquiry_default_iv)
    ImageView mDefault_iv;
    @BindView(R.id.view_address_name_tv)
    TextView address;
    @BindView(R.id.goodsEnquiry_discount_rv)
    RecyclerView mDiscount_rv;
    @BindView(R.id.goodsEnquiry_gift_rv)
    RecyclerView mGift_rv;
    @BindView(R.id.goodsEnquiry_mXBanner)
    XBanner mXBanner;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    @Override
    public int getLayoutID() {
        return R.layout.tab_fragment_goodsenquiry;
    }

    @Override
    public void initViews() {
        TitleSet();
        BaiduLocation();//定位
        BannerData();
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

    @Override
    public void registerListener() {

    }

    @Override
    public void initData() {
        Glide.with(getActivity()).load(R.drawable.rectangle).error(R.drawable.rectangle).into(mDefault_iv);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("第" + i + "个");
        }
        Logger.d(list);
        //设置折扣Adapter
        HomeDiscountAdapter discountAdapter = new HomeDiscountAdapter(getContext(), R.layout.item_goodsenquiry_discount, list);
        mDiscount_rv.setAdapter(discountAdapter);
        //设置满赠Adapter
        HomePresentAdapter giftAdapter = new HomePresentAdapter(getContext(), R.layout.item_goodsenquiry_present, list);
        mGift_rv.setAdapter(giftAdapter);
    }

    @OnClick({R.id.goodsEnquiry_address_rl, R.id.goodsEnquiry_giftMore_tv, R.id.goodsEnquiry_discountMore_tv})
    @Override
    public void viewsClick(View view) {
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
                CommonUtils.startAct(getActivity(), PresentListActivity.class);
                break;
            case R.id.goodsEnquiry_discountMore_tv:
                //折扣优惠
                CommonUtils.startAct(getActivity(), DiscountListActivity.class);
                break;
        }
    }

    /**
     * 标题设置
     */
    private void TitleSet() {

    }

    /**
     * 百度定位
     */
    private void BaiduLocation() {
        MyLocationListener myListener = new MyLocationListener();
        mLocationClient = new LocationClient(getContext());
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setAddrType("all");
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);
    }

    /**
     * 轮播
     */
    private void BannerData() {
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        // 为XBanner绑定数据
        mXBanner.setData(imgesUrl, null);//第二个参数为提示文字资源集合
        // XBanner适配数据
        mXBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效，选择一个即可，总的大概就这么多效果啦，欢迎使用
        mXBanner.setPageTransformer(Transformer.Default);         //横向移动
        mXBanner.setPageTransformer(Transformer.Alpha);           //渐变，效果不明显
        mXBanner.setPageTransformer(Transformer.Rotate);          //单页旋转
        mXBanner.setPageTransformer(Transformer.Cube);            //立体旋转
        mXBanner.setPageTransformer(Transformer.Flip);            // 反转效果
        mXBanner.setPageTransformer(Transformer.Accordion);      //三角换页
        mXBanner.setPageTransformer(Transformer.ZoomFade);       // 缩小本页，同时放大另一页
        mXBanner.setPageTransformer(Transformer.ZoomCenter);     //本页缩小一点，另一页就放大
        mXBanner.setPageTransformer(Transformer.ZoomStack);      // 本页和下页同事缩小和放大
        mXBanner.setPageTransformer(Transformer.Stack);          //本页和下页同时左移
        mXBanner.setPageTransformer(Transformer.Depth);          //本页左移，下页从后面出来
        mXBanner.setPageTransformer(Transformer.Zoom);           //本页刚左移，下页就在后面
        // 设置XBanner页面切换的时间，即动画时长
        mXBanner.setPageChangeDuration(1000);
        mXBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                ToastUtil.toast("你点击了第" + position + "图片");
                Logger.d(position);
            }
        });
        //加载广告图片
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

    @Override
    public void onStart() {
        super.onStart();
        Logger.d("开始定位");
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始定位
        mLocationClient.start();
        //开始轮播
        mXBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mXBanner.stopAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        //结束定位
        mLocationClient.stop();
        Logger.d("暂停定位");
    }
}

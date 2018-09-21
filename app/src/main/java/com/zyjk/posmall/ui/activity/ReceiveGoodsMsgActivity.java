package com.zyjk.posmall.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseActivity;
import com.zyjk.posmall.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/8.
 * 填写收货信息
 */

public class ReceiveGoodsMsgActivity extends BaseActivity {
    private CityPicker mCP;       // 城市选择

    @BindView(R.id.aty_receiveGoodsMsg_area_tv)
    TextView area_tv;

    @Override
    protected int getContentView() {
        return R.layout.activity_receivegoodsmsg;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.titleBar_left_ll,R.id.aty_receiveGoodsMsg_area_rl, R.id.aty_receiveGoodsMsg_save_tv})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_ll:
                finish();
                break;
            case R.id.aty_receiveGoodsMsg_area_rl:
                //地区选择
                mCityPicher();
                mCP.show();
                break;
            case R.id.aty_receiveGoodsMsg_save_tv:
                //保存

                break;
        }
    }

    /**
     * 城市选择
     */
    private void mCityPicher() {
        mCP = new CityPicker.Builder(this).textSize(20)
                //地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#0CB6CA").titleTextColor("#000000").backgroundPop(0xa0000000).confirTextColor("#000000").cancelTextColor("#000000").province("xx省").city("xx市").district("xx区")
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
                area_tv.setText(province + "-" + city + "-" + district);  // 设置返回结果
            }

            @Override
            public void onCancel() {
                ToastUtil.toast("ss");
            }
        });
    }
}

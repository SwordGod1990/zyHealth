package com.zyjk.posmall.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zyjk.posmall.R;
import com.zyjk.posmall.adapter.SlidePagerAdapter;
import com.zyjk.posmall.base.BaseActivity;
import com.zyjk.posmall.utils.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sword God on 2018/8/28.
 * 引导页
 */

public class GuideActivity extends BaseActivity {

    private ArrayList<View> mList;
    private ImageView[] mImageViews;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.btn_im_exp)
    Button btnImExp;
    @BindView(R.id.llyt_dots)
    LinearLayout layoutDots;

    @Override
    protected int getContentView() {
        return R.layout.activity_guide;
    }

    @Override
    public void initViews() {
        LayoutInflater inflater = getLayoutInflater();
        mList = new ArrayList<>();
        mList.add(inflater.inflate(R.layout.view_guide_first, null));
        mList.add(inflater.inflate(R.layout.view_guide_two, null));
        mList.add(inflater.inflate(R.layout.view_guide_third, null));

        //底部点实现
        mImageViews = new ImageView[mList.size()];

        for (int i = 0; i < mList.size(); i++) {
            mImageViews[i] = new ImageView(GuideActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            // 设置边界
            params.setMargins(7, 10, 7, 10);
            mImageViews[i].setLayoutParams(params);
            if (0 == i) {
                mImageViews[i].setBackgroundResource(R.drawable.guide_circular_selected);
            } else {
                mImageViews[i].setBackgroundResource(R.drawable.guide_circular_selected_focus);
            }
            layoutDots.addView(mImageViews[i]);
        }
        viewPager.setAdapter(new SlidePagerAdapter(mList, GuideActivity.this));
        // 绑定回调
        viewPager.addOnPageChangeListener(new onPageChangeListener());
//        viewPager.setCurrentItem(0);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_im_exp})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.btn_im_exp:
                CommonUtils.startAct(getApplicationContext(), MainActivity.class);
                break;
        }
    }

    private class onPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 更新小圆点图标
            for (int i = 0; i < mList.size(); i++) {
                if (position == i) {
                    mImageViews[i].setBackgroundResource(R.drawable.guide_circular_selected);
                } else {
                    mImageViews[i].setBackgroundResource(R.drawable.guide_circular_selected_focus);
                }
            }
            // 滑动到最后pager时显示“立刻体验”按钮并监听
            if (position == mList.size() - 1) {
//                onButtonClick();
                btnImExp.setVisibility(View.VISIBLE);
            } else {
                btnImExp.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}

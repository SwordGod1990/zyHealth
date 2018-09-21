package com.zyjk.posmall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zyjk.posmall.ui.activity.GuideActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/4.
 */

public class SlidePagerAdapter extends PagerAdapter {

    private ArrayList<View> mList;
    private Context context;

    public SlidePagerAdapter(ArrayList<View> mList, GuideActivity guideActivity) {
        this.mList = mList;
        this.context = guideActivity;
    }

    @Override
    public int getCount() {
        // 返回页面数目实现有限滑动效果
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mList.get(position), 0);
        return mList.get(position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        // 注销父类销毁item的方法，因为此方法并不是使用此方法
//        super.destroyItem(container, position, object);
        ((ViewPager) container).removeView(mList.get(position));
    }
}

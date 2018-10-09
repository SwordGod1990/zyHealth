package com.zyjk.posmall.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.tools.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sword God on 2018/9/8.
 * 首页（折扣）
 */

public class HomeDiscountAdapter extends CommonAdapter<String> {
    private Context context;
    private List<String> list;

    public HomeDiscountAdapter(Context context, int item_goodsenquiry_gift, ArrayList<String> list) {
        super(context, item_goodsenquiry_gift, list);
        this.context = context;
        this.list = list;
    }


    @Override
    protected void convert(final ViewHolder holder, String s, int position) {
        holder.setText(R.id.item_goodsEnquiryDiscount_name_tv, s);
        Glide.with(context).load(R.drawable.rectangle).into(holder.<ImageView>getView(R.id.item_goodsEnquiryDiscount_iv));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到折扣优惠详情页
                CommonUtils.startAct(context, CommonFragmentActivity.FRAGMENT_DISCOUNT_DETAILS);
            }
        });
    }
}

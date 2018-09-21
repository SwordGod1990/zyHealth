package com.zyjk.posmall.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 * 折扣优惠
 */

public class DiscountListAdapter extends CommonAdapter<String> {
    private Context context;
    private List<String> list;
    private MyClickListener mListener;

    public DiscountListAdapter(Context context, int layoutId, List<String> datas, MyClickListener listener) {
        super(context, layoutId, datas);
        this.context = context;
        this.list = list;
        this.mListener = listener;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.item_discountList_name_tv, s);
        holder.getView(R.id.item_discountList_addCar_rl).setTag(position - 1);
        holder.getView(R.id.item_discountList_addCar_rl).setOnClickListener(mListener);
        Glide.with(context).load(R.drawable.rectangle).into(holder.<ImageView>getView(R.id.item_discountList_icon_iv));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到折扣优惠详情页
                CommonUtils.startAct(context, CommonFragmentActivity.FRAGMENT_DISCOUNT_DETAILS);
            }
        });
    }

    public static abstract class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            myOnClick((int) v.getTag(), v);
        }

        protected abstract void myOnClick(int tag, View v);
    }
}

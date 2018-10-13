package com.zyjk.posmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.tools.CommonUtils;

import java.util.List;

/**
 * Created by Sword God on 2018/9/12.
 * 满赠优惠
 */

public class PresentListAdapter extends CommonAdapter<String> {
    private Context context;
    private List<String> list;
    private MyClickListener mListener;


    public PresentListAdapter(Context context, int layoutId, List<String> list, MyClickListener listener) {
        super(context, layoutId, list);
        this.context = context;
        this.list = list;
        this.mListener = listener;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.item_presentList_name_tv, s);
        Glide.with(context).load(R.drawable.rectangle).into(holder.<ImageView>getView(R.id.item_presentList_icon_iv));
        holder.getView(R.id.item_presentList_addCar_rl).setTag(position - 1);
        holder.getView(R.id.item_presentList_addCar_rl).setOnClickListener(mListener);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到满赠详情页
                CommonUtils.startAct(context, CommonFragmentActivity.FRAGMENT_PRESENT_DETAILS);
            }
        });

    }

    /**
     * 用于回调的抽象类
     */
    public static abstract class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            myOnClick((int) v.getTag(), v);
            Log.i("------TAG-------", "onClick: " + v);

        }

        protected abstract void myOnClick(int tag, View v);

    }
}

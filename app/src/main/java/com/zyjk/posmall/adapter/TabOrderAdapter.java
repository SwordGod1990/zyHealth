package com.zyjk.posmall.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.tools.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sword God on 2018/9/13.
 */

public class TabOrderAdapter extends CommonAdapter<String> {
    private Context context;
    private List<String> list;
    private MyClickListener mListener;

    public TabOrderAdapter(FragmentActivity activity, int item_pay, ArrayList<String> list, MyClickListener listener) {
        super(activity, item_pay, list);
        this.context = activity;
        this.list = list;
        this.mListener = listener;
    }

    @Override
    protected void convert(final ViewHolder holder, String s, final int position) {
        holder.setText(R.id.item_tabOrder_company_tv, s);
        holder.getView(R.id.item_tabOrder_pay_tv).setTag(position - 1);
        holder.getView(R.id.item_tabOrder_pay_tv).setOnClickListener(mListener);
        //item监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //跳转
                CommonUtils.startAct(context, CommonFragmentActivity.FRAGMENT_SEND_ORDER_DETAILS);
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

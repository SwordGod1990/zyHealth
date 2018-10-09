package com.zyjk.posmall.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyjk.posmall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sword God on 2018/9/13.
 */

public class TabOrderDrugsAdapter extends CommonAdapter<String> {
    private Context context;
    private List<String> list;
    private String type;

    public TabOrderDrugsAdapter(FragmentActivity activity, int item_pay, ArrayList<String> list, String type) {
        super(activity, item_pay, list);
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.item_tabOrder_company_tv, s);
        holder.getView(R.id.item_tabOrder_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去支付
            }
        });
    }
}

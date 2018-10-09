package com.zyjk.posmall.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyjk.posmall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Sword God
 * e-mail : 156690858@qq.com
 * date   : 2018/9/3015:05
 * desc   :
 * version: 1.0
 */

public class SendAdapter extends CommonAdapter<String> {
    private Context context;
    private List<String> list;

    public SendAdapter(Context context, int item_goodsenquiry_gift, ArrayList<String> list) {
        super(context, item_goodsenquiry_gift, list);
        this.context = context;
        this.list = list;
    }


    @Override
    protected void convert(final ViewHolder holder, String s, final int position) {
        holder.setText(R.id.item_send_name_tv, s);
    }
}

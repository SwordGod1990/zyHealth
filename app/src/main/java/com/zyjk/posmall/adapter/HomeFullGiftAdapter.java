package com.zyjk.posmall.adapter;

import android.content.Context;
import android.view.View;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.CommonFragmentActivity;
import com.zyjk.posmall.tools.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sword God on 2018/9/11.
 * 首页（满赠）
 */

public class HomeFullGiftAdapter extends CommonAdapter<String> {

    private Context context;
    private List<String> list;

    public HomeFullGiftAdapter(Context context, int item_goodsenquiry_gift, ArrayList<String> list) {
        super(context, item_goodsenquiry_gift, list);
        this.context = context;
        this.list = list;
    }


    @Override
    protected void convert(final ViewHolder holder, String s, final int position) {
        holder.setText(R.id.item_goodsEnquiryGift_name_tv, s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到满赠详情页
                CommonUtils.startAct(context, CommonFragmentActivity.FRAGMENT_GIFT_DETAILS);
            }
        });
    }
}

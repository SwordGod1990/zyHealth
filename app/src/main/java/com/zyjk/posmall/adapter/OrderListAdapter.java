package com.zyjk.posmall.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyjk.posmall.R;
import com.zyjk.posmall.bean.FoodModel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Sword God on 2018/9/12.
 */

public class OrderListAdapter extends CommonAdapter<FoodModel> {
    private Context context;
    private List<FoodModel> datas;
    private ScavengersActionCallback callback;

    public OrderListAdapter(Context context, int layoutId, List<FoodModel> datas, ScavengersActionCallback callback) {
        super(context, layoutId, datas);
        this.context = context;
        this.datas = datas;
        this.callback = callback;
    }

    @Override
    protected void convert(ViewHolder holder, FoodModel foodModel, int position) {
        holder.setText(R.id.item_orderList_goodsNum_tv, foodModel.getNum() + "");
        holder.setText(R.id.item_orderList_money_tv, foodModel.getPrice() + "");
        holder.setText(R.id.item_orderList_factoryContent_tv, foodModel.getDescription());
        holder.setText(R.id.item_orderList_drugName_tv, foodModel.getName());
        TextView drugName = holder.getView(R.id.item_orderList_drugName_tv);
        //-----------------------------字体过长尾部以...显示
        drugName.setEllipsize(TextUtils.TruncateAt.END);
        drugName.setMaxWidth(500);
        drugName.setSingleLine(true);
        drugName.setOnClickListener(new View.OnClickListener() {
            Boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    drugName.setEllipsize(null);// 展开
                    drugName.setSingleLine(flag);
                } else {
                    flag = true;
                    drugName.setEllipsize(TextUtils.TruncateAt.END); // 收缩
                    drugName.setSingleLine(flag);
                }
            }
        });

        /**
         * ------------------------ 删除条目 ------------------------
         */
        holder.getView(R.id.item_orderList_delete_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position - 1);
                Log.i("----TAG----", "onClick: " + position);
                //
//                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff( new );
            }
        });
        /**
         * ------------------------ 购物车（加）------------------------
         */
        holder.getView(R.id.item_orderList_AddGood_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback == null) return;
                callback.addAction(v, position - 1);
            }
        });
        /**
         * ------------------------ 购物车（减）------------------------
         */
        holder.getView(R.id.item_orderList_reduceGood_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback == null || 0 == foodModel.getNum())
                    return;
                callback.reduceGood(position - 1);
            }
        });
    }

    /**
     * ------------------------ 接口 ------------------------
     */
    public interface ScavengersActionCallback {
        //加
        void addAction(View view, int position);

        //减
        void reduceGood(int position);
    }

    /**
     * ------------------------ 单位精度计算(价格) ------------------------
     *
     * @param price
     * @return
     */
    public String priceResult(double price) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(new BigDecimal(price));

    }

    /**
     * ------------------------ 删除item数据 ------------------------
     *
     * @param position
     */
    public void removeData(int position) {
        datas.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}

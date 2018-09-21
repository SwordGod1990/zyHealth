package com.zyjk.posmall.page;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zyjk.posmall.R;

/**
 * Created by Sword God on 2018/8/30.
 * （加载中、无网络、无数据）界面提示
 */

public class LoadPage extends FrameLayout {
    public static final int STATE_LOADING = 0; //加载中
    public static final int STATE_NO_NET = 1;  //无网络
    public static final int STATE_NO_DATA = 2; //无数据
    public static final int STATE_HIDE = 3;    //隐藏
    private View loadingPage;                   //加载中
    private View noNetPage;                     //无网络
    private View noDataPage;                    //无数据
    private GetDataListener getDataListener;
    private TextView noData_tv;                 //无数据提示

    public interface GetDataListener {
        void onGetData();
    }

    public void setGetDataListener(GetDataListener getDataListener) {
        this.getDataListener = getDataListener;
    }

    public LoadPage(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     *
     */
    private void init() {
        //使用isInEditMode解决可视化编辑器无法识别自定义控件的问题
        if (isInEditMode()) return;
        loadingPage = createLoadingPage();  //加载中页面
        if (null != loadingPage) {
            this.addView(loadingPage, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        noNetPage = createNoNetPage();      //无网络页面
        if (null != noNetPage) {
            this.addView(noNetPage, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        noDataPage = createNoDataPage();    //无数据页面
        if (null != noDataPage) {
            noData_tv = (TextView) noDataPage.findViewById(R.id.noData_tv);
            this.addView(noDataPage, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    /**
     * 创建加载中页面
     *
     * @return
     */
    private View createLoadingPage() {
        View view = View.inflate(getContext(), R.layout.view_loading, null);
        ProgressBar loading_progressBar_pb = (ProgressBar) view.findViewById(R.id.loading_progressBar_pb);
//		loading_progressBar_pb.setIndeterminateDrawable(getContext().getResources().getDrawable(R.drawable.loading));
        return view;
    }

    /**
     * 创建无网络页面
     *
     * @return
     */
    private View createNoNetPage() {
        View view = View.inflate(getContext(), R.layout.view_nonetwork,
                null);
        LinearLayout noNetwork_ll = (LinearLayout) view.findViewById(R.id.noNetwork_ll);
        noNetwork_ll.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (getDataListener != null) {
                    getDataListener.onGetData();
//                    Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
//                    getContext().startActivity(intent);
                }
            }
        });
        return view;
    }

    /**
     * 创建无数据页面
     *
     * @return
     */
    private View createNoDataPage() {
        View view = View.inflate(getContext(), R.layout.view_nodata, null);
        return view;
    }

    /**
     * 根据不同的状态显示不同的界面
     */
    public void switchPage(int sign) {
        //0：加载中显示  1:无网络显示  2:无数据显示  3:全隐藏
        switch (sign) {
            case STATE_LOADING:
                if (null != loadingPage) {
                    loadingPage.setVisibility(VISIBLE);
                }
                if (noNetPage != null) {
                    noNetPage.setVisibility(View.GONE);
                }
                if (noDataPage != null) {
                    noDataPage.setVisibility(View.GONE);
                }
                break;
            case STATE_NO_NET:
                if (null != loadingPage) {
                    loadingPage.setVisibility(GONE);
                }
                if (noNetPage != null) {
                    noNetPage.setVisibility(View.VISIBLE);
                }
                if (noDataPage != null) {
                    noDataPage.setVisibility(View.GONE);
                }
                break;
            case STATE_NO_DATA:
                if (null != loadingPage) {
                    loadingPage.setVisibility(GONE);
                }
                if (noNetPage != null) {
                    noNetPage.setVisibility(View.GONE);
                }
                if (noDataPage != null) {
                    noDataPage.setVisibility(View.VISIBLE);
                }
                break;
            case STATE_HIDE:
                if (null != loadingPage) {
                    loadingPage.setVisibility(VISIBLE);
                }
                if (noNetPage != null) {
                    noNetPage.setVisibility(View.GONE);
                }
                if (noDataPage != null) {
                    noDataPage.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 无数据提示
     *
     * @param text
     */
    public void setNoDataText(String text) {
        if (noData_tv != null) {
            noData_tv.setText(text);
        }
    }
}

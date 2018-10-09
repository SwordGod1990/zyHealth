package com.zyjk.posmall.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyjk.posmall.R;

/**
 * author : Sword God
 * e-mail : 156690858@qq.com
 * date   : 2018/9/2814:14
 * desc   :
 * version: 1.0
 */

public class TitleBar extends FrameLayout {
    private RelativeLayout mRootLayout;
    private TextView mTvTitle;
    private ImageView mIvBack;
    private TextView mTvRightText;
    private ImageView mIvRightIcon;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.pub_toolbar, this);
        mRootLayout = (RelativeLayout) findViewById(R.id.root_layout);
        mTvTitle = (TextView) mRootLayout.findViewById(R.id.title_text);
        mIvBack = (ImageView) mRootLayout.findViewById(R.id.title_back);
        mTvRightText = (TextView) mRootLayout.findViewById(R.id.right_text);
        mIvRightIcon = (ImageView) mRootLayout.findViewById(R.id.right_icon);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        String title = array.getString(R.styleable.TitleBar_title);
        String rightText = array.getString(R.styleable.TitleBar_rightText);
        int rightIcon = array.getResourceId(R.styleable.TitleBar_rightIcon, 0);
        boolean backVisible = array.getBoolean(R.styleable.TitleBar_backVisible, true);
        boolean rightTextVisible = array.getBoolean(R.styleable.TitleBar_rightTextVisible, false);
        boolean rightIconVisible = array.getBoolean(R.styleable.TitleBar_rightIconVisible, false);
        array.recycle();

        setTitle(title);
        setRightText(rightText);
        setRightIcon(rightIcon);
        setBackVisible(backVisible);
        setRightTextVisible(rightTextVisible);
        setRightIconVisible(rightIconVisible);
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public TitleBar setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
        return this;
    }

    /**
     * 设置右边文字
     *
     * @param rightText
     */
    private void setRightText(String rightText) {
        if (!TextUtils.isEmpty(rightText)) {
            mTvRightText.setText(rightText);
        }
    }

    /**
     * 设置右边图片
     *
     * @param rightIcon
     */
    private void setRightIcon(int rightIcon) {
        if (rightIcon != 0) {
            mIvRightIcon.setImageResource(rightIcon);
        }
    }

    /**
     * 关闭Activity
     *
     * @param activity
     * @return
     */
    public TitleBar setBackFinish(final Activity activity) {
        if (activity != null)
            mIvBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        return this;
    }

    /**
     * 右边文字监听
     *
     * @param onClickListener
     * @return
     */
    public TitleBar setRightTextClickListener(OnClickListener onClickListener) {
        mTvRightText.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 右边图片监听
     *
     * @param onClickListener
     * @return
     */
    public TitleBar setRightIconClickListener(OnClickListener onClickListener) {
        mIvRightIcon.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 返回键是否显示
     *
     * @param backVisible
     */
    private void setBackVisible(boolean backVisible) {
        if (backVisible) {
            if (mIvBack.getVisibility() != VISIBLE) {
                mIvBack.setVisibility(VISIBLE);
            }
        } else {
            if (mIvBack.getVisibility() != GONE) {
                mIvBack.setVisibility(GONE);
            }
        }
    }

    /**
     * 右边文字是否显示
     *
     * @param rightTextVisible
     */
    public void setRightTextVisible(boolean rightTextVisible) {
        if (rightTextVisible) {
            if (mTvRightText.getVisibility() != VISIBLE) {
                mTvRightText.setVisibility(VISIBLE);
            }
        } else {
            if (mTvRightText.getVisibility() != GONE) {
                mTvRightText.setVisibility(GONE);
            }
        }
    }

    /**
     * 右边图片是否显示
     *
     * @param rightIconVisible
     */
    public void setRightIconVisible(boolean rightIconVisible) {
        if (rightIconVisible) {
            if (mIvRightIcon.getVisibility() != VISIBLE) {
                mIvRightIcon.setVisibility(VISIBLE);
            }
        } else {
            if (mIvRightIcon.getVisibility() != GONE) {
                mIvRightIcon.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置背景颜色
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        mRootLayout.setBackgroundColor(color);
    }

}

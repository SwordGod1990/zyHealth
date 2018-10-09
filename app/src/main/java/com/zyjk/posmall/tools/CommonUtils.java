package com.zyjk.posmall.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.zyjk.posmall.MyApplication;
import com.zyjk.posmall.ui.activity.LoginActivity;
import com.zyjk.posmall.base.CommonFragmentActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sword God on 2018/8/28.
 */

public class CommonUtils {
    /**
     * 空判断
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0 || "null".equalsIgnoreCase(str.toString()))
            return true;
        else
            return false;
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 启动对应的功能模块
     */
    public static void startAct(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 进入单个Fragment
     *
     * @param target 目标Fragment
     */
    public static void startAct(Context context, int target) {
        Intent intent = new Intent(context, CommonFragmentActivity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(CommonFragmentActivity.TARGET, target);
        context.startActivity(intent);
    }

    /**
     * 应用是否安装
     */
    public static boolean isAppInstalled(Context mActivity, String packageName) {
        try {
            PackageManager manager = mActivity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (info != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     * @param flag     true    隐藏
     */
    public static void hideInputMode(Activity activity, boolean flag) {
        if (flag) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } else {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }
    }

    public static void showKeyboard(Context mcontext, View view) {
        InputMethodManager im = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, 0);
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String getChannel() {
        String ret = "";
        try {
            ret = MyApplication.getInstance().getPackageManager()
                    .getApplicationInfo(MyApplication.getInstance().getPackageName(),
                            PackageManager.GET_META_DATA).metaData.getString("UMENG_CHANNEL");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.e("M_CHANNEL: " + ret);
        return ret;
    }

    //当前登录状态
    public static boolean isLogin() {
        boolean result = false;

        String loginKey = MyApplication.getInstance().getLoginKey();
        if (!isEmpty(loginKey)) {
            return true;
        }

        return result;
    }

    //未登录，直接跳转登录
    public static boolean checkLogin() {
        boolean result = false;

        String loginKey = MyApplication.getInstance().getLoginKey();
        if (!isEmpty(loginKey)) {
            return true;
        } else {
            CommonUtils.startAct(MyApplication.getInstance(), LoginActivity.class);
        }

        return result;
    }

    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile("(\\w|((\\w+.)+\\w+))+@(\\w+.)+\\w+"); //简单匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static int getScreenWidth(Context ctx) {
        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取货币符号
     */
//    public static String getCurrencySign() {
//        return MyApplication.getInstance().getString(R.string.text_rmb);
//    }

//    public static void enterGoodsDetails(String goodsId) {
//        if (isEmpty(goodsId)) return;
//
//        Intent intent = new Intent(MyApplication.getInstance(), GoodsDetailsWebView.class);
//        intent.putExtra("url", URL_GOODS_DETAILS + goodsId);
//        intent.putExtra("goodsId", goodsId);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        MyApplication.getInstance().startActivity(intent);
//    }
//
//    public static void go2pay(String orderId, String orderSn, String amount) {
//        Intent intent = new Intent(MyApplication.getInstance(), PayActivity.class);
//        intent.putExtra("orderIds", orderId);
//        intent.putExtra("orderSns", orderSn);
//        intent.putExtra("amount", amount);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        MyApplication.getInstance().startActivity(intent);
//    }
    public static String numRound(String num, int decimal) {
        if (CommonUtils.isEmpty(num) || !Pattern.compile("-?[0-9]+.?[0-9]+").matcher(num).matches()) {
            num = "0";
        }
        BigDecimal b = new BigDecimal(num);
        num = b.setScale(decimal, RoundingMode.HALF_UP).toString();
        return num;
    }

}

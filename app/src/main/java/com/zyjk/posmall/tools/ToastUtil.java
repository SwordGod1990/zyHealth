package com.zyjk.posmall.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.zyjk.posmall.MyApplication;


/**
 * Created by Sword God on 2018/8/28.
 * 吐司工具类
 */

public class ToastUtil {
    private static Toast mToast;

    static {
        mToast = Toast.makeText(MyApplication.getInstance(), "", Toast.LENGTH_SHORT);
    }

    /**
     * @deprecated
     */
    public static void showLongToast(Context context, final String msg) {
        MyApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                mToast.setText(msg);
                mToast.setDuration(Toast.LENGTH_LONG);
                mToast.show();
            }
        });
    }

    /**
     * @deprecated
     */
    public static void showShortToast(Context context, final String msg) {
        MyApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                mToast.setText(msg);
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    public static void toast(String msg) {
        show(msg);
    }

    public static void toast(int id) {
        show(MyApplication.getInstance().getString(id));
    }

    private static void show(final String msg) {
        MyApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                mToast.setText(msg);
                mToast.show();
            }
        });
    }

    /**
     * 设计原理：在Toast显示消失之前，再次调用Toast.show()进行接力。
     */
    public static class CustomToast {
        private static Toast toast = null;
        private static Handler handler = null;
        private static boolean isToastRunning = false; // 防止重复点击
        private static Runnable toastShowTask = new Runnable() {
            public void run() {
                toast.show();
                handler.postDelayed(toastShowTask, 3300);// 若此处为4s，则会断断续续显示
            }
        };

        /**
         * 显示TOAST
         *
         * @param context 上下文
         * @param msg     提示消息
         * @param time    显示时长
         */
        @SuppressLint("ShowToast")
        public static void show(Context context, String msg, final long time) {
            if (isToastRunning) {
                return;
            }
            handler = new Handler(context.getMainLooper());
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            // 显示
            handler.post(toastShowTask);
            isToastRunning = true;
            // 取消
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 删除Handler队列中的仍处理等待的消息
                    handler.removeCallbacks(toastShowTask);
                    // 取消仍在显示的Toast
                    toast.cancel();
                    isToastRunning = false;
                }
            }, time);
        }

        @SuppressLint("ShowToast")
        public static void showLongLong(Context context, String msg) {
            show(context, msg, 6000);
        }
    }
}

/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zyjk.posmall.tools;

import android.text.TextUtils;
import android.util.Log;

/**
 * Log工具，类似android.util.Log。
 * tag自动产生，格式: customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)。
 * <p/>
 * Author: x
 * Date: 13-7-24
 * Time: 下午12:23
 */
public class LogUtils {

    private static String customTagPrefix = "";
    private static final int TYPE_V = 0;
    private static final int TYPE_D = 1;
    private static final int TYPE_I = 2;
    private static final int TYPE_W = 3;
    private static final int TYPE_E = 4;
    private static final int TYPE_WTF = 5;

    private LogUtils() {
    }

    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;

    public static void debug(boolean debug) {
        allowD = debug;
        allowE = debug;
        allowI = debug;
        allowV = debug;
        allowW = debug;
        allowWtf = debug;
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static CustomLogger customLogger;

    public interface CustomLogger {
        void d(String tag, String content);

        void d(String tag, String content, Throwable tr);

        void e(String tag, String content);

        void e(String tag, String content, Throwable tr);

        void i(String tag, String content);

        void i(String tag, String content, Throwable tr);

        void v(String tag, String content);

        void v(String tag, String content, Throwable tr);

        void w(String tag, String content);

        void w(String tag, String content, Throwable tr);

        void w(String tag, Throwable tr);

        void wtf(String tag, String content);

        void wtf(String tag, String content, Throwable tr);

        void wtf(String tag, Throwable tr);
    }


    public static void d(String content) {
        if (!allowD) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.d(tag, content);
        } else {
            printLog(TYPE_E, tag, content, null);
        }
    }

    public static void d(String content, Throwable tr) {
        if (!allowD) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.d(tag, content, tr);
        } else {
            printLog(TYPE_D, tag, content, tr);
        }
    }

    public static void e(String content) {
        if (!allowE) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.e(tag, content);
        } else {
            printLog(TYPE_E, tag, content, null);

        }
    }

    public static void e(String tag, String content) {
        if (!allowE) {
            return;
        }

        if (customLogger != null) {
            customLogger.e(tag, content);
        } else {
            printLog(TYPE_E, tag, content, null);
        }
    }

    public static void e(String content, Throwable tr) {
        if (!allowE) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.e(tag, content, tr);
        } else {
            printLog(TYPE_E, tag, content, tr);

        }
    }

    public static void i(String content) {
        if (!allowI) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.i(tag, content);
        } else {
            printLog(TYPE_I, tag, content, null);

        }
    }

    public static void i(String content, Throwable tr) {
        if (!allowI) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.i(tag, content, tr);
        } else {
            printLog(TYPE_I, tag, content, tr);
        }
    }

    public static void v(String content) {
        if (!allowV) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.v(tag, content);
        } else {
            printLog(TYPE_V, tag, content, null);
        }
    }

    public static void v(String content, Throwable tr) {
        if (!allowV) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.v(tag, content, tr);
        } else {
            printLog(TYPE_V, tag, content, tr);

        }
    }

    public static void w(String content) {
        if (!allowW) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, content);
        } else {
            printLog(TYPE_W, tag, content, null);

        }
    }

    public static void w(String content, Throwable tr) {
        if (!allowW) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, content, tr);
        } else {
            printLog(TYPE_W, tag, content, tr);

        }
    }

    public static void w(Throwable tr) {
        if (!allowW) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, tr);
        } else {

            Log.w(tag, tr);
        }
    }


    public static void wtf(String content) {
        if (!allowWtf) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, content);
        } else {
            printLog(TYPE_WTF, tag, content, null);

        }
    }

    public static void wtf(String content, Throwable tr) {
        if (!allowWtf) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, content, tr);
        } else {
            printLog(TYPE_WTF, tag, content, tr);
            Log.wtf(tag, content, tr);
        }
    }

    public static void wtf(Throwable tr) {
        if (!allowWtf) {
            return;
        }
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, tr);
        } else {

            Log.wtf(tag, tr);
        }
    }

    private static void printLog(int type, String tag, String content, Throwable throwable) {
        int maxLogSize = 1000;
        for (int i = 0; i <= content.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > content.length() ? content.length() : end;
            switch (type) {
                case TYPE_V:
                    Log.v(tag, content.substring(start, end), throwable);

                    break;
                case TYPE_D:
                    Log.d(tag, content.substring(start, end), throwable);

                    break;
                case TYPE_I:
                    Log.i(tag, content.substring(start, end), throwable);

                    break;
                case TYPE_W:
                    Log.w(tag, content.substring(start, end), throwable);

                    break;
                case TYPE_E:
                    Log.e(tag, content.substring(start, end), throwable);
                    break;
                case TYPE_WTF:
                    Log.wtf(tag, content.substring(start, end), throwable);
                    break;
                default:
                    break;
            }
        }

    }

    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];

    }
}

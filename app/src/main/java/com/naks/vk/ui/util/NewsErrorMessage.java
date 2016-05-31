package com.naks.vk.ui.util;

import android.content.Context;

import com.naks.vk.R;

public class NewsErrorMessage {

    public static String get(Throwable e, boolean pullToRefresh, Context c) {
        if (pullToRefresh) {
            return c.getString(R.string.error_news);
        } else {
            return c.getString(R.string.error_news_retry);
        }
    }
}

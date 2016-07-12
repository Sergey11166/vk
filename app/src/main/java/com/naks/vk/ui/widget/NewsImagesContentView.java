package com.naks.vk.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class NewsImagesContentView extends RelativeLayout {
    public NewsImagesContentView(Context context) {
        super(context);
    }

    public NewsImagesContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsImagesContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NewsImagesContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

package com.naks.vk.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.vk.sdk.api.model.VKApiPhoto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsImagesContentView extends RelativeLayout {

    private List<ImageView> views;
    private float scale;

    public NewsImagesContentView(Context context) {
        super(context);
        init();
    }

    public NewsImagesContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewsImagesContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("unused")
    public NewsImagesContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        views = new ArrayList<>();
        scale = getContext().getResources().getDisplayMetrics().density;
    }

    public void setImages(List<VKApiPhoto> images) {
        if (images.size() == 0) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        for (ImageView view : views) removeView(view);
        views.clear();
        switch (images.size()) {
            case 1:
                VKApiPhoto photo = images.get(0);
                ImageView imageView = new ImageView(getContext());
                int width = (int) (350 * scale);
                int height = width * photo.height / photo.width;
                LayoutParams params = new LayoutParams(width, height);
                addView(imageView, params);
                views.add(imageView);
                Glide.with(getContext())
                        .load(photo.photo_604)
                        .placeholder(new ColorDrawable(Color.LTGRAY))
                        .into(imageView);
                return;

            case 2:
                final int count2 = images.size();
                final LayoutParams[] params2 = new LayoutParams[count2];
                final int[] widths2 = new int[count2];
                final int[] heights2 = new int[count2];
                final ImageView[] views = new ImageView[count2];
                for(int i = 0; i< count2; i++) {
                    views[i] = new ImageView(getContext());
                    views[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
                    views[i].setId(View.generateViewId());
                }

                boolean isHorizontal = false;
                for (int i=0; i<count2; i++)
                    isHorizontal = images.get(i).height >= images.get(i).width;

                if (isHorizontal) {
                    int wSum = 0; for(int i = 0; i< count2; i++) wSum += images.get(i).width;
                    for(int i = 0; i< count2; i++)
                        widths2[i] = (int) (images.get(i).width * 350 * scale/ wSum);
                } else {
                    for(int i = 0; i< count2; i++) widths2[i] = (int) (350 * scale);
                }

                for (int i = 0; i< count2; i++) {
                    heights2[i] = widths2[i] * images.get(i).height / images.get(i).width;
                    params2[i] = new LayoutParams(widths2[i], heights2[i]);
                    if (i != 0) {
                        params2[i].addRule(isHorizontal ? RIGHT_OF : BELOW, views[i - 1].getId());
                        if (isHorizontal) params2[i].setMargins((int) (2 * scale), 0, 0, 0);
                        else params2[i].setMargins(0, (int) (2 * scale), 0, 0);
                    }
                    addView(views[i], params2[i]);
                    this.views.addAll(Arrays.asList(views));
                    Glide.with(getContext())
                            .load(images.get(i).photo_604)
                            .placeholder(new ColorDrawable(Color.LTGRAY))
                            .into(views[i]);
                }
                return;

            case 3:

                return;
            case 4:

                return;
            case 5:

                return;
            case 6:

                return;
            case 7:

                return;
            case 8:

                return;
            case 9:

                return;
            case 10:

        }
    }
}

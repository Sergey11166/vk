package com.naks.vk.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.naks.vk.R;
import com.vk.sdk.api.model.VKApiPhoto;

import java.util.ArrayList;
import java.util.List;

public class NewsImagesContentView extends RelativeLayout {

    List<ImageView> views;

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
    public NewsImagesContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        views = new ArrayList<>();
    }

    public void setImages(List<VKApiPhoto> images) {
        if (images.size() == 0) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        for (ImageView view : views) {
            ((ViewManager)view.getParent()).removeView(view);
        }
        views.clear();
        switch (images.size()) {
            case 1:
                ImageView imageView = new ImageView(getContext());
                LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                addView(imageView, params);
                views.add(imageView);
                VKApiPhoto photo = images.get(0);
                Bitmap bitmap = Bitmap.createBitmap(photo.width, photo.height, Bitmap.Config.RGB_565);
                bitmap.eraseColor(Color.LTGRAY);
                Glide.with(getContext())
                        .load(photo.photo_604)
                        .into(imageView);
                imageView.setImageBitmap(bitmap);
                imageView.setBackgroundColor(Color.GRAY);
                return;
            case 2:

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

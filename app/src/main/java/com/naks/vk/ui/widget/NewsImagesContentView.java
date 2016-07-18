package com.naks.vk.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.vk.sdk.api.model.VKApiPhoto;

import java.util.ArrayList;
import java.util.List;

public class NewsImagesContentView extends RelativeLayout {

    private static final int IMAGES_WIDTH = 350;
    private static final int MARGIN_BETWEEN_IMAGES = 2;

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
                allImagesToLine(images);
                return;
            case 2:
                allImagesToLine(images);
                return;
            case 3:
                handle3Images(images);
                return;
            case 4:
                handle4Or5Images(images);
                return;
            case 5:
                handle4Or5Images(images);
                return;
            case 6:
                handle6Or7Images(images);
                return;
            case 7:
                handle6Or7Images(images);
                return;
            case 8:
                handle8Or9Or10Images(images);
                return;
            case 9:
                handle8Or9Or10Images(images);
                return;
            case 10:
                handle8Or9Or10Images(images);
        }
    }

    private void allImagesToLine(List<VKApiPhoto> images) {
        final LayoutParams[] params = new LayoutParams[images.size()];
        for (int i=0; i<images.size(); i++) params[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(images.toArray(new VKApiPhoto[images.size()]),
                createViewsForImages(images.size()), params);
    }

    private void handle3Images(List<VKApiPhoto> images) {
        boolean isAllVertical = isAllVertical(images);
        if (isAllVertical) {
            allImagesToLine(images);
            return;
        }

        final boolean isFirstImageVertical = images.get(0).width < images.get(0).height;

        VKApiPhoto[] images1 = new VKApiPhoto[isFirstImageVertical ? 2 : 1];
        for (int i=0; i<images1.length; i++) images1[i] = images.get(i);
        ImageView[] views1 = createViewsForImages(isFirstImageVertical ? 2 : 1);
        LayoutParams[] params1 = new LayoutParams[isFirstImageVertical ? 2 : 1];
        for (int i=0; i<params1.length; i++) params1[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(images1, views1, params1);

        VKApiPhoto[] images2 = new VKApiPhoto[isFirstImageVertical ? 1 : 2];
        for (int i=0; i<images2.length; i++) images2[i] = images.get(i + (isFirstImageVertical ? 2 : 1));
        ImageView[] views2 = createViewsForImages(isFirstImageVertical ? 1 : 2);
        LayoutParams[] params2 = new LayoutParams[isFirstImageVertical ? 1 : 2];
        for (int i=0; i<params2.length; i++) {
            params2[i] = new LayoutParams(0, 0);
            params2[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params2[i].addRule(BELOW, views1[0].getId());
        }
        fillImagesToViewsInLine(images2, views2, params2);
    }

    private void handle4Or5Images(List<VKApiPhoto> images) {
        VKApiPhoto[] images1 = new VKApiPhoto[2];
        for (int i=0; i<images1.length; i++) images1[i] = images.get(i);
        ImageView[] views1 = createViewsForImages(2);
        LayoutParams[] params1 = new LayoutParams[2];
        for (int i=0; i<params1.length; i++) params1[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(images1, views1, params1);

        VKApiPhoto[] images2 = new VKApiPhoto[images.size() - 2];
        for (int i=0; i<images2.length; i++) images2[i] = images.get(i + 2);
        ImageView[] views2 = createViewsForImages(images.size() - 2);
        LayoutParams[] params2 = new LayoutParams[images.size() - 2];
        for (int i=0; i<params2.length; i++) {
            params2[i] = new LayoutParams(0, 0);
            params2[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params2[i].addRule(BELOW, views1[0].getId());
        }
        fillImagesToViewsInLine(images2, views2, params2);
    }

    private void handle6Or7Images(List<VKApiPhoto> images) {
        boolean isAllVertical = isAllVertical(images);

        final int countInTopLine = isAllVertical ? 3 : 2;
        VKApiPhoto[] images1 = new VKApiPhoto[countInTopLine];
        for (int i=0; i<images1.length; i++) images1[i] = images.get(i);
        ImageView[] views1 = createViewsForImages(countInTopLine);
        LayoutParams[] params1 = new LayoutParams[countInTopLine];
        for (int i=0; i<params1.length; i++) params1[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(images1, views1, params1);

        final int countMiddleLine = isAllVertical && images.size() == 7 ? 4 : countInTopLine;
        VKApiPhoto[] images2 = new VKApiPhoto[countMiddleLine];
        for (int i=0; i<images2.length; i++) images2[i] = images.get(i + countInTopLine);
        ImageView[] views2 = createViewsForImages(countMiddleLine);
        LayoutParams[] params2 = new LayoutParams[countMiddleLine];
        for (int i=0; i<params2.length; i++) {
            params2[i] = new LayoutParams(0, 0);
            params2[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params2[i].addRule(BELOW, views1[0].getId());
        }
        fillImagesToViewsInLine(images2, views2, params2);

        if (isAllVertical) return;
        VKApiPhoto[] images3 = new VKApiPhoto[images.size() - 4];
        for (int i=0; i<images3.length; i++) images3[i] = images.get(i + 4);
        ImageView[] views3 = createViewsForImages(images.size() - 4);
        LayoutParams[] params3 = new LayoutParams[images.size() - 4];
        for (int i=0; i<params3.length; i++) {
            params3[i] = new LayoutParams(0, 0);
            params3[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params3[i].addRule(BELOW, views2[0].getId());
        }
        fillImagesToViewsInLine(images3, views3, params3);
    }

    private void handle8Or9Or10Images(List<VKApiPhoto> images) {
        final int countInTopLine = images.size() == 8 ? 2 : 3;
        VKApiPhoto[] images1 = new VKApiPhoto[countInTopLine];
        for (int i=0; i<images1.length; i++) images1[i] = images.get(i);
        ImageView[] views1 = createViewsForImages(countInTopLine);
        LayoutParams[] params1 = new LayoutParams[countInTopLine];
        for (int i=0; i<params1.length; i++) params1[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(images1, views1, params1);

        VKApiPhoto[] images2 = new VKApiPhoto[3];
        for (int i=0; i<images2.length; i++) images2[i] = images.get(i + countInTopLine);
        ImageView[] views2 = createViewsForImages(3);
        LayoutParams[] params2 = new LayoutParams[3];
        for (int i=0; i<params2.length; i++) {
            params2[i] = new LayoutParams(0, 0);
            params2[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params2[i].addRule(BELOW, views1[0].getId());
        }
        fillImagesToViewsInLine(images2, views2, params2);

        VKApiPhoto[] images3 = new VKApiPhoto[images.size() - countInTopLine - 3];
        for (int i=0; i<images3.length; i++) images3[i] = images.get(i + countInTopLine + 3);
        ImageView[] views3 = createViewsForImages(images.size() - countInTopLine - 3);
        LayoutParams[] params3 = new LayoutParams[images.size() - countInTopLine - 3];
        for (int i=0; i<params3.length; i++) {
            params3[i] = new LayoutParams(0, 0);
            params3[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params3[i].addRule(BELOW, views2[0].getId());
        }
        fillImagesToViewsInLine(images3, views3, params3);
    }

    private void fillImagesToViewsInLine(VKApiPhoto[] images, ImageView[] views, LayoutParams[] params) {
        final int[] widths = new int[images.length];
        final int[] heights = new int[images.length];

        int[] widthNorm = new int[images.length];
        int widthSum = widthNorm[0] = images[0].width;
        for (int i=1; i<images.length; i++) {
            widthNorm[i] = images[0].height * images[i].width / images[i].height;
            widthSum += widthNorm[i];
        }

        for(int i = 0; i<images.length; i++) {
            widths[i] = (int) ((IMAGES_WIDTH * widthNorm[i] / widthSum) * scale);
            heights[i] = widths[0] * images[0].height / images[0].width;
        }

        for (int i = 0; i<images.length; i++) {
            params[i].width = widths[i];
            params[i].height = heights[i];
            if (i != 0) {
                params[i].addRule(RIGHT_OF, views[i - 1].getId());
                params[i].setMargins(
                        (int) (MARGIN_BETWEEN_IMAGES * scale),
                        params[i].topMargin,
                        params[i].rightMargin,
                        params[i].bottomMargin);
            }
            addView(views[i], params[i]);
            this.views.add(views[i]);
            Glide.with(getContext())
                    .load(images[i].photo_604)
                    .placeholder(new ColorDrawable(Color.LTGRAY))
                    .into(views[i]);
        }
    }

    private ImageView[] createViewsForImages(int size) {
        final ImageView[] views = new ImageView[size];
        for(int i = 0; i<size; i++) {
            views[i] = new ImageView(getContext());
            views[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            views[i].setId(View.generateViewId());
        }
        return views;
    }

    private static boolean isAllVertical(List<VKApiPhoto> images) {
        for (VKApiPhoto image : images) if (image.width > image.height) return false;
        return true;
    }
}

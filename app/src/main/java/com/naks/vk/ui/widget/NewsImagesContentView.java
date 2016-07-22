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
import com.vk.sdk.api.model.VKApiVideo;

import java.util.ArrayList;
import java.util.List;

import static com.vk.sdk.api.model.VKAttachments.VKApiAttachment;

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

    public void setImages(List<VKApiAttachment> attachments) {
        if (attachments.size() == 0) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        for (ImageView view : views) removeView(view);
        views.clear();
        switch (attachments.size()) {
            case 1:
                allImagesToLine(attachments);
                return;
            case 2:
                allImagesToLine(attachments);
                return;
            case 3:
                handle3Images(attachments);
                return;
            case 4:
                handle4Or5Images(attachments);
                return;
            case 5:
                handle4Or5Images(attachments);
                return;
            case 6:
                handle6Or7Images(attachments);
                return;
            case 7:
                handle6Or7Images(attachments);
                return;
            case 8:
                handle8Or9Or10Images(attachments);
                return;
            case 9:
                handle8Or9Or10Images(attachments);
                return;
            case 10:
                handle8Or9Or10Images(attachments);
        }
    }

    private void allImagesToLine(List<VKApiAttachment> attachments) {
        final LayoutParams[] params = new LayoutParams[attachments.size()];
        for (int i=0; i<attachments.size(); i++) params[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(attachments.toArray(new VKApiAttachment[attachments.size()]),
                createViewsForImages(attachments.size()), params);
    }
    private void handle3Images(List<VKApiAttachment> attachments) {
        boolean isAllVertical = isAllVertical(attachments);
        if (isAllVertical) {
            allImagesToLine(attachments);
            return;
        }

        boolean isFirstImageVertical = false;
        if (attachments.get(0) instanceof VKApiPhoto) {
            VKApiPhoto photo = (VKApiPhoto) attachments.get(0);
            isFirstImageVertical = photo.width < photo.height;
        }

        VKApiAttachment[] attachments1 = new VKApiAttachment[isFirstImageVertical ? 2 : 1];
        for (int i=0; i<attachments1.length; i++) attachments1[i] = attachments.get(i);
        ImageView[] views1 = createViewsForImages(isFirstImageVertical ? 2 : 1);
        LayoutParams[] params1 = new LayoutParams[isFirstImageVertical ? 2 : 1];
        for (int i=0; i<params1.length; i++) params1[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(attachments1, views1, params1);

        VKApiAttachment[] attachments2 = new VKApiAttachment[isFirstImageVertical ? 1 : 2];
        for (int i=0; i<attachments2.length; i++)
            attachments2[i] = attachments.get(i + (isFirstImageVertical ? 2 : 1));
        ImageView[] views2 = createViewsForImages(isFirstImageVertical ? 1 : 2);
        LayoutParams[] params2 = new LayoutParams[isFirstImageVertical ? 1 : 2];
        for (int i=0; i<params2.length; i++) {
            params2[i] = new LayoutParams(0, 0);
            params2[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params2[i].addRule(BELOW, views1[0].getId());
        }
        fillImagesToViewsInLine(attachments2, views2, params2);
    }

    private void handle4Or5Images(List<VKApiAttachment> attachments) {
        VKApiAttachment[] attachments1 = new VKApiAttachment[2];
        for (int i=0; i<attachments1.length; i++) attachments1[i] = attachments.get(i);
        ImageView[] views1 = createViewsForImages(2);
        LayoutParams[] params1 = new LayoutParams[2];
        for (int i=0; i<params1.length; i++) params1[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(attachments1, views1, params1);

        VKApiAttachment[] attachments2 = new VKApiAttachment[attachments.size() - 2];
        for (int i=0; i<attachments2.length; i++) attachments2[i] = attachments.get(i + 2);
        ImageView[] views2 = createViewsForImages(attachments.size() - 2);
        LayoutParams[] params2 = new LayoutParams[attachments.size() - 2];
        for (int i=0; i<params2.length; i++) {
            params2[i] = new LayoutParams(0, 0);
            params2[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params2[i].addRule(BELOW, views1[0].getId());
        }
        fillImagesToViewsInLine(attachments2, views2, params2);
    }

    private void handle6Or7Images(List<VKApiAttachment> attachments) {
        boolean isAllVertical = isAllVertical(attachments);

        final int countInTopLine = isAllVertical ? 3 : 2;
        VKApiAttachment[] attachments1 = new VKApiAttachment[countInTopLine];
        for (int i=0; i<attachments1.length; i++) attachments1[i] = attachments.get(i);
        ImageView[] views1 = createViewsForImages(countInTopLine);
        LayoutParams[] params1 = new LayoutParams[countInTopLine];
        for (int i=0; i<params1.length; i++) params1[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(attachments1, views1, params1);

        final int countMiddleLine = isAllVertical && attachments.size() == 7 ? 4 : countInTopLine;
        VKApiAttachment[] attachments2 = new VKApiAttachment[countMiddleLine];
        for (int i=0; i<attachments2.length; i++) attachments2[i] = attachments.get(i + countInTopLine);
        ImageView[] views2 = createViewsForImages(countMiddleLine);
        LayoutParams[] params2 = new LayoutParams[countMiddleLine];
        for (int i=0; i<params2.length; i++) {
            params2[i] = new LayoutParams(0, 0);
            params2[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params2[i].addRule(BELOW, views1[0].getId());
        }
        fillImagesToViewsInLine(attachments2, views2, params2);

        if (isAllVertical) return;
        VKApiAttachment[] attachments3 = new VKApiAttachment[attachments.size() - 4];
        for (int i=0; i<attachments3.length; i++) attachments3[i] = attachments.get(i + 4);
        ImageView[] views3 = createViewsForImages(attachments.size() - 4);
        LayoutParams[] params3 = new LayoutParams[attachments.size() - 4];
        for (int i=0; i<params3.length; i++) {
            params3[i] = new LayoutParams(0, 0);
            params3[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params3[i].addRule(BELOW, views2[0].getId());
        }
        fillImagesToViewsInLine(attachments3, views3, params3);
    }

    private void handle8Or9Or10Images(List<VKApiAttachment> attachments) {
        final int countInTopLine = attachments.size() == 8 ? 2 : 3;
        VKApiAttachment[] attachments1 = new VKApiAttachment[countInTopLine];
        for (int i=0; i<attachments1.length; i++) attachments1[i] = attachments.get(i);
        ImageView[] views1 = createViewsForImages(countInTopLine);
        LayoutParams[] params1 = new LayoutParams[countInTopLine];
        for (int i=0; i<params1.length; i++) params1[i] = new LayoutParams(0, 0);
        fillImagesToViewsInLine(attachments1, views1, params1);

        VKApiAttachment[] attachments2 = new VKApiAttachment[3];
        for (int i=0; i<attachments2.length; i++) attachments2[i] = attachments.get(i + countInTopLine);
        ImageView[] views2 = createViewsForImages(3);
        LayoutParams[] params2 = new LayoutParams[3];
        for (int i=0; i<params2.length; i++) {
            params2[i] = new LayoutParams(0, 0);
            params2[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params2[i].addRule(BELOW, views1[0].getId());
        }
        fillImagesToViewsInLine(attachments2, views2, params2);

        VKApiAttachment[] attachments3 = new VKApiAttachment[attachments.size() - countInTopLine - 3];
        for (int i=0; i<attachments3.length; i++)
            attachments3[i] = attachments.get(i + countInTopLine + 3);
        ImageView[] views3 = createViewsForImages(attachments.size() - countInTopLine - 3);
        LayoutParams[] params3 = new LayoutParams[attachments.size() - countInTopLine - 3];
        for (int i=0; i<params3.length; i++) {
            params3[i] = new LayoutParams(0, 0);
            params3[i].setMargins(0, (int) (MARGIN_BETWEEN_IMAGES * scale), 0, 0);
            params3[i].addRule(BELOW, views2[0].getId());
        }
        fillImagesToViewsInLine(attachments3, views3, params3);
    }

    private void fillImagesToViewsInLine(VKApiAttachment[] attachments, ImageView[] views,
                                         LayoutParams[] params) {

        int[] widthNorm = new int[attachments.length];
        int widthSum = widthNorm[0] = attachments[0] instanceof VKApiPhoto ?
                ((VKApiPhoto)attachments[0]).width : 640;
        int heightFirst = 0;
        if (attachments[0] instanceof VKApiPhoto) {
            VKApiPhoto photo = (VKApiPhoto) attachments[0];
            heightFirst = photo.height;
        } else if (attachments[0] instanceof VKApiVideo) {
            heightFirst = 480;
        }
        for (int i=1; i<attachments.length; i++) {
            if (attachments[i] instanceof VKApiPhoto) {
                VKApiPhoto photo = (VKApiPhoto) attachments[i];
                widthNorm[i] = heightFirst * photo.width / photo.height;
            } else if (attachments[i] instanceof VKApiVideo) {
                widthNorm[i] = heightFirst * 640 / 480;
            }
            widthSum += widthNorm[i];
        }

        final int[] widths = new int[attachments.length];
        final int[] heights = new int[attachments.length];
        if (attachments[0] instanceof VKApiPhoto) {
            VKApiPhoto photo = (VKApiPhoto) attachments[0];
            for(int i = 0; i<attachments.length; i++) {
                widths[i] = (int) ((IMAGES_WIDTH * widthNorm[i] / widthSum) * scale);
                heights[i] = widths[0] * photo.height / photo.width;
            }
        } else if (attachments[0] instanceof VKApiVideo) {
            for(int i = 0; i<attachments.length; i++) {
                widths[i] = (int) ((IMAGES_WIDTH * widthNorm[i] / widthSum) * scale);
                heights[i] = widths[0] * 480 / 640;
            }
        }

        for (int i = 0; i<attachments.length; i++) {
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

            String url = null;
            if (attachments[i] instanceof VKApiPhoto) {
                VKApiPhoto photo = (VKApiPhoto) attachments[i];
                url = photo.photo_604;
            } else if (attachments[i] instanceof VKApiVideo) {
                VKApiVideo video = (VKApiVideo) attachments[i];
                url = video.photo_640;
                if (url.isEmpty()) url = video.photo_320;
            }
            Glide.with(getContext())
                    .load(url)
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

    private static boolean isAllVertical(List<VKApiAttachment> images) {
        for (VKApiAttachment image : images) {
            if (image instanceof VKApiPhoto) {
                VKApiPhoto photo = (VKApiPhoto) image;
                if (photo.width > photo.height) return false;
            } else return false;
        }
        return true;
    }
}

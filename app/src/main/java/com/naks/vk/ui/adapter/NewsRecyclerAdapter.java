package com.naks.vk.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.naks.vk.R;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VISIBLE_THRESHOLD = 10;

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;
    private static final int VIEW_TYPE_ERROR_LOAD_PAGE = 3;

    private static final int MAX_COUNT_WORDS = 40;

    private static final int ANIMATE_EXPAND_DURATION = 200;

    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private boolean isShowError;

    private Context context;
    private VKApiNews data;
    private OnNewsItemClickListener newsItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private int spanColor;

    public NewsRecyclerAdapter(Context context) {
        this.context = context;
        this.spanColor = ContextCompat.getColor(context, R.color.colorAccent);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_item, parent, false));
            case VIEW_TYPE_PROGRESS:
                return new ProgressViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.progress_item, parent, false));
            case VIEW_TYPE_ERROR_LOAD_PAGE:
                return new ErrorViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.error_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ProgressViewHolder) return;

        if (holder instanceof ErrorViewHolder) {
            ((ErrorViewHolder)holder).button.setOnClickListener(v -> {
                isShowError = false;
                notifyItemChanged(getItemCount() - 1);
                onLoadMoreListener.onLoadMore();
            });
            return;
        }

        ItemViewHolder vh = (ItemViewHolder) holder;
        vh.item = data.items.get(position);

        int sourceId = vh.item.source_id;
        if (sourceId > 0) {
            vh.user = findUsersByItem(sourceId);
        } else {
            vh.group = findGroupByItem(sourceId);
        }
        assert vh.user != null;
        assert vh.group != null;

        Glide.with(context)
                .load(sourceId > 0 ? vh.user.photo_100 : vh.group.photo_100)
                .centerCrop()
                .crossFade()
                .into(vh.headImage);

        vh.owner.setText(sourceId > 0 ? vh.user.first_name + " " + vh.user.last_name :
                vh.group.name);

        String date = String.valueOf(vh.item.date);
        // TODO: 28.06.2016: Implement pars date
        vh.date.setText(date);

        vh.ownerLayout.setOnClickListener(view1 -> {
            Toast.makeText(context, "clickOnHeader", Toast.LENGTH_SHORT).show();
            // TODO: 28.06.2016: Implement showing owner (profile or group)
        });

        if (!vh.item.text.isEmpty()) {
            processAndPresentText(vh.collapsedText, vh.expandedText, vh.expandButton, vh.item.text);
            vh.expandButton.setOnClickListener(view -> expandWithAnimation(vh.collapsedText,
                    vh.expandedText, vh.expandButton, vh.item.text));
        } else {
            vh.collapsedText.setVisibility(View.GONE);
            vh.expandedText.setVisibility(View.GONE);
            vh.expandButton.setVisibility(View.GONE);
        }

        vh.collapsedText.setOnClickListener(v -> onItemClick(v, vh));
        vh.expandedText.setOnClickListener(v -> onItemClick(v, vh));
        vh.v.setOnClickListener(v -> onItemClick(v, vh));
    }

    private void onItemClick(View v, ItemViewHolder vh) {
        if (v.getTag() != null) {
            v.setTag(null);
            return;
        }
        Toast.makeText(context, "onNewsClick", Toast.LENGTH_SHORT).show();
        newsItemClickListener.onClick(vh.item, vh.user, vh.group);
    }

    @Override
    public int getItemCount() {
        return data == null || data.items == null ? 0 : data.items.size();
    }

    public VKApiNews getData() {
        return data;
    }

    public void setData(VKApiNews data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(VKApiNews data) {
        if (this.data == null) {
            this.data = data;
            return;
        }
        this.data.items.remove(getItemCount() - 1);
        this.data.items.addAll(data.items);

        for (VKApiUserFull user : data.profiles) {
            if (!this.data.profiles.contains(user)) {
                this.data.profiles.add(user);
            }
        }

        for (VKApiCommunityFull group : data.groups) {
            if (!this.data.groups.contains(group)) {
                this.data.groups.add(group);
            }
        }
        notifyDataSetChanged();
        isLoading = false;
    }

    private VKApiUserFull findUsersByItem(int sourceId) {
        for (VKApiUserFull user : data.profiles) {
            if(user.id == sourceId) return user;
        }
        return null;
    }

    private VKApiCommunityFull findGroupByItem(int sourceId) {
        for (VKApiCommunityFull group : data.groups) {
            if(group.id == -sourceId) return group;
        }
        return null;
    }

    public void initOnScrollListener(RecyclerView recyclerView) {

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                        data.items.add(null);
                        notifyItemInserted(getItemCount() - 1);
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void showErrorLoadPage() {
        isShowError = true;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        VKApiItem item = data.items.get(position);
        if (item != null) {
            return VIEW_TYPE_ITEM;
        } else {
            return isShowError ? VIEW_TYPE_ERROR_LOAD_PAGE : VIEW_TYPE_PROGRESS;
        }
    }

    public void setOnNewsItemClickListener(OnNewsItemClickListener listener) {
        this.newsItemClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnNewsItemClickListener {
        void onClick(VKApiItem item, VKApiUserFull user, VKApiCommunityFull community);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final View v;

        @BindView(R.id.ownerLayout) View ownerLayout;
        @BindView(R.id.headImage) ImageView headImage;
        @BindView(R.id.owner) TextView owner;
        @BindView(R.id.date) TextView date;
        @BindView(R.id.collapsedText) TextView collapsedText;
        @BindView(R.id.expandedText) TextView expandedText;
        @BindView(R.id.expandButton) TextView expandButton;

        public VKApiItem item;
        public VKApiUserFull user;
        public VKApiCommunityFull group;
        public ItemViewHolder(View view) {
            super(view);
            v = view;
            ButterKnife.bind(this, view);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public final View v;

        public ProgressViewHolder(View view) {
            super(view);
            v = view;
            ButterKnife.bind(this, view);
        }
    }

    public static class ErrorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button) Button button;

        public ErrorViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private void processAndPresentText(View collapsedText, View expandedText, View expandButton, String text) {

        String[] words = text.split(" ");

        if (words.length > MAX_COUNT_WORDS) {
            collapsedText.setVisibility(View.VISIBLE);
            expandedText.setVisibility(View.GONE);
            expandButton.setVisibility(View.VISIBLE);

            final ViewGroup.LayoutParams layoutParams = expandButton.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            expandButton.setLayoutParams(layoutParams);

            StringBuilder sb = new StringBuilder();
            for (int i=0; i<MAX_COUNT_WORDS; i++) {
                sb.append(words[i]);
                sb.append(i != MAX_COUNT_WORDS - 1 ? " " : "…");
            }
            TextView collapsedTV = (TextView) collapsedText;
            collapsedTV.setText(textToSpannable(sb.toString()), TextView.BufferType.SPANNABLE);
            collapsedTV.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            collapsedText.setVisibility(View.GONE);
            expandButton.setVisibility(View.GONE);
            expandedText.setVisibility(View.VISIBLE);
            TextView expandedTV = (TextView) expandedText;
            expandedTV.setText(textToSpannable(text), TextView.BufferType.SPANNABLE);
            expandedTV.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private Spannable textToSpannable(String text) {
        Spannable result = new SpannableString(text);
        addHashTagSpans(result);
        addUrlSpans(result);

        return result;
    }

    private void addHashTagSpans(Spannable spannable) {
        String text = spannable.toString();

        Pattern hashTagsPattern = Pattern.compile("(?:^|\\s|[\\p{Punct}&&[^/]])(#[\\p{L}0-9-_@]+)");
        Matcher mather = hashTagsPattern.matcher(text);
        List<String> hashTags = new ArrayList<>();
        while (mather.find()) {
            String tag = mather.group(1);
            String[] tags = tag.split("#");
            if (tags.length > 1) for (String t : tags) hashTags.add("#".concat(t));
            else hashTags.add("#".concat(tag));
        }

        for (String hashTag : hashTags) {
            int startPos, endPos; //span positions
            startPos = text.indexOf(hashTag);
            endPos = startPos + hashTag.length();
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(spanColor);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    view.setTag(true);
                    Toast.makeText(context, hashTag, Toast.LENGTH_SHORT).show();
                    //TODO 28.06.2016: Implement searching by tag
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };
            spannable.setSpan(colorSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(clickableSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void addUrlSpans(Spannable spannable) {
        String text = spannable.toString();

        Pattern urlPattern = Pattern.compile("(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?");
        Matcher matcher = urlPattern.matcher(text);
        List<String> urls = new ArrayList<>();
        while (matcher.find()) {
            String url = matcher.group();
            urls.add(url);
        }

        for (String url : urls) {
            int startPos, endPos; //span positions
            startPos = text.indexOf(url);
            endPos = startPos + url.length();
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(spanColor);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    view.setTag(true);
                    Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                    // TODO: 01.07.2016: Handle click on link
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };
            spannable.setSpan(colorSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(clickableSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void expandWithAnimation(View collapsedView,View expandedView, View button, String text) {

        int[] startHeight = new int[1];

        button.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        button.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        final ValueAnimator animator = ValueAnimator.ofInt(button.getMeasuredHeight(), 0);
                        animator.addUpdateListener(animation -> {
                            final ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
                            layoutParams.height = (int) animation.getAnimatedValue();
                            button.setLayoutParams(layoutParams);
                        });

                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(final Animator animation) {
                                final ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
                                layoutParams.height = 0;
                                button.setLayoutParams(layoutParams);
                            }
                        });

                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setDuration(ANIMATE_EXPAND_DURATION).start();
                    }
                });

        collapsedView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                collapsedView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                startHeight[0] = collapsedView.getMeasuredHeight();
            }
        });

        TextView expandedTV = (TextView) expandedView;
        expandedTV.setMovementMethod(LinkMovementMethod.getInstance());
        expandedTV.setText(textToSpannable(text), TextView.BufferType.SPANNABLE);
        expandedView.setVisibility(View.VISIBLE);
        collapsedView.setVisibility(View.GONE);

        expandedView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        expandedView.getViewTreeObserver().removeOnGlobalLayoutListener (this);

                        final ValueAnimator animator = ValueAnimator
                                .ofInt(startHeight[0], expandedView.getMeasuredHeight());

                        animator.addUpdateListener(animation -> {
                            final ViewGroup.LayoutParams layoutParams = expandedView.getLayoutParams();
                            layoutParams.height = (int) animation.getAnimatedValue();
                            expandedView.setLayoutParams(layoutParams);
                        });

                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(final Animator animation) {
                                final ViewGroup.LayoutParams layoutParams = expandedView.getLayoutParams();
                                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                                expandedView.setLayoutParams(layoutParams);
                            }
                        });

                        animator.setInterpolator(new LinearInterpolator());
                        animator.setDuration(ANIMATE_EXPAND_DURATION).start();
                    }
                });
    }
}

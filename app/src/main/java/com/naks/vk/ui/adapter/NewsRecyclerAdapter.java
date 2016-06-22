package com.naks.vk.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.naks.vk.R;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VISIBLE_THRESHOLD = 10;

    private static final int VIEW_ITEM = 0;
    private static final int VIEW_PROGRESS = 1;
    private static final int VIEW_ERROR_LOAD_PAGE = 3;

    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private boolean isShowError;

    private Context context;
    private VKApiNews data;
    private OnNewsItemClickListener newsItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;

    public NewsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_ITEM:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_item, parent, false));
            case VIEW_PROGRESS:
                return new ProgressViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.progress_item, parent, false));
            case VIEW_ERROR_LOAD_PAGE:
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

        if (data != null && data.items != null) {

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

            vh.owner.setText(sourceId > 0 ?
                    vh.user.first_name + " " + vh.user.last_name :
                    vh.group.name);

            vh.date.setText(String.valueOf(vh.item.date));
            if (!vh.item.text.isEmpty()) vh.text.setText(vh.item.text);

            vh.v.setOnClickListener(v -> newsItemClickListener.onClick(vh.item, vh.user, vh.group));
        }
    }

    @Override
    public int getItemCount() {
        return data == null || data.items == null ? 0 : data.items.size();
    }

    @SuppressWarnings("unused")
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
            return VIEW_ITEM;
        } else {
            return isShowError ? VIEW_ERROR_LOAD_PAGE : VIEW_PROGRESS;
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

        @BindView(R.id.headImage) ImageView headImage;
        @BindView(R.id.owner) TextView owner;
        @BindView(R.id.date) TextView date;
        @BindView(R.id.text) TextView text;

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
}

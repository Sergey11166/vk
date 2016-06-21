package com.naks.vk.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.naks.vk.R;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VISIBLE_THRESHOLD = 10;

    private static final int VIEW_ITEM = 0;
    private static final int VIEW_PROGRESS = 1;
    private static final int VIEW_ERROR_LOAD_PAGE = 3;

    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private boolean isShowError;

    private VKApiNews data;
    private OnNewsItemClickListener newsItemClickListener;
    private EndlessScrollListener endlessScrollListener;

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
            ((ErrorViewHolder)holder).v.setOnClickListener(v -> {
                isShowError = false;
                notifyItemChanged(getItemCount() - 1);
                endlessScrollListener.onLoadMore();
            });
            return;
        }

        if (data != null && data.items != null) {
            ItemViewHolder itemVH = (ItemViewHolder) holder;
            itemVH.item = data.items.get(position);
            int sourceId = itemVH.item.source_id;
            if (sourceId > 0) {
                itemVH.user = findUsersByItem(sourceId);
            } else {
                itemVH.group = findGroupByItem(sourceId);
            }
            assert itemVH.user != null;
            assert itemVH.group != null;
            itemVH.text.setText(sourceId > 0 ?
                    itemVH.user.first_name + "_" + itemVH.user.last_name :
                    itemVH.group.name);
            if (!itemVH.item.text.isEmpty()) itemVH.text.append("\n\n" + itemVH.item.text);
            itemVH.v.setOnClickListener(v -> newsItemClickListener
                    .onClick(itemVH.item, itemVH.user, itemVH.group));
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
                    if (endlessScrollListener != null) {
                        endlessScrollListener.onLoadMore();
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

    public void setEndlessScrollListener(EndlessScrollListener endlessScrollListener) {
        this.endlessScrollListener = endlessScrollListener;
    }

    public interface OnNewsItemClickListener {
        void onClick(VKApiItem item, VKApiUserFull user, VKApiCommunityFull community);
    }

    public interface EndlessScrollListener {
        void onLoadMore();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final View v;
        public final TextView text;
        public VKApiItem item;
        public VKApiUserFull user;
        public VKApiCommunityFull group;
        public ItemViewHolder(View view) {
            super(view);
            v = view;
            text = (TextView) view.findViewById(R.id.text);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public final View v;
        public ProgressBar progressBar;
        public ProgressViewHolder(View view) {
            super(view);
            v = view;
            progressBar = (ProgressBar) view.findViewById(R.id.progressItem);
        }
    }

    public static class ErrorViewHolder extends RecyclerView.ViewHolder {
        public final View v;
        public TextView error;
        public ErrorViewHolder(View view) {
            super(view);
            v = view;
            error = (TextView) view.findViewById(R.id.errorView);
        }
    }
}

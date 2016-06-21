package com.naks.vk.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naks.vk.R;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>{

    static final int VISIBLE_THRESHOLD = 10;

    private VKApiNews data;
    private OnNewsItemClickListener listener;
    private EndlessScrollListener endlessScrollListener;

    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NewsRecyclerAdapter.ViewHolder holder, int position) {
        if (data != null && data.items != null) {

            if(position == getItemCount() - VISIBLE_THRESHOLD) {
                if(endlessScrollListener != null) {
                    endlessScrollListener.onLoadMore();
                }
            }

            holder.item = data.items.get(position);
            int sourceId = holder.item.source_id;
            if (sourceId > 0) {
                holder.user = findUsersByItem(sourceId);
            } else {
                holder.group = findGroupByItem(sourceId);
            }
            assert holder.user != null;
            assert holder.group != null;
            holder.text.setText(sourceId > 0 ?
                    holder.user.first_name + "_" + holder.user.last_name :
                    holder.group.name);
            if (!holder.item.text.isEmpty()) holder.text.append("\n\n" + holder.item.text);
            holder.v.setOnClickListener(v -> listener.onClick(holder.item, holder.user, holder.group));
        }
    }

    @Override
    public int getItemCount() {
        return data == null || data.items == null ? 0 : data.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View v;
        public final TextView text;
        public VKApiItem item;
        public VKApiUserFull user;
        public VKApiCommunityFull group;
        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            text = (TextView) itemView.findViewById(R.id.text);
        }
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

    public void setOnNewsItemClickListener(OnNewsItemClickListener listener) {
        this.listener = listener;
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
}

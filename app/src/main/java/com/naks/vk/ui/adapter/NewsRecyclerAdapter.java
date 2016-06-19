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

    private VKApiNews data;
    private OnNewsItemClickListener listener;

    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NewsRecyclerAdapter.ViewHolder holder, int position) {
        if (data != null && data.items != null) {
            holder.item = data.items.get(position);
            int sourceId = holder.item.source_id;
            if (sourceId > 0) {
                holder.user = findUsersByItem(sourceId);
            } else {
                holder.group = findGroupByItem(sourceId);
            }
            holder.textContent.setText(holder.item.text);
            holder.sourceName.setText(sourceId > 0 ?
                    holder.user.first_name + "_" + holder.user.last_name :
                    holder.group.name);
            holder.v.setOnClickListener(v -> listener.onClick(holder.item, holder.user, holder.group));
        }
    }

    @Override
    public int getItemCount() {
        return data == null || data.items == null ? 0 : data.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View v;
        public final TextView textContent;
        public final TextView sourceName;
        public VKApiItem item;
        public VKApiUserFull user;
        public VKApiCommunityFull group;
        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            textContent = (TextView) itemView.findViewById(R.id.text);
            sourceName = (TextView) itemView.findViewById(R.id.source_name);
        }
    }

    @SuppressWarnings("unused")
    public VKApiNews getData() {
        return data;
    }

    public void setData(VKApiNews data) {
        this.data = data;
    }

    public void setOnNewsItemClickListener(OnNewsItemClickListener listener) {
        this.listener = listener;
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

    public interface OnNewsItemClickListener {
        void onClick(VKApiItem item, VKApiUserFull user, VKApiCommunityFull community);
    }
}

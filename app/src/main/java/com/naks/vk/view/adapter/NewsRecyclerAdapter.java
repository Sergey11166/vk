package com.naks.vk.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naks.vk.R;
import com.naks.vk.model.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>{

    private List<News> data = new ArrayList<>();
    private OnNewsItemClickListener listener;

    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NewsRecyclerAdapter.ViewHolder holder, int position) {
        holder.news = data.get(position);
        holder.textContent.setText(holder.news.getContent());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, holder.news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View v;
        public final TextView textContent;
        public News news;
        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            textContent = (TextView) itemView.findViewById(R.id.text_content);
        }
    }

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }

    public void setOnNewsItemClickListener(OnNewsItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnNewsItemClickListener {
        void onClick(View v, News news);
    }
}

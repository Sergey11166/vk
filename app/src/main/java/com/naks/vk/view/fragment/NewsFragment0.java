package com.naks.vk.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.naks.vk.R;
import com.naks.vk.model.domain.News;
import com.naks.vk.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment0 extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static NewsFragment0 newInstance() {
        NewsFragment0 instance = new NewsFragment0();
        return instance;
    }

    public NewsFragment0() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //((MainActivity)getActivity()).getComponent().inject(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater
                .from(getContext())
                .inflate(R.layout.news_fragment0, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNews();
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        setupRecyclerView(recyclerView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new NewsAdapter(createSampleNews()));
    }

    private void refreshNews() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    private List<News> createSampleNews() {
        List<News> result = new ArrayList<>(100);
        for (int i=0; i<100; i++) {
            News news = new News();
            news.setContent("text content " + i);
            result.add(news);
        }
        return result;
    }

    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private final List<News> mNewsList;

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }


        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
            holder.mNews = mNewsList.get(position);
            holder.mTextContent.setText(holder.mNews.getContent());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "start newsActivity", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mTextContent;
            public News mNews;
            public ViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                mTextContent = (TextView) itemView.findViewById(R.id.text_content);
            }
        }
    }
}

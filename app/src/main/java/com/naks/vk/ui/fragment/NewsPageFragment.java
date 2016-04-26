package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.naks.vk.R;
import com.naks.vk.mvp.model.viewmodel.News;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.view.NewsPageView;
import com.naks.vk.ui.adapter.NewsRecyclerAdapter;

import java.util.List;

public class NewsPageFragment extends BaseFragment implements NewsPageView {

    private static final String TAG = "NewsPageFragment";
    public static final String KEY_NEWS_TYPE = NewsPageFragment.class.getName() + "_keyNewsType";

    @InjectPresenter
    NewsPagePresenter presenter;

    private NewsRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View progressView;
    private View errorView;

    public static NewsPageFragment newInstance() {
        return new NewsPageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_page_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreated(getArguments());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {presenter.loadNews(true);}
        });
        progressView = view.findViewById(R.id.progressView);
        errorView = view.findViewById(R.id.errorView);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadNews(false);
            }
        });
        setupRecyclerView((RecyclerView) view.findViewById(R.id.recyclerView));
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter = new NewsRecyclerAdapter();
        adapter.setOnNewsItemClickListener(new NewsRecyclerAdapter.OnNewsItemClickListener() {
            @Override
            public void onClick(View view, News news) {
                presenter.onItemClick(news);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showRefreshing() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        swipeRefreshLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRefreshingError(String message) {
        Toast.makeText(getActivity(), "refreshing failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNews(List<News> news, boolean maybeMore) {
        adapter.setData(news);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addNews(List<News> news, boolean maybeMore) {

    }

    @Override
    public void navigateToNewsDetailActivity(News item) {
        Log.d(TAG, "navigateToNewsDetailActivity");
    }
}

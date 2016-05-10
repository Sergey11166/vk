package com.naks.vk.ui.fragment;

import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsPageFragment extends BaseFragment implements NewsPageView {

    private static final String TAG = "NewsPageFragment";
    public static final String KEY_NEWS_TYPE = NewsPageFragment.class.getName() + "_keyNewsType";

    @InjectPresenter NewsPagePresenter presenter;

    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressView) View progressView;
    @BindView(R.id.errorView) View errorView;

    private NewsRecyclerAdapter adapter;
    private Unbinder unbinder;

    private boolean isVisibleToUser;

    public static NewsPageFragment newInstance() {
        return new NewsPageFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreated(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_page_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        errorView.setOnClickListener(v -> presenter.loadNews(false));
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadNews(true));
        setupRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupRecyclerView() {
        adapter = new NewsRecyclerAdapter();
        adapter.setOnNewsItemClickListener(id -> presenter.onItemClick(id));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showRefreshing() {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(false));
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
        if (!isVisibleToUser) return;
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
    public void navigateToNewsDetailActivity(long id) {
        Log.d(TAG, "navigateToNewsDetailActivity " + id);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
    }
}

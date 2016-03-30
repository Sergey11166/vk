package com.naks.vk.view.fragment;

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

import com.naks.vk.R;
import com.naks.vk.di.HasComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.di.component.NewsPageComponent;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.model.domain.News;
import com.naks.vk.model.interactor.NewsPageInteractor;
import com.naks.vk.presenter.NewsPagePresenter;
import com.naks.vk.view.NewsPageView;
import com.naks.vk.view.adapter.NewsRecyclerAdapter;

import javax.inject.Inject;

public class NewsPageFragment extends BaseFragment implements
        HasComponent<NewsPageComponent>,
        NewsPageView {

    private static final String TAG = "NewsPageFragment";
    public static final String KEY_NEWS_TYPE = NewsPageFragment.class.getName() + "KEY_NEWS_TYPE";

    private NewsPageComponent component;

    @Inject NewsPagePresenter presenter;
    @Inject NewsRecyclerAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private NewsPageInteractor.TypeNews typeNews;
    private View progressBar;

    public static NewsPageFragment newInstance() {
        NewsPageFragment instance = new NewsPageFragment();
        instance.setRetainInstance(true);
        return instance;
    }

    public NewsPageFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeNews = getTypeNews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView(recyclerView);
        setupSwipeRefreshLayout();
        presenter.onActivityCreated(typeNews);
        presenter.onRefreshNews(typeNews);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void setupComponent(MainComponent component) {
        this.component = component.plus(new NewsPageModule(this));
        this.component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater
                .from(getContext())
                .inflate(R.layout.news_page_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        progressBar = rootView.findViewById(R.id.progress);
        return rootView;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter.setOnNewsItemClickListener(new NewsRecyclerAdapter.OnNewsItemClickListener() {
            @Override
            public void onClick(View view, News news) {
                presenter.onItemClick(view, news);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefreshNews(typeNews);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
    }

    private NewsPageInteractor.TypeNews getTypeNews() {
        String key = getArguments().getString(KEY_NEWS_TYPE);
        assert key != null;
        return NewsPageInteractor.TypeNews.valueOf(NewsPageInteractor.TypeNews.class, key);
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean isShow) {
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
        swipeRefreshLayout.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setRefreshingFalse() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void navigateToNewsDetailActivity(News item) {
        Log.d(TAG, "navigateToNewsDetailActivity");
    }

    @Override
    public NewsPageComponent getComponent() {
        return component;
    }
}

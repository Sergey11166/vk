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

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.CastedArrayListLceViewState;
import com.naks.vk.R;
import com.naks.vk.di.HasComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.di.component.NewsPageComponent;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.model.domain.News;
import com.naks.vk.model.interactor.NewsPageInteractor;
import com.naks.vk.presenter.NewsPagePresenter;
import com.naks.vk.presenter.NewsPagePresenterImpl;
import com.naks.vk.view.NewsPageView;
import com.naks.vk.view.activity.MainActivity;
import com.naks.vk.view.adapter.NewsRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

public class NewsPageFragment extends MosbyBaseFragment<SwipeRefreshLayout, List<News>,
        NewsPageView, NewsPagePresenter, MainComponent, MainActivity>
        implements
        NewsPageView,
        HasComponent<NewsPageComponent>,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "NewsPageFragment";
    public static final String KEY_NEWS_TYPE = NewsPageFragment.class.getName() + "KEY_NEWS_TYPE";

    private NewsPageComponent component;

    @Inject NewsRecyclerAdapter adapter;

    private RecyclerView recyclerView;
    private NewsPageInteractor.TypeNews typeNews;

    public static NewsPageFragment newInstance() {
        NewsPageFragment instance = new NewsPageFragment();
        return instance;
    }

    @NonNull
    @Override public LceViewState<List<News>, NewsPageView> createViewState() {
        return new CastedArrayListLceViewState<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView(recyclerView);
        Log.d(TAG, "presenter.hashCode()" + String.valueOf(presenter.hashCode()));
    }

    @Override
    protected void setupComponent(MainComponent component) {
        this.component = component.plus(new NewsPageModule(this));
        this.component.inject(this);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_page_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        typeNews = getTypeNews();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        setupRecyclerView(recyclerView);
        Log.d(TAG, "presenter.hashCode()" + String.valueOf(presenter.hashCode()));
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

    private NewsPageInteractor.TypeNews getTypeNews() {
        String key = getArguments().getString(KEY_NEWS_TYPE);
        assert key != null;
        return NewsPageInteractor.TypeNews.valueOf(NewsPageInteractor.TypeNews.class, key);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadNews(typeNews, pullToRefresh);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public NewsPagePresenter createPresenter() {
        return new NewsPagePresenterImpl(this);
    }

    @Override
    public void setData(List<News> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            contentView.post(new Runnable() {
                @Override
                public void run() {
                    contentView.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public List<News> getData() {
        return adapter == null? null : adapter.getData();
    }

    @Override
    public boolean isRetainInstance() {
        return super.isRetainInstance();
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

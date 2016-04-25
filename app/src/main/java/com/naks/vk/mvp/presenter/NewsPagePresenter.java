package com.naks.vk.mvp.presenter;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.App;
import com.naks.vk.di.component.DaggerNewsPageComponent;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.viewmodel.News;
import com.naks.vk.mvp.view.NewsPageView;
import com.naks.vk.ui.fragment.NewsPageFragment;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class NewsPagePresenter extends MvpPresenter<NewsPageView> implements
        GetNewsInteractor.OnNewsLoadingFinishedListener {

    @Inject GetNewsInteractor interactor;

    private boolean mIsInLoading;

    private GetNewsInteractor.TypeNews typeNews;

    public NewsPagePresenter() {
        super();
        initComponent();
    }

    private void initComponent() {
        DaggerNewsPageComponent.builder()
                .appComponent(App.get().getComponent())
                .newsPageModule(new NewsPageModule())
                .build()
                .inject(this);
    }

    public void onCreated(Bundle args) {
        String key = args.getString(NewsPageFragment.KEY_NEWS_TYPE);
        assert key != null;
        typeNews = GetNewsInteractor.TypeNews.valueOf(GetNewsInteractor.TypeNews.class, key);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadNews(false);
    }

    public void onItemClick(News item) {
        getViewState().navigateToNewsDetailActivity(item);
    }

    public void loadNextNews(int currentCount) {
        int page = currentCount / 10;
        loadData(page, true, false);
    }

    public void loadNews(boolean pullToRefresh) {
        loadData(1, false, pullToRefresh);
    }

    private void loadData(int page, boolean isPageLoading, boolean pullToRefresh) {
        if (mIsInLoading) return;
        mIsInLoading = true;
        showProgress(isPageLoading, isPageLoading);
        interactor.get(typeNews, page, isPageLoading, pullToRefresh, this);
    }

    private void showProgress(boolean isPageLoading, boolean pullToRefresh) {
        if (isPageLoading) return;
        if (pullToRefresh) {
            getViewState().showRefreshing();
        } else {
            getViewState().showProgress();
        }
    }

    private void hideProgress(boolean isPageLoading, boolean pullToRefresh) {
        if (isPageLoading) return;
        if (pullToRefresh){
            getViewState().hideRefreshing();
        } else {
            getViewState().hideProgress();
        }
    }

    @Override
    public void onLoadingSuccess(List<News> news, boolean isPageLoading, boolean pullToRefresh) {
        hideProgress(isPageLoading, pullToRefresh);
        getViewState().setNews(news, false);
    }

    @Override
    public void onLoadingFailed(Exception e, boolean isPageLoading, boolean pullToRefresh) {
        hideProgress(isPageLoading, pullToRefresh);
        if (pullToRefresh) {
            getViewState().showRefreshingError(e.getMessage());
        } else {
            getViewState().showError(e.getMessage());
        }
    }
}

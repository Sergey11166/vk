package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.viewmodel.News;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.view.NewsPageView;
import com.naks.vk.ui.fragment.NewsPageFragment;

import java.util.List;

import javax.inject.Inject;

public class NewsPagePresenterImpl
        implements NewsPagePresenter, GetNewsInteractor.OnNewsLoadingFinishedListener{

    @Inject NewsPageView view;
    @Inject GetNewsInteractor interactor;

    private boolean isInLoading;
    private boolean isViewCreated;

    private GetNewsInteractor.TypeNews typeNews;

    private LoadingSuccessHolder successHolder;
    private LoadingErrorHolder errorHolder;

    public NewsPagePresenterImpl(NewsPageFragment fragment) {
        fragment.getComponent().inject(this);
        String key = fragment.getArguments().getString(NewsPageFragment.KEY_NEWS_TYPE);
        assert key != null;
        typeNews = GetNewsInteractor.TypeNews.valueOf(GetNewsInteractor.TypeNews.class, key);
    }

    @Override
    public void onViewCreated() {
        isViewCreated = true;
        updateUI();
    }

    @Override
    public void onDestroyView() {
        isViewCreated = false;
    }

    public void onItemClick(long id) {
        view.navigateToNewsDetailActivity(id);
    }

    @SuppressWarnings("unused")
    public void loadNextNews(int currentCount) {
        int page = currentCount / 10;
        loadData(page, true, false);
    }

    public void loadNews(boolean pullToRefresh) {
        loadData(1, false, pullToRefresh);
    }

    private void loadData(int page, boolean isPageLoading, boolean pullToRefresh) {
        if (isInLoading) return;
        isInLoading = true;
        showProgress(isPageLoading, pullToRefresh);
        interactor.get(typeNews, page, isPageLoading, pullToRefresh, this);
    }

    private void showProgress(boolean isPageLoading, boolean pullToRefresh) {
        if (isPageLoading) return;
        if (pullToRefresh) {
            view.showRefreshing();
        } else {
            view.showProgress();
        }
    }

    private void hideProgress(boolean isPageLoading, boolean pullToRefresh) {
        if (isPageLoading) return;
        if (pullToRefresh){
            view.hideRefreshing();
        } else {
            view.hideProgress();
        }
    }

    @Override
    public void onLoadingSuccess(List<News> news, boolean isPageLoading, boolean pullToRefresh) {
        isInLoading = false;

        if (successHolder == null) successHolder = new LoadingSuccessHolder();
        successHolder.news = news;
        successHolder.isPageLoading = isPageLoading;
        successHolder.pullToRefresh = pullToRefresh;
        successHolder.isApplied = false;

        if (isViewCreated) updateUI();
    }

    @Override
    public void onLoadingFailed(Throwable t, boolean isPageLoading, boolean pullToRefresh) {
        isInLoading = false;

        if (errorHolder == null) errorHolder = new LoadingErrorHolder();
        errorHolder.throwable = t;
        errorHolder.isPageLoading = isPageLoading;
        errorHolder.pullToRefresh = pullToRefresh;
        errorHolder.isApplied = false;

        if (isViewCreated) updateUI();
    }

    private void updateUI() {
        if (successHolder != null && !successHolder.isApplied) {
            hideProgress(successHolder.isPageLoading, successHolder.pullToRefresh);
            view.setNews(successHolder.news, false);
            successHolder.isApplied = true;
        } else if (errorHolder != null && !errorHolder.isApplied) {
            hideProgress(errorHolder.isPageLoading, errorHolder.pullToRefresh);
            if (errorHolder.pullToRefresh) {
                view.showLiteError(errorHolder.throwable.getMessage());
            } else {
                view.showError(errorHolder.throwable.getMessage());
            }
            errorHolder.isApplied = true;
        }
    }

    private class LoadingSuccessHolder {
        List<News> news;
        boolean isPageLoading;
        boolean pullToRefresh;
        boolean isApplied;
    }

    private class LoadingErrorHolder {
        Throwable throwable;
        boolean isPageLoading;
        boolean pullToRefresh;
        boolean isApplied;
    }
}

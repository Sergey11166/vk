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

    private StateHolder stateHolder;

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
        if (stateHolder == null) {
            stateHolder = new StateHolder();
        }
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
        interactor.get(typeNews, page, isPageLoading, pullToRefresh, this);
        stateHolder.throwable = null;
        stateHolder.isPageLoading = isPageLoading;
        stateHolder.pullToRefresh = pullToRefresh;
        showProgress(isPageLoading, pullToRefresh);
    }

    private void showProgress(boolean isPageLoading, boolean pullToRefresh) {
        stateHolder.isShowProgress = true;
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
        stateHolder.news = news;
        stateHolder.isShowProgress = false;
        if (isViewCreated) updateUI();
    }

    @Override
    public void onLoadingFailed(Throwable t, boolean isPageLoading, boolean pullToRefresh) {
        isInLoading = false;
        stateHolder.throwable = t;
        stateHolder.isShowProgress = false;
        if (isViewCreated) updateUI();
    }

    private void updateUI() {
        if (stateHolder == null) return;
        if (stateHolder.news != null) {
            hideProgress(stateHolder.isPageLoading, stateHolder.pullToRefresh);
            view.setNews(stateHolder.news, false);
        }
        if (stateHolder.isShowProgress) {
            showProgress(stateHolder.isPageLoading, stateHolder.pullToRefresh);
            if (!stateHolder.pullToRefresh && !stateHolder.isPageLoading) return;
        }
        if (stateHolder.throwable != null) {
            hideProgress(stateHolder.isPageLoading, stateHolder.pullToRefresh);
            if (stateHolder.pullToRefresh) {
                view.showLiteError(stateHolder.throwable.getMessage());
                stateHolder.throwable = null;
            } else {
                view.showError(stateHolder.throwable.getMessage());
            }
        }
    }

    private class StateHolder {
        List<News> news;
        Throwable throwable;
        boolean isPageLoading;
        boolean pullToRefresh;
        boolean isShowProgress;
    }
}

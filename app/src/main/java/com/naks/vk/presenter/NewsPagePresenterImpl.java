package com.naks.vk.presenter;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.naks.vk.model.domain.News;
import com.naks.vk.model.interactor.NewsPageInteractor;
import com.naks.vk.view.NewsPageView;
import com.naks.vk.view.fragment.NewsPageFragment;

import java.util.List;

import javax.inject.Inject;

public class NewsPagePresenterImpl extends MvpBasePresenter<NewsPageView> implements
        NewsPagePresenter, NewsPageInteractor.OnNewsFinishedListener {

    private static final String TAG = "NewsPagePresenterImpl";

    @Inject NewsPageInteractor interactor;

    private NewsPageView view;

    public NewsPagePresenterImpl(NewsPageFragment fragment) {
        fragment.getComponent().inject(this);
        view = fragment;
    }

    @Override
    public void onItemClick(View v, News item) {
        view.navigateToNewsDetailActivity(item);
    }

    @Override
    public void loadNews(NewsPageInteractor.TypeNews type, boolean pullToRefresh) {
        view.showLoading(pullToRefresh);
        interactor.loadNews(type, this, pullToRefresh);
    }

    @Override
    public void onSuccess(List<News> result) {
        if (isViewAttached()) {
            view.setData(result);
            view.showContent();
        }
    }

    @Override
    public void onError(Exception e, boolean pullToRefresh) {
        if (isViewAttached()) {
            view.showError(e, pullToRefresh);
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }

    @Override
    public void attachView(NewsPageView view) {
        super.attachView(view);
    }
}

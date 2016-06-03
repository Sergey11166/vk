package com.naks.vk.mvp.presenter.impl;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.viewmodel.News;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.view.NewsPageView;
import com.naks.vk.ui.fragment.NewsPageFragment;

import java.util.List;

import javax.inject.Inject;

public class NewsPagePresenterImpl extends MvpBasePresenter <NewsPageView>
        implements NewsPagePresenter, GetNewsInteractor.OnNewsLoadingFinishedListener {

    private static final String TAG = "NewsPagePresenter";

    @Inject GetNewsInteractor interactor;

    private GetNewsInteractor.TypeNews typeNews;

    public NewsPagePresenterImpl(NewsPageFragment fragment) {
        Log.d(TAG, "constructor " + toString());
        fragment.getComponent().inject(this);
        String key = fragment.getArguments().getString(NewsPageFragment.KEY_NEWS_TYPE);
        assert key != null;
        typeNews = GetNewsInteractor.TypeNews.valueOf(GetNewsInteractor.TypeNews.class, key);
    }

    @Override
    public void loadNews(boolean pullToRefresh) {
        Log.d(TAG, "loadNews(" + pullToRefresh + ")");
        interactor.get(typeNews, pullToRefresh, this);
        assert getView() != null;
        getView().showLoading(pullToRefresh);
    }

    @Override
    public void onItemClick(long id) {
        Log.d(TAG, "inItemClick(" + id + ")");
        if (isViewAttached()) {
            assert getView() != null;
            getView().navigateToNewsDetailActivity(id);
        }
    }

    @Override
    public void attachView(NewsPageView view) {
        super.attachView(view);
        Log.d(TAG, "attachView(" + view.toString() + ")");
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        Log.d(TAG, "detachView(" + retainInstance + ")");
    }

    @Override
    public void onLoadingSuccess(List<News> news) {
        if (isViewAttached()) {
            Log.d(TAG, "setData()");
            assert getView() != null;
            getView().setData(news);

            Log.d(TAG, "showContent()");
            getView().showContent();
        }
    }

    @Override
    public void onLoadingFailed(Throwable t, boolean pullToRefresh) {
        if (isViewAttached()) {

            Log.d(TAG, "showError(" + t.getClass().getSimpleName() + " , " + pullToRefresh + ")");
            assert getView() != null;
            getView().showError(t, pullToRefresh);
        }
    }
}

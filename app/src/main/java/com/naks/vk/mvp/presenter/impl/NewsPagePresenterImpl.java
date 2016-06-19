package com.naks.vk.mvp.presenter.impl;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.view.NewsPageView;
import com.naks.vk.ui.fragment.NewsPageFragment;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

import javax.inject.Inject;

public class NewsPagePresenterImpl extends MvpBasePresenter <NewsPageView>
        implements NewsPagePresenter, GetNewsInteractor.OnNewsLoadingFinishedListener {

    private static final String TAG = "NewsPagePresenter";

    @Inject GetNewsInteractor interactor;

    private GetNewsInteractor.TypeNews typeNews;

    private String startFrom;

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
        interactor.get(typeNews, pullToRefresh, startFrom, this);
        assert getView() != null;
        getView().showLoading(pullToRefresh);
    }

    @Override
    public void onItemClick(VKApiItem item, VKApiUserFull user, VKApiCommunityFull group) {
        Log.d(TAG, "inItemClick(" + item + ")");
        if (isViewAttached()) {
            assert getView() != null;
            getView().navigateToNewsDetailActivity(item, user, group);
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
    public void onLoadingSuccess(VKApiNews news) {
        if (isViewAttached()) {
            Log.d(TAG, "setData()");
            startFrom = news.next_from;
            assert getView() != null;
            getView().setData(news);

            Log.d(TAG, "showContent()");
            getView().showContent();
        }
    }

    @Override
    public void onLoadingFailed(VKError error, boolean pullToRefresh) {
        if (isViewAttached()) {

            Log.d(TAG, "showError(" + error.getClass().getSimpleName() + " , " + pullToRefresh + ")");
            assert getView() != null;
            getView().showError(new Exception(error.toString()), pullToRefresh);
        }
    }
}

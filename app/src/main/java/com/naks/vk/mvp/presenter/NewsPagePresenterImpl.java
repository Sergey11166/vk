package com.naks.vk.mvp.presenter;

import android.view.View;

import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.viewmodel.News;
import com.naks.vk.mvp.view.NewsPageView;

import java.util.List;

import javax.inject.Inject;

public class NewsPagePresenterImpl extends MvpPresenter<NewsPageView> implements
        NewsPagePresenter, GetNewsInteractor.OnNewsFinishedListener {

    private static final String TAG = "NewsPagePresenterImpl";

    @Inject GetNewsInteractor interactor;

    public NewsPagePresenterImpl() {
        super();
    }

    @Override
    public void onItemClick(View v, News item) {
        getViewState().navigateToNewsDetailActivity(item);
    }

    @Override
    public void loadNews(GetNewsInteractor.TypeNews type, boolean pullToRefresh) {
        interactor.loadNews(type, this, pullToRefresh);
    }

    @Override
    public void onSuccess(List<News> result) {

    }

    @Override
    public void onError(Exception e, boolean pullToRefresh) {

    }
}

package com.naks.vk.presenter;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.naks.vk.model.domain.News;
import com.naks.vk.model.interactor.NewsPageInteractor;
import com.naks.vk.view.NewsPageView;

public interface NewsPagePresenter extends MvpPresenter<NewsPageView > {

    void onItemClick(View v, News item);

    void loadNews(NewsPageInteractor.TypeNews type, final boolean pullToRefresh);
}

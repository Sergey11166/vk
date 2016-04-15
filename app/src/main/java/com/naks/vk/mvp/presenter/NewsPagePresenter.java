package com.naks.vk.mvp.presenter;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.naks.vk.mvp.model.viewmodel.News;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.view.NewsPageView;

public interface NewsPagePresenter extends MvpPresenter<NewsPageView > {

    void onItemClick(View v, News item);

    void loadNews(GetNewsInteractor.TypeNews type, final boolean pullToRefresh);
}

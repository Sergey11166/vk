package com.naks.vk.mvp.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.naks.vk.mvp.view.NewsPageView;

public interface NewsPagePresenter extends MvpPresenter<NewsPageView> {

    void loadNews(boolean pullToRefresh);

    void onItemClick(long id);
}
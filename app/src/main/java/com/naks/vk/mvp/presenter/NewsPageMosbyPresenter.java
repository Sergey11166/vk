package com.naks.vk.mvp.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.naks.vk.mvp.view.NewsPageMosbyView;

public interface NewsPageMosbyPresenter extends MvpPresenter<NewsPageMosbyView> {

    void loadNews(final boolean pullToRefresh);
}
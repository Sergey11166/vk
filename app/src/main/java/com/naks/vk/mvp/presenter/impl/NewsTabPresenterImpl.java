package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.mvp.presenter.NewsTabPresenter;
import com.naks.vk.mvp.view.NewsTabView;

public class NewsTabPresenterImpl implements NewsTabPresenter {

    private NewsTabView view;

    public NewsTabPresenterImpl(NewsTabView view) {
        this.view = view;
    }

    public void onFABClick() {
        view.navigateToNewPostActivity();
    }
}

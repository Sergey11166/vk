package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.mvp.presenter.NewsTabPresenter;
import com.naks.vk.mvp.view.NewsTabView;
import com.naks.vk.ui.fragment.NewsTabsFragment;

import javax.inject.Inject;

public class NewsTabPresenterImpl implements NewsTabPresenter {

    @Inject NewsTabView view;

    public NewsTabPresenterImpl(NewsTabsFragment fragment) {
        fragment.getComponent().inject(this);
    }

    public void onFABClick() {
        view.navigateToNewPostActivity();
    }
}

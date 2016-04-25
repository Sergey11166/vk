package com.naks.vk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.mvp.view.NewsTabView;

@InjectViewState
public class NewsTabPresenter extends MvpPresenter<NewsTabView> {

    public void onFABClick() {
        getViewState().navigateToNewPostActivity();
    }
}

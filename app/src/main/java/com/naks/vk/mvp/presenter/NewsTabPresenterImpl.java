package com.naks.vk.mvp.presenter;

import android.util.Log;

import com.naks.vk.mvp.model.interactor.NewsTabInteractor;
import com.naks.vk.mvp.view.NewsTabView;
import com.naks.vk.ui.fragment.NewsTabsFragment;

import javax.inject.Inject;

public class NewsTabPresenterImpl implements NewsTabPresenter {

    private static final String TAG = "NewsTabPresenterImpl";

    @Inject NewsTabView view;
    @Inject NewsTabInteractor interactor;

    public NewsTabPresenterImpl(NewsTabsFragment fragment) {
        fragment.getComponent().inject(this);
    }

    @Override
    public void onFABClick() {
        String result = interactor.testAction();
        view.navigateToNewPostActivity();
        Log.d(TAG, view.toString());
        Log.d(TAG, result);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}

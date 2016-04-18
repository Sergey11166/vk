package com.naks.vk.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.mvp.model.interactor.NewsTabInteractor;
import com.naks.vk.mvp.view.NewsTabView;

import javax.inject.Inject;

@InjectViewState
public class NewsTabPresenterImpl extends MvpPresenter<NewsTabView> implements NewsTabPresenter {

    private static final String TAG = "NewsTabPresenterImpl";

    @Inject NewsTabInteractor interactor;

    public NewsTabPresenterImpl() {
        super();
    }

    @Override
    public void onFABClick() {
        String result = interactor.testAction();
        getViewState().navigateToNewPostActivity();
        Log.d(TAG, result);
    }

    @Override
    public void onDestroy() {

    }
}

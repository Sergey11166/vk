package com.naks.vk.mvp.model.interactor;

import com.naks.vk.mvp.model.viewmodel.News;

import java.util.List;

public interface GetNewsInteractor {

    enum TypeNews {
        NEWS,
        RECOMMENDATIONS,
        FRIENDS,
        COMMUNITIES
    }

    interface OnNewsLoadingFinishedListener {

        void onLoadingSuccess(List<News> news);

        void onLoadingFailed(Throwable t, boolean pullToRefresh);
    }

    void get(TypeNews type, boolean pullToRefresh, OnNewsLoadingFinishedListener listener);
}
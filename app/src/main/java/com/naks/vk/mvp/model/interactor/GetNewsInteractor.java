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

        void onLoadingSuccess(List<News> news, boolean isPageLoading, boolean pullToRefresh);

        void onLoadingFailed(Throwable t, boolean isPageLoading, boolean pullToRefresh);
    }

    void get(TypeNews type, int page, boolean isPageLoading, boolean pullToRefresh,
             OnNewsLoadingFinishedListener listener);
}
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

    interface OnNewsFinishedListener {

        void onSuccess(List<News> result);

        void onError(Exception e, boolean pullToRefresh);
    }

    void loadNews(TypeNews type, OnNewsFinishedListener listener, boolean pullToRefresh);

    void cancelLoader();

}

package com.naks.vk.model.interactor;

import com.naks.vk.model.domain.News;

import java.util.List;

public interface NewsPageInteractor {

    enum TypeNews {
        NEWS,
        RECOMMENDATIONS,
        FRIENDS,
        COMMUNITIES
    }

    interface OnNewsFinishedListener {

        void onSuccess(List<News> result);

        void onError();
    }

    void requestNews(TypeNews type, OnNewsFinishedListener listener);

    void onDestroy();


}

package com.naks.vk.mvp.model.interactor;

import com.naks.vk.api.domain.VKApiNews;
import com.vk.sdk.api.VKError;

public interface GetNewsInteractor {

    enum TypeNews {
        NEWS,
        RECOMMENDATIONS,
        FRIENDS,
        COMMUNITIES
    }

    interface OnNewsLoadingFinishedListener {

        void onLoadingSuccess(VKApiNews news, boolean pullTpRefresh);

        void onLoadingFailed(VKError error, boolean pullToRefresh);
    }

    void get(TypeNews type, boolean pullToRefresh, String startFrom, OnNewsLoadingFinishedListener listener);
}
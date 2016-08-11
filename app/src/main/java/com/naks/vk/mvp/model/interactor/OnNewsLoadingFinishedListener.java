package com.naks.vk.mvp.model.interactor;

import com.naks.vk.api.domain.VKApiNews;
import com.vk.sdk.api.VKError;

public interface OnNewsLoadingFinishedListener {

    void onLoadingSuccess(VKApiNews news, boolean pullTpRefresh);

    void onLoadingFailed(VKError error, boolean pullToRefresh);
}
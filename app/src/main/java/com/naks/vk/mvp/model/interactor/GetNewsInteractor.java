package com.naks.vk.mvp.model.interactor;

public interface GetNewsInteractor {

    void get(TypeNews type, boolean pullToRefresh, String startFrom, OnNewsLoadingFinishedListener listener);
}
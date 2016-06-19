package com.naks.vk.mvp.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.mvp.view.NewsPageView;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

public interface NewsPagePresenter extends MvpPresenter<NewsPageView> {

    void loadNews(boolean pullToRefresh);

    void onItemClick(VKApiItem item, VKApiUserFull user, VKApiCommunityFull group);
}
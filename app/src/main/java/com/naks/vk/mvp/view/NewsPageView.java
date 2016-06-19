package com.naks.vk.mvp.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

public interface NewsPageView extends MvpLceView<VKApiNews> {

    void addData(VKApiNews data);

    void navigateToNewsDetailActivity(VKApiItem id, VKApiUserFull user, VKApiCommunityFull community);
}

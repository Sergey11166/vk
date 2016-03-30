package com.naks.vk.view;

import com.naks.vk.model.domain.News;

public interface NewsPageView {

    void notifyDataSetChanged();

    void showError();

    void showProgress(boolean isShow);

    void setRefreshingFalse();

    void navigateToNewsDetailActivity(News item);
}

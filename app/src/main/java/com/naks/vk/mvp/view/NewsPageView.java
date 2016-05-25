package com.naks.vk.mvp.view;

import com.naks.vk.mvp.model.viewmodel.News;

import java.util.List;

public interface NewsPageView extends MvpView {

    void showRefreshing();

    void hideRefreshing();

    void showProgress();

    void hideProgress();

    void showError(String message);

    void showLiteError(String message);

    void setNews(List<News> news, boolean maybeMore);

    void addNews(List<News> news, boolean maybeMore);

    void navigateToNewsDetailActivity(long id);
}
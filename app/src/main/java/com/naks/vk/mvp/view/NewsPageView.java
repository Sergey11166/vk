package com.naks.vk.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.naks.vk.mvp.model.viewmodel.News;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NewsPageView extends MvpView {

    void showError(String message);

    void hideError();

    void onStartLoading();

    void onFinishLoading();

    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void setNews(List<News> news, boolean maybeMore);

    void addNews(List<News> news, boolean maybeMore);

    void navigateToNewsDetailActivity(News item);
}

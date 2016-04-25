package com.naks.vk.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.naks.vk.mvp.model.viewmodel.News;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NewsPageView extends MvpView {

    void showRefreshing();

    void hideRefreshing();

    void showProgress();

    void hideProgress();

    void showError(String message);

    void showRefreshingError(String message);

    void setNews(List<News> news, boolean maybeMore);

    void addNews(List<News> news, boolean maybeMore);

    void navigateToNewsDetailActivity(News item);
}

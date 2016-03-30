package com.naks.vk.presenter;

import android.view.View;

import com.naks.vk.model.domain.News;
import com.naks.vk.model.interactor.NewsPageInteractor;

public interface NewsPagePresenter {

    void onItemClick(View v, News item);

    void onRefreshNews(NewsPageInteractor.TypeNews type);

    void onActivityCreated(NewsPageInteractor.TypeNews type);

    void onDestroy();
}

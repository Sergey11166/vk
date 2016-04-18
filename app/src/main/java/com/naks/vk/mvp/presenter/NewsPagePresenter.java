package com.naks.vk.mvp.presenter;

import android.view.View;

import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.viewmodel.News;

public interface NewsPagePresenter {

    void onItemClick(View v, News item);

    void loadNews(GetNewsInteractor.TypeNews type, final boolean pullToRefresh);
}

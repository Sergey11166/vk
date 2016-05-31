package com.naks.vk.mvp.presenter;

public interface NewsPagePresenter {

    void loadNews(boolean pullToRefresh);

    void onItemClick(long id);

    void onViewCreated();

    void onDestroyView();
}

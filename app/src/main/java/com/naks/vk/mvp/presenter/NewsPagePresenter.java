package com.naks.vk.mvp.presenter;

public interface NewsPagePresenter extends MvpPresenter {

    void loadNews(boolean pullToRefresh);
    void onItemClick(long id);

    void onViewCreated();
    void onDestroyView();
}

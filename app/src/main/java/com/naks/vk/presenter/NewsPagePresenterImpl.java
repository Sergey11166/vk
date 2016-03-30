package com.naks.vk.presenter;

import android.util.Log;
import android.view.View;

import com.naks.vk.model.domain.News;
import com.naks.vk.model.interactor.NewsPageInteractor;
import com.naks.vk.view.NewsPageView;
import com.naks.vk.view.adapter.NewsRecyclerAdapter;
import com.naks.vk.view.fragment.NewsPageFragment;

import java.util.List;

import javax.inject.Inject;

public class NewsPagePresenterImpl implements
        NewsPagePresenter, NewsPageInteractor.OnNewsFinishedListener {

    private static final String TAG = "NewsPagePresenterImpl";

    @Inject NewsPageView view;
    @Inject NewsPageInteractor interactor;
    @Inject NewsRecyclerAdapter adapter;

    public NewsPagePresenterImpl(NewsPageFragment fragment) {
        fragment.getComponent().inject(this);
    }

    @Override
    public void onItemClick(View v, News item) {
        view.navigateToNewsDetailActivity(item);
    }

    @Override
    public void onActivityCreated(NewsPageInteractor.TypeNews type) {
        view.showProgress(true);
        interactor.requestNews(type, this);
    }

    @Override
    public void onRefreshNews(NewsPageInteractor.TypeNews type) {
        interactor.requestNews(type, this);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        view = null;
        interactor.onDestroy();
    }

    @Override
    public void onSuccess(List<News> result) {
        setDataToAdapter(result);
        view.notifyDataSetChanged();
        view.showProgress(false);
        view.setRefreshingFalse();
    }

    @Override
    public void onError() {
        view.showProgress(false);
        view.setRefreshingFalse();
        view.showError();
    }

    private void setDataToAdapter(List<News> data) {
        adapter.setData(data);
    }
}

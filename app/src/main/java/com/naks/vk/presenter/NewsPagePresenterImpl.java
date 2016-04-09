package com.naks.vk.presenter;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.naks.vk.model.domain.News;
import com.naks.vk.model.interactor.GetNewsInteractor;
import com.naks.vk.view.NewsPageView;
import com.naks.vk.view.fragment.NewsPageFragment;

import java.util.List;

import javax.inject.Inject;

public class NewsPagePresenterImpl extends MvpBasePresenter<NewsPageView> implements
        NewsPagePresenter, GetNewsInteractor.OnNewsFinishedListener {

    private static final String TAG = "NewsPagePresenterImpl";

    @Inject
    GetNewsInteractor interactor;

    public NewsPagePresenterImpl(NewsPageFragment fragment) {
        fragment.getComponent().inject(this);
    }

    @Override
    public void onItemClick(View v, News item) {
        assert getView() != null;
        getView().navigateToNewsDetailActivity(item);
    }

    @Override
    public void loadNews(GetNewsInteractor.TypeNews type, boolean pullToRefresh) {
        assert getView() != null;
        getView().showLoading(pullToRefresh);
        interactor.loadNews(type, this, pullToRefresh);
    }

    @Override
    public void onSuccess(List<News> result) {
        if (isViewAttached()) {
            assert getView() != null;
            getView().setData(result);
            getView().showContent();
        }
    }

    @Override
    public void onError(Exception e, boolean pullToRefresh) {
        if (isViewAttached()) {
            assert getView() != null;
            getView().showError(e, pullToRefresh);
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            interactor.cancelLoader();
        }
    }

    @Override
    public void attachView(NewsPageView view) {
        super.attachView(view);
    }
}

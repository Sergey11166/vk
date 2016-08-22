package com.naks.vk.mvp.presenter.impl;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.naks.vk.R;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.OnNewsLoadingFinishedListener;
import com.naks.vk.mvp.model.interactor.TypeNews;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.view.NewsPageView;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

public class NewsPagePresenterImpl extends MvpBasePresenter <NewsPageView>
        implements NewsPagePresenter, OnNewsLoadingFinishedListener {

    private static final String TAG = "NewsPagePresenter";

    GetNewsInteractor interactor;
    TypeNews typeNews;
    String startFrom;
    NewsPageView view;

    public NewsPagePresenterImpl(GetNewsInteractor interactor, TypeNews typeNews) {
        Log.d(TAG, "constructor " + toString());
        this.interactor = interactor;
        this.typeNews = typeNews;
    }

    @Override
    public void loadNews(boolean pullToRefresh) {
        Log.d(TAG, "loadNews(" + pullToRefresh + ")");
        String startFrom = null;
        if (!pullToRefresh) startFrom = this.startFrom;
        interactor.get(typeNews, pullToRefresh, startFrom, this);
        if (!pullToRefresh && this.startFrom != null) return;
        showLoading(pullToRefresh);
    }

    @Override
    public void onItemClick(VKApiItem item, VKApiUserFull user, VKApiCommunityFull group) {
        Log.d(TAG, "inItemClick(" + item + ")");
        if (view == null) view = getView();
        assert view != null;
        view.navigateToNewsDetailActivity(item, user, group);
    }

    @Override
    public void onMenuItemClick(Context context, VKApiItem item, int menuItemId) {
        Log.d(TAG, "onMenuItemClick(VKApiItem, " + menuItemId + ")");
        switch (menuItemId) {
            case R.id.popup_news_copy:
                String url = "http://vk.com/wall"
                        .concat(String.valueOf(item.source_id))
                        .concat("_")
                        .concat(String.valueOf(item.post_id));
                ClipboardManager clipboardManager = (ClipboardManager) context
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("VK_WALL_ID_URL", url);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, context.getString(R.string.toast_reference)
                                .concat(" ").concat(url).concat(" ")
                                .concat(context.getString(R.string.toast_is_copied)),
                        Toast.LENGTH_SHORT).show();
                return;

            case R.id.popup_news_complains:

                return;

            case R.id.popup_news_not_interesting:
        }
    }

    @Override
    public void attachView(NewsPageView view) {
        super.attachView(view);
        Log.d(TAG, "attachView(" + view.toString() + ")");
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        Log.d(TAG, "detachView(" + retainInstance + ")");
    }

    @Override
    public void onLoadingSuccess(VKApiNews news, boolean pullToRefresh) {
        if (pullToRefresh || startFrom == null) {
            setData(news);
        } else {
            addData(news);
        }
        this.startFrom = news.next_from;
    }

    @Override
    public void onLoadingFailed(VKError error, boolean pullToRefresh) {
        Log.d(TAG, "showError(" + error.getClass().getSimpleName() + " , " + pullToRefresh + ")");
        if (!pullToRefresh && startFrom != null) {
            showErrorLoadPage();
        } else {
            showError(error, pullToRefresh);
        }
    }

    void showLoading(boolean pullToRefresh) {
        if (view == null) view = getView();
        assert view != null;
        view.showLoading(pullToRefresh);
}

    void setData(VKApiNews news) {
        Log.d(TAG, "setData()");
        if (view == null) view = getView();
        assert view != null;
        view.setData(news);
        view.showContent();
    }

    void addData(VKApiNews news) {
        Log.d(TAG, "addData()");
        if (view == null) view = getView();
        assert view != null;
        view.addData(news);
    }

    void showErrorLoadPage() {
        if (view == null) view = getView();
        assert view != null;
        view.showErrorLoadPage();
    }

    void showError(VKError error, boolean pullToRefresh) {
        if (view == null) view = getView();
        assert view != null;
        view.showError(new Exception(error.toString()), pullToRefresh);
    }
}

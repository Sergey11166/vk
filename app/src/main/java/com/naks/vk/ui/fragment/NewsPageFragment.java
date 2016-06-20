package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.naks.vk.R;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.di.component.HasComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.di.component.NewsPageComponent;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.view.NewsPageView;
import com.naks.vk.ui.adapter.NewsRecyclerAdapter;
import com.naks.vk.ui.util.NewsErrorMessage;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiUserFull;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsPageFragment extends MvpLceViewStateNestedBaseFragment<SwipeRefreshLayout,
        VKApiNews, NewsPageView, NewsPagePresenter>
        implements NewsPageView, HasComponent<NewsPageComponent> {

    private static final String TAG = "NewsPageFragment";
    public static final String KEY_NEWS_TYPE = NewsPageFragment.class.getName() + "_keyNewsType";

    private NewsPageComponent component;

    @Inject NewsRecyclerAdapter adapter;

    @BindView (R.id.recyclerView) RecyclerView recyclerView;

    private Unbinder unbinder;

    @Override
    protected void setupComponent(MainComponent component) {
        Log.d(TAG, "setupComponent(" + component.toString() + ")");
        this.component = component.plus(new NewsPageModule(this));
        this.component.inject(this);
    }

    @Override
    public NewsPageComponent getComponent() {
        Log.d(TAG, "getComponent()");
        return component;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public LceViewState<VKApiNews, NewsPageView> createViewState() {
        Log.d(TAG, "createViewState()");
        return component.getViewState();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, inflater.toString() + container + savedInstanceState);
        View view = inflater.inflate(R.layout.news_page_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        Log.d(TAG, view.toString() + savedInstance);
        contentView.setOnRefreshListener(() -> loadData(true));
        adapter.setOnNewsItemClickListener((item, user, group) ->
                presenter.onItemClick(item, user, group));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView()");
        unbinder.unbind();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        Log.d(TAG, "loadData(" + pullToRefresh + ")");
        presenter.loadNews(pullToRefresh);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        Log.d(TAG, "getErrorMessage(" + e + ", " + pullToRefresh + ")");
        return NewsErrorMessage.get(e, pullToRefresh, getActivity());
    }

    @NonNull
    @Override
    public NewsPagePresenter createPresenter() {
        Log.d(TAG, "createPresenter()");
        return component.getPresenter();
    }

    @Override
    public void setData(VKApiNews data) {
        Log.d(TAG, "setData(" + data + ")");
        adapter.setData(data);
    }

    @Override
    public void addData(VKApiNews data) {
        Log.d(TAG, "addData(" + data + ")");
        adapter.addData(data);
    }

    @Override
    public void navigateToNewsDetailActivity(VKApiItem item, VKApiUserFull user,
                                             VKApiCommunityFull group) {
        Log.d(TAG, "navigateToNewsDetailActivity(" + item + ")");
    }

    @Override
    public VKApiNews getData() {
        Log.d(TAG, "getData()");
        return adapter == null ? null : adapter.getData();
    }
}

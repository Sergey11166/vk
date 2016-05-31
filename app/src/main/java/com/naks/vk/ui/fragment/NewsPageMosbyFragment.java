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
import com.naks.vk.di.component.HasComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.di.component.NewsPageComponent;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.mvp.model.viewmodel.News;
import com.naks.vk.mvp.presenter.NewsPageMosbyPresenter;
import com.naks.vk.mvp.view.NewsPageMosbyView;
import com.naks.vk.ui.adapter.NewsRecyclerAdapter;
import com.naks.vk.ui.util.NewsErrorMessage;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsPageMosbyFragment extends MvpLceViewStateNestedFragment<SwipeRefreshLayout,
        List<News>, NewsPageMosbyView, NewsPageMosbyPresenter>
        implements NewsPageMosbyView, HasComponent<NewsPageComponent> {

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
    public LceViewState<List<News>, NewsPageMosbyView> createViewState() {
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
        adapter.setOnNewsItemClickListener(id -> presenter.onItemClick(id));
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
    public NewsPageMosbyPresenter createPresenter() {
        Log.d(TAG, "createPresenter()");
        return component.getPresenter();
    }

    @Override
    public void setData(List<News> data) {
        Log.d(TAG, "setData(" + data + ")");
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addData(List<News> data) {
        Log.d(TAG, "addData(" + data + ")");

    }

    @Override
    public void navigateToNewsDetailActivity(long id) {
        Log.d(TAG, "navigateToNewsDetailActivity(" + id + ")");
    }

    @Override
    public List<News> getData() {
        Log.d(TAG, "getData()");
        return adapter == null ? null : adapter.getData();
    }
}

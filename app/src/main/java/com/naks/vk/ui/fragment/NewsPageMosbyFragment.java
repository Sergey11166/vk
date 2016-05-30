package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.naks.vk.R;
import com.naks.vk.mvp.model.viewmodel.News;
import com.naks.vk.mvp.presenter.NewsPageMosbyPresenter;
import com.naks.vk.mvp.presenter.impl.NewsPageMosbyPresenterImpl;
import com.naks.vk.mvp.view.NewsPageMosbyView;
import com.naks.vk.ui.adapter.NewsRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class NewsPageMosbyFragment extends MvpLceViewStateFragment<SwipeRefreshLayout,
        List<News>, NewsPageMosbyView, NewsPageMosbyPresenter>
        implements NewsPageMosbyView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "NewsPageFragment";
    public static final String KEY_NEWS_TYPE = NewsPageFragment.class.getName() + "_keyNewsType";

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private NewsRecyclerAdapter adapter;
    private Unbinder unbinder;

    @NonNull
    @Override
    public LceViewState<List<News>, NewsPageMosbyView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_page_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

        contentView.setOnRefreshListener(this);
        adapter = new NewsRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadNews(pullToRefresh);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "error";
        //return CountriesErrorMessage.get(e, pullToRefresh, getActivity());
    }

    @NonNull
    @Override
    public NewsPageMosbyPresenter createPresenter() {
        return new NewsPageMosbyPresenterImpl();
    }

    @Override
    public void setData(List<News> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            // Workaround for measure bug: https://code.google.com/p/android/issues/detail?id=77712
            contentView.post(() -> contentView.setRefreshing(true));
        }
    }

    @Override
    public List<News> getData() {
        return adapter == null ? null : adapter.getData();
    }

    @Override public boolean isRetainInstance() {
        return true;
    }
    @Override public boolean shouldInstanceBeRetained() {
        return true;
    }
}

package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.ui.activity.MainActivity;

public abstract class MvpLceViewStateNestedBaseFragment<
        CV extends SwipeRefreshLayout,
        M, V extends MvpLceView<M>,
        P extends MvpPresenter<V>>
        extends MvpLceViewStateFragment<CV, M, V, P> {

    private static final String TAG = "MvpLceNestedFragment";

    private boolean isVisibleToUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setupComponent(((MainActivity)getActivity()).getComponent());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
    }

    @Override
    protected void showLightError(String msg) {
        Log.d(TAG, "showLightError(" + msg + ")");
        if (isVisibleToUser) super.showLightError(msg);
    }

    @Override
    public void showContent() {
        super.showContent();
        Log.d(TAG, "showContent()");
        contentView.post(() -> contentView.setRefreshing(false));
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        Log.d(TAG, "showError(" + e + ", " + pullToRefresh + ")");
        contentView.post(() -> contentView.setRefreshing(false));
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        Log.d(TAG, "showLoading(" + pullToRefresh + ")");
        contentView.post(() -> contentView.setRefreshing(true));
    }

    @Override
    public boolean isRetainInstance() {
        Log.d(TAG, "isRetainInstance()");
        return true;
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        Log.d(TAG, "shouldInstanceBeRetained()");
        return true;
    }

    protected abstract void setupComponent(MainComponent component);
}

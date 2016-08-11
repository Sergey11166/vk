package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;

public abstract class MvpLceViewStateDaggerBaseFragment<
        CV extends SwipeRefreshLayout,
        M, V extends MvpLceView<M>,
        P extends MvpPresenter<V>, C>
        extends MvpLceViewStateFragment<CV, M, V, P> {

    protected C component;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setupComponent();
        super.onCreate(savedInstanceState);
    }

    public C getComponent() {
        return component;
    }

    protected abstract void setupComponent();
}

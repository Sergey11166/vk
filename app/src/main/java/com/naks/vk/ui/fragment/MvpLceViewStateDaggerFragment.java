package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.naks.vk.di.component.HasComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.ui.activity.MainActivity;

public abstract class MvpLceViewStateDaggerFragment<
        CV extends SwipeRefreshLayout,
        M, V extends MvpLceView<M>,
        P extends MvpPresenter<V>, C>
        extends MvpLceViewStateFragment<CV, M, V, P> implements HasComponent<C> {

    C component;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setupComponent(((MainActivity)getActivity()).getComponent());
        super.onCreate(savedInstanceState);
    }

    @Override
    public C getComponent() {
        return component;
    }

    protected abstract void setupComponent(MainComponent component);
}

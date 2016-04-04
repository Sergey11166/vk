package com.naks.vk.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.naks.vk.di.HasComponent;

public abstract class MosbyBaseFragment<CV extends View,
        M,
        V extends MvpLceView<M>,
        P extends MvpPresenter<V>,
        T,
        HC extends HasComponent<T>> extends MvpLceViewStateFragment<CV, M, V, P> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //noinspection unchecked
        setupComponent(((HC)getActivity()).getComponent());
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void setupComponent(T component);
}

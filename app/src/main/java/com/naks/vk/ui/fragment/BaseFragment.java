package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.naks.vk.di.HasComponent;

public abstract class BaseFragment<T, HC extends HasComponent<T>> extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //noinspection unchecked
        setupComponent(((HC)getActivity()).getComponent());
    }

    protected abstract void setupComponent(T component);
}

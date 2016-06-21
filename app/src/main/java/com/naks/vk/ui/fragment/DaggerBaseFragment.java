package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.naks.vk.di.component.HasComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.ui.activity.MainActivityDagger;

public abstract class DaggerBaseFragment<C> extends Fragment implements HasComponent<C> {

    protected C component;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setupComponent(((MainActivityDagger)getActivity()).getComponent());
        super.onCreate(savedInstanceState);
    }

    @Override
    public C getComponent() {
        return component;
    }

    protected abstract void setupComponent(MainComponent component);
}
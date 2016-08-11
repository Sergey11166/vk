package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class DaggerBaseFragment<C> extends Fragment {

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
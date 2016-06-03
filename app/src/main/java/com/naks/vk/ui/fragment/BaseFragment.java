package com.naks.vk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.naks.vk.di.component.MainComponent;
import com.naks.vk.ui.activity.MainActivity;

public abstract class BaseFragment extends FixFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(((MainActivity)getActivity()).getComponent());
    }

    protected abstract void setupComponent(MainComponent component);
}
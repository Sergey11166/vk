package com.naks.vk.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.naks.vk.App;
import com.naks.vk.di.component.AppComponent;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupComponent(App.get(getActivity()).getComponent());
    }

    protected abstract void setupComponent(AppComponent appComponent);
}

package com.naks.vk.di.component;

import android.content.Context;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.view.fragment.NewsTabsFragment;
import com.squareup.otto.Bus;

import dagger.Component;

@PerFragment
@Component(dependencies = NewsTabFragmentComponent.HasNewsTabFragmentDepends.class)
public interface NewsTabFragmentComponent {
    void inject(NewsTabsFragment newsTabsFragment);

    interface HasNewsTabFragmentDepends {
        Bus bus();
        Context context();
    }
}

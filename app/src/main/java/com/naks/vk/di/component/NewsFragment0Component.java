package com.naks.vk.di.component;

import android.content.Context;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.view.fragment.NewsFragment0;
import com.squareup.otto.Bus;

import dagger.Component;

@PerFragment
@Component(dependencies = NewsFragment0Component.HasNewsFragment0Depends.class)
public interface NewsFragment0Component {
    void inject(NewsFragment0 newsFragment0);

    interface HasNewsFragment0Depends {
        Bus bus();
        Context context();
    }
}

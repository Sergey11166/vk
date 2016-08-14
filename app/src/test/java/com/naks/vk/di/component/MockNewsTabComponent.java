package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.di.module.MockNewsTabModule;
import com.naks.vk.mvp.presenter.impl.NewsTabPresenterTest;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = MockNewsTabModule.class)
public interface MockNewsTabComponent {
    void inject(NewsTabPresenterTest test);
}

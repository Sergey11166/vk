package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.di.module.MockNewsPageModule;
import com.naks.vk.mvp.presenter.impl.NewsPagePresenterTest;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = MockNewsPageModule.class)
public interface MockNewsPageComponent {
    void inject(NewsPagePresenterTest test);
}

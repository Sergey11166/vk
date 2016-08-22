package com.naks.vk.di.module;

import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.OnNewsLoadingFinishedListener;
import com.naks.vk.mvp.model.interactor.TypeNews;
import com.naks.vk.mvp.presenter.impl.NewsPagePresenterImpl;
import com.naks.vk.mvp.view.NewsPageView;

import org.json.JSONException;
import org.json.JSONObject;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@Module
public class MockNewsPageModule {

    @Provides
    @PerFragment
    NewsPageView provideView() {
        return mock(NewsPageView.class);
    }

    @Provides
    @PerFragment
    GetNewsInteractor provideInteractor() {

        String from = "{response: {items: [], profiles: [], groups: [], next_from: 'test'}}";

        GetNewsInteractor interactor = mock(GetNewsInteractor.class);

        //pullToRefresh
        doAnswer(invocation -> {
            VKApiNews news = new VKApiNews(new JSONObject(from));
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingSuccess(news, true);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(true), anyString(), any(OnNewsLoadingFinishedListener.class));

        //addData
        doAnswer(invocation -> {
            VKApiNews news = new VKApiNews(new JSONObject(from));
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingSuccess(news, false);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(false), anyString(), any(OnNewsLoadingFinishedListener.class));

        //firstLoadNews
        doAnswer(invocation -> {
            VKApiNews news = new VKApiNews(new JSONObject(from));
            news.next_from = null;
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingSuccess(news, false);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(false), eq(null), any(OnNewsLoadingFinishedListener.class));

        return interactor;
    }

    @Provides
    @PerFragment
    NewsPagePresenterImpl providePresenterNews(GetNewsInteractor interactor) {
        return new NewsPagePresenterImpl(interactor, TypeNews.NEWS);
    }
}

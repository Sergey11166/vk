package com.naks.vk.di.module;

import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.di.anotation.TNCommunities;
import com.naks.vk.di.anotation.TNFriends;
import com.naks.vk.di.anotation.TNNews;
import com.naks.vk.di.anotation.TNRecommendations;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.OnNewsLoadingFinishedListener;
import com.naks.vk.mvp.model.interactor.TypeNews;
import com.naks.vk.mvp.presenter.impl.NewsPagePresenterImpl;
import com.naks.vk.mvp.view.NewsPageView;

import org.json.JSONObject;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Matchers.any;
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
        GetNewsInteractor interactor = mock(GetNewsInteractor.class);

        doAnswer(invocation -> {
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[0])
                    .onLoadingSuccess(new VKApiNews(new JSONObject("data")), false);
            return null;
        }).when(interactor)
                .get(TypeNews.NEWS, false, "startFrom", any(OnNewsLoadingFinishedListener.class));

        doAnswer(invocation -> {
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[0])
                    .onLoadingSuccess(new VKApiNews(new JSONObject("data")), true);
            return null;
        }).when(interactor)
                .get(TypeNews.NEWS, true, "startFrom", any(OnNewsLoadingFinishedListener.class));

        doAnswer(invocation -> {
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[0])
                    .onLoadingSuccess(new VKApiNews(new JSONObject("data")), false);
            return null;
        }).when(interactor)
                .get(TypeNews.NEWS, false, null, any(OnNewsLoadingFinishedListener.class));

        return interactor;
    }

    @TNNews
    @Provides
    @PerFragment
    NewsPagePresenterImpl providePresenterNews(GetNewsInteractor interactor) {
        return new NewsPagePresenterImpl(interactor, TypeNews.NEWS);
    }

    @Provides
    @PerFragment
    @TNRecommendations
    NewsPagePresenterImpl providePresenterRecommendations(GetNewsInteractor interactor) {
        return new NewsPagePresenterImpl(interactor, TypeNews.RECOMMENDATIONS);
    }

    @Provides
    @TNFriends
    @PerFragment
    NewsPagePresenterImpl providePresenterFriends(GetNewsInteractor interactor) {
        return new NewsPagePresenterImpl(interactor, TypeNews.FRIENDS);
    }

    @Provides
    @PerFragment
    @TNCommunities
    NewsPagePresenterImpl providePresenterCommunities(GetNewsInteractor interactor) {
        return new NewsPagePresenterImpl(interactor, TypeNews.COMMUNITIES);
    }
}

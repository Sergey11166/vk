package com.naks.vk.model.interactor;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.naks.vk.model.domain.News;
import com.naks.vk.view.fragment.NewsPageFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsPageInteractorImpl implements NewsPageInteractor {

    private static final String TAG = "NewsPageInteractorImpl";

    @Inject Context context;

    public NewsPageInteractorImpl(NewsPageFragment fragment) {
        fragment.getComponent().inject(this);
    }

    @Override
    public void requestNews(final TypeNews type, final OnNewsFinishedListener listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (type) {
                    case NEWS:
                        if (context != null) {
                            listener.onSuccess(createSampleNews("simple news"));
                        } else {
                            listener.onError();
                        }
                        break;
                    case RECOMMENDATIONS:
                        if (context != null) {
                            listener.onSuccess(createSampleNews("simple recommendations"));
                        } else {
                            listener.onError();
                        }
                        break;
                    case FRIENDS:
                        if (context != null) {
                            listener.onSuccess(createSampleNews("simple friends"));
                        } else {
                            listener.onError();
                        }
                        break;
                    case COMMUNITIES:
                        if (context != null) {
                            listener.onSuccess(createSampleNews("simple communities"));
                        } else {
                            listener.onError();
                        }
                        break;
                }
            }
        }, 2000);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    private List<News> createSampleNews(String sampleText) {
        List<News> result = new ArrayList<>(100);
        for (int i=0; i<100; i++) {
            News news = new News();
            news.setContent(sampleText + i);
            result.add(news);
        }
        return result;
    }
}

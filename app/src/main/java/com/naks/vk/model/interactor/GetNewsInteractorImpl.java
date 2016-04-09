package com.naks.vk.model.interactor;

import android.content.Context;
import android.os.AsyncTask;

import com.naks.vk.model.domain.News;
import com.naks.vk.view.fragment.NewsPageFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class GetNewsInteractorImpl implements GetNewsInteractor {

    private static final String TAG = "GetNewsInteractorImpl";

    @Inject Context context;

    private NewsAsyncLoader loader;

    public GetNewsInteractorImpl(NewsPageFragment fragment) {
        fragment.getComponent().inject(this);
    }

    @Override
    public void loadNews(final TypeNews type, OnNewsFinishedListener listener, boolean pullToRefresh) {
        if (loader != null && !loader.isCancelled()) {
            loader.cancel(true);
        }
        loader = new NewsAsyncLoader(type, listener, pullToRefresh);
        loader.execute();
    }

    @Override
    public void cancelLoader() {
        if (loader != null) {
            loader.cancel(true);
        }
    }

    private class NewsAsyncLoader extends AsyncTask<Void, Void, List<News>> {

        private GetNewsInteractor.TypeNews type;
        private GetNewsInteractor.OnNewsFinishedListener listener;
        private boolean pullToRefresh;

        public NewsAsyncLoader(GetNewsInteractor.TypeNews type,
                               GetNewsInteractor.OnNewsFinishedListener listener,
                               boolean pullToRefresh) {
            this.listener = listener;
            this.type = type;
            this.pullToRefresh = pullToRefresh;
        }

        @Override
        protected List<News> doInBackground(Void... params) {

            try {
                if (pullToRefresh) Thread.sleep(3000);
            } catch (InterruptedException e) {
                return null;
            }

            List<News> news = new ArrayList<>();

            switch (type) {
                case NEWS:
                    news = createSampleNews("simple news");
                    break;
                case RECOMMENDATIONS:
                    news = createSampleNews("simple recommendations");
                    break;
                case FRIENDS:
                    news = createSampleNews("simple friends");
                    break;
                case COMMUNITIES:
                    //error
                    break;
            }
            Collections.shuffle(news);
            return news;
        }

        @Override
        protected void onPostExecute(List<News> news) {

            if (isCancelled() || news == null) {
                return;
            }

            if (news.isEmpty()) {
                listener.onError(new Exception("Error loading"), pullToRefresh);
            } else {
                listener.onSuccess(news);
            }
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
}

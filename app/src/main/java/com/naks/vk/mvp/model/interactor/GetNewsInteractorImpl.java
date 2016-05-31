package com.naks.vk.mvp.model.interactor;

import android.os.AsyncTask;

import com.naks.vk.mvp.model.viewmodel.News;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GetNewsInteractorImpl implements GetNewsInteractor {

    private NewsAsyncLoader loader;
    private Random random;

    public GetNewsInteractorImpl(Random random) {
        this.random = random;
    }

    @Override
    public void get(final TypeNews type, boolean pullToRefresh,
                    OnNewsLoadingFinishedListener listener) {
        if (loader != null && !loader.isCancelled()) {
            loader.cancel(true);
        }
        loader = new NewsAsyncLoader(type, pullToRefresh, listener);
        loader.execute();
    }

    private class NewsAsyncLoader extends AsyncTask<Void, Void, List<News>> {

        private GetNewsInteractor.TypeNews type;
        private GetNewsInteractor.OnNewsLoadingFinishedListener listener;
        private boolean pullToRefresh;

        public NewsAsyncLoader(GetNewsInteractor.TypeNews type,
                               boolean pullToRefresh,
                               GetNewsInteractor.OnNewsLoadingFinishedListener listener) {
            this.listener = listener;
            this.type = type;
            this.pullToRefresh = pullToRefresh;
        }

        @Override
        protected List<News> doInBackground(Void... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return null;
            }

            List<News> news = null;

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
                    news = createSampleNews("simple communities");
                    break;
            }
            if (news != null) Collections.shuffle(news);
            return news;
        }

        @Override
        protected void onPostExecute(List<News> news) {

            if (isCancelled()) {
                return;
            }

            if (news == null) {
                listener.onLoadingFailed(new Exception("Error loading"), pullToRefresh);
            } else {
                listener.onLoadingSuccess(news);
            }
        }

        private List<News> createSampleNews(String sampleText) {
            if (random.nextBoolean()) return null;
            List<News> result = new ArrayList<>(100);
            for (int i=0; i<100; i++) {
                News news = new News();
                news.setId(i);
                StringBuilder sb = new StringBuilder(sampleText);
                news.setContent(sb.append(i).toString());
                result.add(news);
            }
            return result;
        }
    }
}

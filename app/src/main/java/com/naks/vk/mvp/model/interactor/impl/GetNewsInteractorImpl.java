package com.naks.vk.mvp.model.interactor.impl;

import android.util.Log;

import com.naks.vk.BuildConfig;
import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.OnNewsLoadingFinishedListener;
import com.naks.vk.mvp.model.interactor.TypeNews;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;

/**
 * Interactor for get vk news
 */
public class GetNewsInteractorImpl implements GetNewsInteractor {

    private static final String TAG = "GetNewsInteractorImpl";
    private static final String COUNT = "20";

    @Override
    public void get(TypeNews type, boolean pullToRefresh, String startFrom,
                    OnNewsLoadingFinishedListener listener) {

        VKRequest vkRequest = null;

        switch (type) {
            case NEWS:
                vkRequest = new VKRequest("newsfeed.get", VKParameters.from(
                        VKApiConst.FILTERS, "post"));
                break;
            case RECOMMENDATIONS:
                vkRequest = new VKRequest("newsfeed.getRecommended");
                break;
            case FRIENDS:
                vkRequest = new VKRequest("newsfeed.get", VKParameters.from(
                        VKApiConst.FILTERS, "post,photo,photo_tag",
                        "source_ids", "friends,following"));
                break;
            case COMMUNITIES:
                vkRequest = new VKRequest("newsfeed.get", VKParameters.from(
                        VKApiConst.FILTERS, "post,photo",
                        "source_ids", "groups,pages"));
        }

        vkRequest.addExtraParameter("start_from", startFrom);
        vkRequest.addExtraParameter("count", COUNT);

        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                try {
                    listener.onLoadingSuccess(new VKApiNews(response.json), pullToRefresh);
                } catch (JSONException e) {
                    if(BuildConfig.DEBUG) Log.e(TAG, "Pars response failed", e);
                }
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {

            }

            @Override
            public void onError(VKError error) {
                listener.onLoadingFailed(error, pullToRefresh);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType,
                                   long bytesLoaded, long bytesTotal) {

            }
        });
    }
}

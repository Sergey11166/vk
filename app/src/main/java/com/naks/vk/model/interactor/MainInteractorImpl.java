package com.naks.vk.model.interactor;

import android.content.Context;
import android.util.Log;

import com.naks.vk.view.activity.MainActivity;

import javax.inject.Inject;

public class MainInteractorImpl implements MainInteractor {

    private static final String TAG = "MainInteractorImpl";

    @Inject Context context;

    public MainInteractorImpl(MainActivity activity) {
        activity.getComponent().inject(this);
    }

    @Override
    public String testAction() {
        Log.d(TAG, context.toString());
        return "ResultTestAction";
    }
}

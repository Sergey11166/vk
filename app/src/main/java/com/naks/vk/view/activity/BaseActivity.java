package com.naks.vk.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.naks.vk.App;
import com.naks.vk.di.component.AppComponent;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initDiComponent();
        super.onCreate(savedInstanceState);
    }

    abstract protected void initDiComponent();

    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getComponent();
    }
}

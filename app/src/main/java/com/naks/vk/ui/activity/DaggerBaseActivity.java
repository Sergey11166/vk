package com.naks.vk.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.naks.vk.App;
import com.naks.vk.di.component.AppComponent;
import com.naks.vk.di.component.HasComponent;

public abstract class DaggerBaseActivity<C> extends AppCompatActivity implements HasComponent<C> {

    protected C component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupComponent(App.get(this).getComponent());
        super.onCreate(savedInstanceState);
    }

    @Override
    public C getComponent() {
        return component;
    }

    protected abstract void setupComponent(AppComponent appComponent);
}

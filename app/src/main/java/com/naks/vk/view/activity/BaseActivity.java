package com.naks.vk.view.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.naks.vk.App;
import com.naks.vk.di.component.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.get(this).getComponent());
    }

    protected abstract void setupComponent(AppComponent appComponent);
}

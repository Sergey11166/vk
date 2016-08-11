package com.naks.vk.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class DaggerBaseActivity<C> extends AppCompatActivity {

    protected C component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupComponent();
        super.onCreate(savedInstanceState);
    }

    public C getComponent() {
        return component;
    }

    protected abstract void setupComponent();
}

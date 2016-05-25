package com.naks.vk.mvp.view;

public interface LoginView extends MvpView {

    void showLoginScreen(String[] scope);

    void navigateToMainScreen();
}
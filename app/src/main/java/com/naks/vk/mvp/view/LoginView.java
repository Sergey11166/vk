package com.naks.vk.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface LoginView extends MvpView {

    void showLoginScreen(String[] scope);

    @StateStrategyType(SkipStrategy.class)
    void navigateToMainScreen();
}
package com.naks.vk.mvp.view;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface MainView extends MvpView {

    void showNewsTabFragment();

    void closeDrawer();

    void pressBack();
}

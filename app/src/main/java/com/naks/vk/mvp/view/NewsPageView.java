package com.naks.vk.mvp.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.naks.vk.mvp.model.viewmodel.News;

import java.util.List;

public interface NewsPageView extends MvpLceView<List<News>> {

    void navigateToNewsDetailActivity(News item);
}

package com.naks.vk.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.naks.vk.model.domain.News;

import java.util.List;

public interface NewsPageView extends MvpLceView<List<News>> {

    void navigateToNewsDetailActivity(News item);
}

package com.naks.vk.mvp.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.naks.vk.mvp.model.viewmodel.News;

import java.util.List;

public interface NewsPageMosbyView extends MvpLceView<List<News>> {

    void addData(List<News> data);

    void navigateToNewsDetailActivity(long id);
}

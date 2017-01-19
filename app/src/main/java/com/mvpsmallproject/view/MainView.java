package com.mvpsmallproject.view;

import com.mvpsmallproject.base.MvpView;
import com.mvpsmallproject.model.Ribot;

import java.util.List;

public interface MainView extends MvpView {

    void showLoading();
    void hideLoading();
    void showRibotsProfiles(List<Ribot> ribots);
    void showEmptyStateRibotsProfiles();
    void showError();

}

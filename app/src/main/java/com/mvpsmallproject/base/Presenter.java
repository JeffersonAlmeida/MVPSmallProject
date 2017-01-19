package com.mvpsmallproject.base;

public interface Presenter<J extends MvpView> {

    void attachView(J mainView);
    void detachView();

}

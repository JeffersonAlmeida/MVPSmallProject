package com.mvpsmallproject.base;

public class BasePresenter<J extends MvpView> implements Presenter<J> {

    private J mvpView;

    @Override
    public void attachView(J view) {
        mvpView = view;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }

    public J getMvpView() {
        return mvpView;
    }
}

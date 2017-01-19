package com.mvpsmallproject.presenter;

import com.mvpsmallproject.base.BasePresenter;
import com.mvpsmallproject.model.Ribot;
import com.mvpsmallproject.remote.Service;
import com.mvpsmallproject.view.MainView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {

    private Service service;

    public MainPresenter(Service service) {
        this.service = service;
    }

    public void getRibotsProfiles(){
        getMvpView().showLoading();
        service.getRibots()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Ribot>>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Ribot> ribots) {
                        if ( ribots == null || ribots.isEmpty() ){
                            getMvpView().showEmptyStateRibotsProfiles();
                        }else {
                            getMvpView().showRibotsProfiles(ribots);
                        }
                    }
                });
    }

}

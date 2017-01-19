package com.mvpsmallproject.presenter;

import com.mvpsmallproject.model.Ribot;
import com.mvpsmallproject.remote.Service;
import com.mvpsmallproject.view.MainView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import util.RxSchedulersOverrideRule;
import util.TestDataFabric;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private Service service;

    private MainPresenter mainPresenter;

    private MainView mainView;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() throws Exception {
        service = Mockito.mock(Service.class);
        mainPresenter = new MainPresenter(service);
        mainView = Mockito.mock(MainView.class);

        mainPresenter.attachView(mainView);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        mainPresenter.detachView();
        mainView = null;
    }

    @Test
    public void testSubscribeRibotsProfileList() throws Exception {

        List<Ribot> ribotsList = TestDataFabric.createRibotsList();
        Mockito.when(service.getRibots()).thenReturn(Observable.just(ribotsList));

        mainPresenter.getRibotsProfiles();

        Mockito.verify(mainView).showLoading();
        Mockito.verify(mainView).showRibotsProfiles(Mockito.<Ribot>anyList());
        Mockito.verify(mainView).hideLoading();

        Mockito.verify(mainView, Mockito.never()).showError();

    }

    @Test
    public void testGetRibotsProfilesFails() throws Exception {

        Mockito.when(service.getRibots())
                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));

        mainPresenter.getRibotsProfiles();

        Mockito.verify(mainView).showLoading();
        Mockito.verify(mainView).hideLoading();
        Mockito.verify(mainView).showError();
    }

    @Test
    public void testGetRibotsEmptyState() throws Exception {

        Mockito.when(service.getRibots())
                .thenReturn(Observable.just(Collections.<Ribot>emptyList()));

        mainPresenter.getRibotsProfiles();

        Mockito.verify(mainView).showLoading();
        Mockito.verify(mainView).hideLoading();
        Mockito.verify(mainView).showEmptyStateRibotsProfiles();

    }

}
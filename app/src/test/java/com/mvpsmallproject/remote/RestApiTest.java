package com.mvpsmallproject.remote;

import com.mvpsmallproject.model.Profile;
import com.mvpsmallproject.model.Ribot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RestApiTest {

    private static final String TAG = RestApiTest.class.getSimpleName();

    Service service;

    @Before
    public void setUp() throws Exception {
        service = RestApi.Service();
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }

    @Test
    public void restService() throws Exception {

        Observable<List<Ribot>> ribots = service.getRibots();

        TestSubscriber<List<Ribot>> testSubscriber = new TestSubscriber<>();
        ribots.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();

        List<List<Ribot>> profiles = testSubscriber.getOnNextEvents();

        List<Ribot> ribotList = profiles.get(0);
        assertEquals(11, ribotList.size());

        Ribot ribot = ribotList.get(0);
        Profile profile = ribot.getProfile();
        assertNotNull(profile);

        assertTrue(!profile.getName().getFirst().isEmpty());
        assertTrue(!profile.getAvatar().isEmpty());
        assertTrue(!profile.getBio().isEmpty());

    }

}
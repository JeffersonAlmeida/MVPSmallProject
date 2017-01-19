package com.mvpsmallproject.util;

import com.mvpsmallproject.model.Name;
import com.mvpsmallproject.model.Profile;
import com.mvpsmallproject.model.Ribot;
import com.mvpsmallproject.remote.Service;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MockService implements Service {

    @Override
    public Observable<List<Ribot>> getRibots() {
        List<Ribot> ribots = new ArrayList<>();
        Profile profile = new Profile("id", "jeff@domain.com.br", new Name("Jeff"));
        Ribot ribot = new Ribot(profile);
        ribots.add(ribot);
        return Observable.just(ribots);
    }
}

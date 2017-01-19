package com.mvpsmallproject.remote;

import com.mvpsmallproject.model.Ribot;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface Service {

    @GET("ribots")
    Observable<List<Ribot>> getRibots();

}

package com.mvpsmallproject.remote;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {

    private static final String BASE_URL = "https://api.ribot.io/";

    private static Service service;

    private static Service createService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(logging);
        OkHttpClient client = builder.build();

        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public static synchronized Service Service() {
        if ( service == null ) {
            service = createService();
        }
        return service;
    }
}

package com.mvpsmallproject.injection;

import com.mvpsmallproject.MVPApplication;
import com.mvpsmallproject.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ServiceModule.class )
public interface ServiceComponent {

    void inject(MainActivity mainActivity);
    void inject(MVPApplication mvpApplication);

}

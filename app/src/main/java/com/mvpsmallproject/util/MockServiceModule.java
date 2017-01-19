package com.mvpsmallproject.util;

import com.mvpsmallproject.injection.ServiceModule;
import com.mvpsmallproject.remote.Service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class MockServiceModule extends ServiceModule {

    @Provides
    Service service(){
        return new MockService();
    }
}

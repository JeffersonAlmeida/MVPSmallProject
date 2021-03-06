package com.mvpsmallproject;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.mvpsmallproject.injection.DaggerServiceComponent;
import com.mvpsmallproject.injection.ServiceComponent;
import com.mvpsmallproject.injection.ServiceModule;
import com.mvpsmallproject.util.ActivityEvent;
import com.mvpsmallproject.util.ActivityEventKind;

import java.util.concurrent.ArrayBlockingQueue;

import rx.Observable;
import rx.Subscriber;

public class MVPApplication extends Application {

    ServiceComponent serviceComponent;

    private static Observable<ActivityEvent> _activityEventStream;

    @Override
    public void onCreate() {
        super.onCreate();

        ActivityEventProducer activityEventProducer = new ActivityEventProducer();
        _activityEventStream = Observable.create(activityEventProducer);
        registerActivityLifecycleCallbacks(activityEventProducer);

        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule())
                .build();

        getServiceComponent().inject(this);

    }

    public ServiceComponent getServiceComponent(){
        return serviceComponent;
    }

    public void setServiceComponent(ServiceComponent service) {
        serviceComponent = service;
    }

    private static class ActivityEventProducer implements ActivityLifecycleCallbacks, Observable.OnSubscribe<ActivityEvent> {

        private ArrayBlockingQueue<ActivityEvent> activityEvents = new ArrayBlockingQueue<>(256, false);
        private boolean anyOneSubscribed;

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if(!anyOneSubscribed) {
                return;
            }
            ActivityEvent activityEvent = new ActivityEvent();
            activityEvent.setActivityClass(activity.getClass());
            activityEvent.setEventKind(ActivityEventKind.CREATED);
            activityEvents.add(activityEvent);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if(!anyOneSubscribed) {
                return;
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            if(!anyOneSubscribed) {
                return;
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            if(!anyOneSubscribed) {
                return;
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if(!anyOneSubscribed) {
                return;
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            if(!anyOneSubscribed) {
                return;
            }
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            if(!anyOneSubscribed) {
                return;
            }
            ActivityEvent activityEvent = new ActivityEvent();
            activityEvent.setActivityClass(activity.getClass());
            activityEvent.setEventKind(ActivityEventKind.DESTROYED);
            activityEvents.add(activityEvent);
        }

        @Override
        public void call(Subscriber<? super ActivityEvent> subscriber) {
            anyOneSubscribed = true;
            try {
                while(!subscriber.isUnsubscribed()) {
                    ActivityEvent activityEvent = activityEvents.take();
                    subscriber.onNext(activityEvent);
                }
            } catch(Exception e) {
                subscriber.onError(e);
            } finally {
                anyOneSubscribed = false;
                activityEvents.clear();
            }
        }
    }
}

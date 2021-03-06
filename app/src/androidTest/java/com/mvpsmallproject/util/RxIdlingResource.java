package com.mvpsmallproject.util;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Espresso Idling resource that handles waiting for RxJava Observables executions.
 * This class must be used with RxIdlingExecutionHook.
 * Before registering this idling resource you must:
 * 1. Create an instance of RxIdlingExecutionHook by passing an instance of this class.
 * 2. Register RxIdlingExecutionHook with the RxJavaPlugins using registerObservableExecutionHook()
 * 3. Register this idle resource with Espresso using Espresso.registerIdlingResources()
 */
public class RxIdlingResource implements IdlingResource {

    private final AtomicInteger mActiveSubscriptionsCount = new AtomicInteger(0);
    private ResourceCallback mResourceCallback;

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        return mActiveSubscriptionsCount.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }

    public void incrementActiveSubscriptionsCount() {
        int count = mActiveSubscriptionsCount.incrementAndGet();
    }

    public void decrementActiveSubscriptionsCount() {
        if (isIdleNow()) {
            mResourceCallback.onTransitionToIdle();
        }
    }

}

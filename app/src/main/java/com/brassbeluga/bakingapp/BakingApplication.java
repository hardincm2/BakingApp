package com.brassbeluga.bakingapp;

import android.app.Activity;
import android.app.Application;

import com.brassbeluga.bakingapp.component.DaggerBakingApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Custom {@link Application} for this Baking app. This enables custom Activity injection.
 */
public class BakingApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerBakingApplicationComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
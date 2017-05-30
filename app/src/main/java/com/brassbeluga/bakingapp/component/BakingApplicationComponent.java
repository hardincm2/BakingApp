package com.brassbeluga.bakingapp.component;

import com.brassbeluga.bakingapp.BakingApplication;
import com.brassbeluga.bakingapp.module.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        MainActivityModule.class
})
public interface BakingApplicationComponent {
    void inject(BakingApplication application);
}

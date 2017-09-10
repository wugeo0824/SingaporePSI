package com.xjcrepe.sgpsi.di;

import com.xjcrepe.sgpsi.map.MainActivity;
import com.xjcrepe.sgpsi.map.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();
}

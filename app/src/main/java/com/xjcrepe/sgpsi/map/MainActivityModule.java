package com.xjcrepe.sgpsi.map;

import com.xjcrepe.sgpsi.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by LiXijun on 2017/9/10.
 */

@Module
public abstract class MainActivityModule {

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);
}

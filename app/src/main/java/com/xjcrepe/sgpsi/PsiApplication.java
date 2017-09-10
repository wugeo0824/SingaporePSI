package com.xjcrepe.sgpsi;

import android.support.annotation.VisibleForTesting;

import com.xjcrepe.sgpsi.di.AppComponent;
import com.xjcrepe.sgpsi.di.DaggerAppComponent;
import com.xjcrepe.sgpsi.repo.PsiReadingsRepository;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by LiXijun on 2017/9/10.
 */

public class PsiApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}

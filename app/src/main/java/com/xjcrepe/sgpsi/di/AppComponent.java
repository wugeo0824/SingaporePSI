package com.xjcrepe.sgpsi.di;

import android.app.Application;

import com.xjcrepe.sgpsi.PsiApplication;
import com.xjcrepe.sgpsi.network.NetworkModule;
import com.xjcrepe.sgpsi.repo.PsiReadingsRepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        PsiReadingsRepositoryModule.class,
        NetworkModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(PsiApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
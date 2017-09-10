package com.xjcrepe.sgpsi.repo;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by LiXijun on 2017/9/10.
 */

@Module
public abstract class PsiReadingsRepositoryModule {

    @Binds
    @Singleton
    abstract PsiReadingsDataSource psiReadingsRepository(FakePsiReadingsRepository psiReadingsRepository);
}

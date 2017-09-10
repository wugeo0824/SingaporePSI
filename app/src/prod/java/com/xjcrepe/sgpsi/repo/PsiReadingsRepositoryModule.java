package com.xjcrepe.sgpsi.repo;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PsiReadingsRepositoryModule {

    @Binds
    @Singleton
    abstract PsiReadingsDataSource psiReadingsRepository(PsiReadingsRepository psiReadingsRepository);
}
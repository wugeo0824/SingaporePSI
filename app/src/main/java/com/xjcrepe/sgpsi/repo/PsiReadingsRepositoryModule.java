package com.xjcrepe.sgpsi.repo;

import com.xjcrepe.sgpsi.network.PsiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PsiReadingsRepositoryModule {

    @Provides
    @Singleton
    public PsiReadingsRepository psiReadingsRepository(PsiService psiService) {
        return new PsiReadingsRepository(psiService);
    }
}
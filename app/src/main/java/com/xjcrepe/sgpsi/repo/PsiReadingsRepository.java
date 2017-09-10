package com.xjcrepe.sgpsi.repo;

import android.support.annotation.Nullable;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.network.PsiService;
import com.xjcrepe.sgpsi.network.response.PsiReadingSetResponse;

import io.reactivex.Single;

/**
 * Created by LiXijun on 2017/9/10.
 */

public class PsiReadingsRepository implements PsiReadingsDataSource {

    @Nullable
    private PsiReadingSetResponse psiReadingSet;

    private final PsiService psiService;

    public PsiReadingsRepository(PsiService psiService) {
        this.psiService = psiService;
    }

    @Override
    public Single<PsiReadings> getPsiReadingsWithType(int readingType) {
        return null;
    }

    private PsiReadings findPsiReadingsFromSet(int readingType) {
        return null;
    }
}

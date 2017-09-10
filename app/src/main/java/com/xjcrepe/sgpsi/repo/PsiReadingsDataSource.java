package com.xjcrepe.sgpsi.repo;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.model.PsiReadingsType;

import io.reactivex.Single;

public interface PsiReadingsDataSource {
    Single<PsiReadings> getPsiReadingsWithType(@PsiReadingsType int readingType);
}

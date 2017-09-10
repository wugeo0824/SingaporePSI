package com.xjcrepe.sgpsi.repo;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.model.PsiReadingsType;

import io.reactivex.Single;

/**
 * Created by LiXijun on 2017/9/10.
 */

public interface PsiReadingsDataSource {

    Single<PsiReadings> getPsiReadingsWithType(@PsiReadingsType int readingType);
}

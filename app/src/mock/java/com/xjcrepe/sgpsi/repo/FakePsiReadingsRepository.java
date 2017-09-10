package com.xjcrepe.sgpsi.repo;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.model.PsiReadingsType;
import com.xjcrepe.sgpsi.network.response.PsiReadingSetResponse;
import com.xjcrepe.sgpsi.network.response.PsiResponse;
import com.xjcrepe.sgpsi.utils.CommonUtils;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;

import static com.xjcrepe.sgpsi.model.PsiReadingsType.CO8HourMax;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.COSubIndex;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.O38HourMax;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.O3SubIndex;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.PM10Daily;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.PM10SubIndex;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.PM25Daily;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.PM25SubIndex;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.PSIDaily;
import static com.xjcrepe.sgpsi.model.PsiReadingsType.SO2Daily;

/**
 * Created by LiXijun on 2017/9/10.
 */

public class FakePsiReadingsRepository implements PsiReadingsDataSource {

    private PsiReadingSetResponse psiReadingSet;

    @Inject
    public FakePsiReadingsRepository(Context context) {
        try {
            String resultString = CommonUtils.getStringFromFile(context, "psi_200.json");
            Gson gson = new Gson();
            PsiResponse psiResponse = gson.fromJson(resultString, PsiResponse.class);
            psiReadingSet = psiResponse.getItemsResponse().getReadingSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SingleSource<? extends PsiReadings> getPsiReadingsSingleSource(@PsiReadingsType int readingType, PsiReadingSetResponse psiReadingSet) {
        PsiReadings result = findPsiReadingsFromSet(readingType, psiReadingSet);
        if (result == null)
            return Single.error(new InvalidReadingTypeException());
        else
            return Single.just(result);
    }

    @Nullable
    private static PsiReadings findPsiReadingsFromSet(@PsiReadingsType int readingType, PsiReadingSetResponse psiReadingSet) {
        if (psiReadingSet == null) {
            return null;
        }

        switch (readingType) {
            case O3SubIndex:
                return psiReadingSet.getO3SubIndex();
            case PM10Daily:
                return psiReadingSet.getPm10Daily();
            case PM10SubIndex:
                return psiReadingSet.getPm10SubIndex();
            case COSubIndex:
                return psiReadingSet.getCoSubIndex();
            case PM25Daily:
                return psiReadingSet.getPm25Daily();
            case CO8HourMax:
                return psiReadingSet.getCoEightHourMax();
            case SO2Daily:
                return psiReadingSet.getSo2Daily();
            case PM25SubIndex:
                return psiReadingSet.getPm25SubIndex();
            case PSIDaily:
                return psiReadingSet.getPsiDaily();
            case O38HourMax:
                return psiReadingSet.getO3EightHourMax();
        }

        return null;
    }

    @Override
    public Single<PsiReadings> getPsiReadingsWithType(final int readingType) {
        return Single.defer(new Callable<SingleSource<? extends PsiReadings>>() {
            @Override
            public SingleSource<? extends PsiReadings> call() {
                return getPsiReadingsSingleSource(readingType, psiReadingSet);
            }
        });
    }
}

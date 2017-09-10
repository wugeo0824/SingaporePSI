package com.xjcrepe.sgpsi.repo;

import android.support.annotation.Nullable;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.model.PsiReadingsType;
import com.xjcrepe.sgpsi.network.PsiService;
import com.xjcrepe.sgpsi.network.response.PsiReadingSetResponse;
import com.xjcrepe.sgpsi.network.response.PsiResponse;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

public class PsiReadingsRepository {

    private final PsiService psiService;

    @Nullable
    private PsiReadingSetResponse psiReadingSet;

    public PsiReadingsRepository(PsiService psiService) {
        this.psiService = psiService;
    }

    public Single<PsiReadings> getPsiReadingsWithType(@PsiReadingsType final int readingType) {
        if (psiReadingSet == null) {
            return psiService.getPsi()
                    .subscribeOn(Schedulers.io())
                    .map(new Function<PsiResponse, PsiReadingSetResponse>() {
                        @Override
                        public PsiReadingSetResponse apply(PsiResponse psiResponse) throws Exception {
                            if (psiResponse.getItemsResponse() != null &&
                                    psiResponse.getItemsResponse().getReadingSet() != null) {
                                psiReadingSet = psiResponse.getItemsResponse().getReadingSet();
                            }
                            return psiReadingSet;
                        }
                    }).flatMap(new Function<PsiReadingSetResponse, SingleSource<? extends PsiReadings>>() {
                        @Override
                        public SingleSource<? extends PsiReadings> apply(PsiReadingSetResponse psiReadingSetResponse) {
                            return getPsiReadingsSingleSource(readingType, psiReadingSetResponse);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return Single.defer(new Callable<SingleSource<? extends PsiReadings>>() {
                @Override
                public SingleSource<? extends PsiReadings> call() {
                    return getPsiReadingsSingleSource(readingType, psiReadingSet);
                }
            });
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
}

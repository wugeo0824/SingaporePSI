package com.xjcrepe.sgpsi.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
 * Annotations for psi readings type
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        O3SubIndex,
        PM10Daily,
        PM10SubIndex,
        COSubIndex,
        PM25Daily,
        CO8HourMax,
        SO2Daily,
        PM25SubIndex,
        PSIDaily,
        O38HourMax
})
public @interface PsiReadingsType {
    int O3SubIndex = 0;
    int PM10Daily = 1;
    int PM10SubIndex = 2;
    int COSubIndex = 3;
    int PM25Daily = 4;
    int CO8HourMax = 5;
    int SO2Daily = 6;
    int PM25SubIndex = 7;
    int PSIDaily = 8;
    int O38HourMax = 9;
}
package com.xjcrepe.sgpsi.network.response;

import com.google.gson.annotations.SerializedName;
import com.xjcrepe.sgpsi.model.PsiReadings;

/**
 * Created by LiXijun on 2017/9/9.
 */

public class PsiReadingSetResponse {

    @SerializedName("o3_sub_index")
    private PsiReadings o3SubIndex;

    @SerializedName("pm10_twenty_four_hourly")
    private PsiReadings pm10Daily;

    @SerializedName("pm10_sub_index")
    private PsiReadings pm10SubIndex;

    @SerializedName("co_sub_index")
    private PsiReadings coSubIndex;

    @SerializedName("pm25_twenty_four_hourly")
    private PsiReadings pm25Daily;

    @SerializedName("co_eight_hour_max")
    private PsiReadings coEightHourMax;

    @SerializedName("so2_twenty_four_hourly")
    private PsiReadings so2Daily;

    @SerializedName("pm25_sub_index")
    private PsiReadings pm25SubIndex;

    @SerializedName("psi_twenty_four_hourly")
    private PsiReadings psiDaily;

    @SerializedName("o3_eight_hour_max")
    private PsiReadings o3EightHourMax;

    public PsiReadings getO3SubIndex() {
        return o3SubIndex;
    }

    public PsiReadings getPm10Daily() {
        return pm10Daily;
    }

    public PsiReadings getPm10SubIndex() {
        return pm10SubIndex;
    }

    public PsiReadings getCoSubIndex() {
        return coSubIndex;
    }

    public PsiReadings getPm25Daily() {
        return pm25Daily;
    }

    public PsiReadings getCoEightHourMax() {
        return coEightHourMax;
    }

    public PsiReadings getSo2Daily() {
        return so2Daily;
    }

    public PsiReadings getPm25SubIndex() {
        return pm25SubIndex;
    }

    public PsiReadings getPsiDaily() {
        return psiDaily;
    }

    public PsiReadings getO3EightHourMax() {
        return o3EightHourMax;
    }
}

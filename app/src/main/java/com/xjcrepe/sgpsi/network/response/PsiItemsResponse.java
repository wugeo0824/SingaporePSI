package com.xjcrepe.sgpsi.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LiXijun on 2017/9/9.
 */

public class PsiItemsResponse {

    private String timestamp;

    @SerializedName("readings")
    private PsiReadingSetResponse readingSet;

    public String getTimestamp() {
        return timestamp;
    }

    public PsiReadingSetResponse getReadingSet() {
        return readingSet;
    }
}

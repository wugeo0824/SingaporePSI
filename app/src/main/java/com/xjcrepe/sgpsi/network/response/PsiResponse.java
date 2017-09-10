package com.xjcrepe.sgpsi.network.response;

import com.google.gson.annotations.SerializedName;
import com.xjcrepe.sgpsi.model.ApiInfo;

/**
 * Created by LiXijun on 2017/9/9.
 */

public class PsiResponse {

    @SerializedName("items")
    private PsiItemsResponse itemsResponse;

    @SerializedName("api_info")
    private ApiInfo apiInfo;

    public PsiItemsResponse getItemsResponse() {
        return itemsResponse;
    }

    public ApiInfo getApiInfo() {
        return apiInfo;
    }
}

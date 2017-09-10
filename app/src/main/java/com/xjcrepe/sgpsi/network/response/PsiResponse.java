package com.xjcrepe.sgpsi.network.response;

import com.google.gson.annotations.SerializedName;
import com.xjcrepe.sgpsi.model.ApiInfo;

import java.util.List;

/**
 * Created by LiXijun on 2017/9/9.
 */

public class PsiResponse {

    @SerializedName("items")
    private List<PsiItemsResponse> itemsResponseList;

    @SerializedName("api_info")
    private ApiInfo apiInfo;

    public PsiItemsResponse getItemsResponse() {
        return itemsResponseList.get(0);
    }

    public ApiInfo getApiInfo() {
        return apiInfo;
    }
}

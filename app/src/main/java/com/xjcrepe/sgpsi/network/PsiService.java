package com.xjcrepe.sgpsi.network;

import com.xjcrepe.sgpsi.network.response.PsiResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by LiXijun on 2017/9/9.
 */

public interface PsiService {
    @GET("v1/environment/psi")
    Single<PsiResponse> getPsi();
}

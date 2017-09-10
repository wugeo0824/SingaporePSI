package com.xjcrepe.sgpsi.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.xjcrepe.sgpsi.R;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LiXijun on 2017/9/10.
 */

@Module
public class NetworkModule {

    private static final String PARAM_API_KEY = "api-key";
    private static final String PARAM_CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    @Provides
    @Singleton
    @Named("base_url")
    String provideBaseUrl(Context context) {
        return context.getString(R.string.base_url);
    }

    @Provides
    @Singleton
    @Named("api_key")
    String provideApiKey(Context context) {
        return context.getString(R.string.api_key);
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final @Named("api_key") String apiKey) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request original = chain.request();

                        // add the api key to each network call header
                        Request.Builder requestBuild = original.newBuilder()
                                .header(PARAM_CONTENT_TYPE, CONTENT_TYPE_JSON)
                                .addHeader(PARAM_API_KEY, apiKey);

                        return chain.proceed(requestBuild.build());
                    }
                }).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory,
                             RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                             OkHttpClient okHttpClient,
                             @Named("base_url") String baseUrl) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    PsiService providePsiService(Retrofit retrofit) {
        return retrofit.create(PsiService.class);
    }
}

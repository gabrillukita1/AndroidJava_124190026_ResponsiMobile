package com.example.covid.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConfig {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://covid19-public.digitalservice.id/api/v1/";

    private static OkHttpClient getOkHttpClientInstance() {
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClientInstance())
                    .build();
        }
        return retrofit;
    }

    public static CovidAPIService covidAPIService() {
        return getRetrofitInstance().create(CovidAPIService.class);
    }
}

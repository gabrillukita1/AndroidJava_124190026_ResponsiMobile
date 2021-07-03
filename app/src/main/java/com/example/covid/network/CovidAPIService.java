package com.example.covid.network;

import com.example.covid.network.response.ResponseDataHarian;
import com.example.covid.network.response_faskes.FaskesResponse;
import com.example.covid.network.response_kumulatif.KumulatifResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidAPIService {
    @GET("rekapitulasi_v2/jabar/harian")
    Call<ResponseDataHarian> getDataHarian(@Query("level") String query);

    @GET("rekapitulasi_v2/jabar/kumulatif")
    Call<KumulatifResponse> getDataKumulatif(@Query("level") String query);

    @GET("sebaran_v2/jabar/faskes")
    Call<FaskesResponse> getDataFaskes(@Query("tipe") String query);

}

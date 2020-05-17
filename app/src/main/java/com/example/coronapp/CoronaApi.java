package com.example.coronapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaApi {

    @GET("/summary")
    Call<RestCoronaResponse> getCoronaResponse();
}

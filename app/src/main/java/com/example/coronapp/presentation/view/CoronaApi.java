package com.example.coronapp;

import com.example.coronapp.model.RestCoronaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaApi {

    @GET("/summary")
    Call<RestCoronaResponse> getCoronaResponse();
}

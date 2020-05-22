package com.example.coronapp.data;

import com.example.coronapp.presentation.model.RestCoronaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaApi {

    @GET("/summary")
    Call<RestCoronaResponse> getCoronaResponse();
}

package com.example.coronapp.presentation.controller;

import android.content.SharedPreferences;

import com.example.coronapp.Constants;
import com.example.coronapp.Singletons;
import com.example.coronapp.presentation.model.Corona;
import com.example.coronapp.presentation.model.RestCoronaResponse;
import com.example.coronapp.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;


    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;

    }

    public void onStart(){



        List<Corona> coronaList = getDataFromCache();

        if(coronaList != null){
            view.showList(coronaList);
        } else {
            makeApiCall();
        }

    }


    private void makeApiCall(){
        Call<RestCoronaResponse> call = Singletons.getCoronaApi().getCoronaResponse();

        call.enqueue(new Callback<RestCoronaResponse>() {
            @Override
            public void onResponse(Call<RestCoronaResponse> call, Response<RestCoronaResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Corona> coronaList = response.body().getCountries();
                    saveList(coronaList);
                    view.showList(coronaList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestCoronaResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Corona> coronaList) {
        String jsonString = gson.toJson(coronaList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_CORONA_LIST, jsonString)
                .apply();
    }

    private List<Corona> getDataFromCache() {
        String jsonCorona = sharedPreferences.getString(Constants.KEY_CORONA_LIST, null);

        if(jsonCorona == null){
            return null;
        } else {
            Type listType = new TypeToken<List<Corona>>(){}.getType();
            return gson.fromJson(jsonCorona, listType);
        }
    }

    public void onItemClick(Corona corona){
        view.navigateToDetails(corona);

    }
}

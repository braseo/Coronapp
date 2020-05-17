package com.example.coronapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coronapp.presentation.Constants;
import com.example.coronapp.data.CoronaApi;
import com.example.coronapp.R;
import com.example.coronapp.presentation.model.Corona;
import com.example.coronapp.presentation.model.RestCoronaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.covid19api.com/";

    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("Coronapp", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Corona> coronaList = getDataFromCache();

        if(coronaList != null){
            showList(coronaList);
        } else {
            makeApiCall();
        }
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

    private void showList(List<Corona> coronaList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(coronaList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CoronaApi coronaApi = retrofit.create(CoronaApi.class);

        Call<RestCoronaResponse> call = coronaApi.getCoronaResponse();

        call.enqueue(new Callback<RestCoronaResponse>() {
            @Override
            public void onResponse(Call<RestCoronaResponse> call, Response<RestCoronaResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Corona> coronaList = response.body().getCountries();
                    saveList(coronaList);
                    showList(coronaList);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestCoronaResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Corona> coronaList) {
        String jsonString = gson.toJson(coronaList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_CORONA_LIST, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}

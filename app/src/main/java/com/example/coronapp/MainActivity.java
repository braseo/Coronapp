package com.example.coronapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
        makeApiCall();
    }

    private void showList(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }

        mAdapter = new RecyclerViewAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CoronaApi coronaApi = retrofit.create(CoronaApi.class);

        Call<RestCoronaResponse> call = coronaApi.getCoronaResponse();
        call.enqueue(new Callback<RestCoronaResponse>() {
            @Override
            public void onResponse(Call<RestCoronaResponse> call, Response<RestCoronaResponse> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    List<Corona> coronaList = response.body().getCountries();
                    Toast.makeText(getApplicationContext(), "API Succes", Toast.LENGTH_SHORT).show();
                }else{
                    showError();
                }

            }

            @Override
            public void onFailure(Call<RestCoronaResponse> call, Throwable t) {

            }
        });
    }
    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }


}

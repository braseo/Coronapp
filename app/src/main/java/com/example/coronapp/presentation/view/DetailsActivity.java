package com.example.coronapp.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronapp.R;
import com.example.coronapp.Singletons;
import com.example.coronapp.presentation.controller.MainController;
import com.example.coronapp.presentation.model.Corona;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private TextView txtDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txtDetails = findViewById(R.id.details_txt);
        Intent intent = getIntent();
        String coronaJson = intent.getStringExtra("coronakey");
        Corona corona = Singletons.getGson().fromJson(coronaJson, Corona.class);

        showDetails(corona);

    }

    private void showDetails(Corona corona) {
        txtDetails.setText(corona.getCountry());
    }
}

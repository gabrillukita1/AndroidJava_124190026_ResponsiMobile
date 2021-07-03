package com.example.covid.rumah_sakit_rujukan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.covid.network.APIConfig;
import com.example.covid.kasus_harian.MainActivity;
import com.example.covid.R;
import com.example.covid.network.response_faskes.DataItem;
import com.example.covid.network.response_faskes.FaskesResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RumahSakitRujukanActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static BottomNavigationView navbar;
    private RecyclerView recyclerView;
    private RumahSakitRujukanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rumah_sakit_rujukan);

        navbar = findViewById(R.id.bottomNavigation);
        navbar.setOnNavigationItemSelectedListener(this);
        navbar.setSelectedItemId(R.id.ic_rsRujukan);

        recyclerView = findViewById(R.id.rv_rsRujukan);
        adapter = new RumahSakitRujukanAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Call<FaskesResponse> call = APIConfig.covidAPIService().getDataFaskes("");

        call.enqueue(new Callback<FaskesResponse>() {
            @Override
            public void onResponse(Call<FaskesResponse> call, Response<FaskesResponse> responseFaskes) {
                if(responseFaskes.isSuccessful()){

                    List<DataItem> faskes = responseFaskes.body().getData();
                    Log.d("data", responseFaskes.body().toString());
                    if (!faskes.isEmpty()){
                        adapter.setData(faskes);
                    }
                }
            }

            @Override
            public void onFailure(Call<FaskesResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_kasusCovid:
                Intent i = new Intent(RumahSakitRujukanActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                break;

            case R.id.ic_rsRujukan:
                break;

        }
        return true;
    }
}
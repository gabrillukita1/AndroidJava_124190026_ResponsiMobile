package com.example.covid.kasus_harian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.covid.network.APIConfig;
import com.example.covid.R;
import com.example.covid.network.response.ContentItem;
import com.example.covid.network.response.ResponseDataHarian;
import com.example.covid.rumah_sakit_rujukan.RumahSakitRujukanActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView navbar;
    private KasusHarianAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navbar = findViewById(R.id.bottomNavigation);
        navbar.setOnNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.rv_kasusCovid);
        adapter = new KasusHarianAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));
        recyclerView.setAdapter(adapter);
        getKasusHarian();

    }

    private void getKasusHarian() {

        Call<ResponseDataHarian> call = APIConfig.covidAPIService().getDataHarian("");

        call.enqueue(new Callback<ResponseDataHarian>() {
            @Override
            public void onResponse(Call<ResponseDataHarian> call, Response<ResponseDataHarian> responseDataHarian) {
                if(responseDataHarian.isSuccessful()){
                    Toast.makeText(MainActivity.this, "get data sukses", Toast.LENGTH_SHORT).show();

                    List<ContentItem> kasusHarian = responseDataHarian.body().getData().getContent();
                    Log.d("data", responseDataHarian.body().toString());
                    if (!kasusHarian.isEmpty()){
                        adapter.setData(kasusHarian);
                        recyclerView.scrollToPosition(adapter.getItemCount()-1);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDataHarian> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ic_kasusCovid:
                break;

            case R.id.ic_rsRujukan:
                Intent i = new Intent(MainActivity.this, RumahSakitRujukanActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                break;
        }
        return true;
    }

}
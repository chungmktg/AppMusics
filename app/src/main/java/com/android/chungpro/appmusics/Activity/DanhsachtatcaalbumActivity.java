package com.android.chungpro.appmusics.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.chungpro.appmusics.Adapter.AllAlbumAdapter;
import com.android.chungpro.appmusics.Model.Albumhot;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaalbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewTatcaAlbum;
    Toolbar toolbar;

    AllAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcaalbum);
        init();
        Getdata();

    }

    private void Getdata() {
        DataService dataService = APIService.getService1();
        Call<List<Albumhot>> callback = dataService.GetAllAlbum();
        callback.enqueue(new Callback<List<Albumhot>>() {
            @Override
            public void onResponse(Call<List<Albumhot>> call, Response<List<Albumhot>> response) {
                ArrayList<Albumhot> mangAlbum = (ArrayList<Albumhot>) response.body();

                allAlbumAdapter = new AllAlbumAdapter(DanhsachtatcaalbumActivity.this,mangAlbum);
                recyclerViewTatcaAlbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this,2));
                recyclerViewTatcaAlbum.setAdapter(allAlbumAdapter);

            }

            @Override
            public void onFailure(Call<List<Albumhot>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTatcaAlbum = findViewById(R.id.recyclerTatcaAlbum);
        toolbar = findViewById(R.id.toolbarAlbum);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tất cả Album");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

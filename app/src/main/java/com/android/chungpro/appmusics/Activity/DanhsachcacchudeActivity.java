package com.android.chungpro.appmusics.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.chungpro.appmusics.Adapter.DanhsachbaihatAdapter;
import com.android.chungpro.appmusics.Adapter.DanhsachcacchudeAdapter;
import com.android.chungpro.appmusics.Model.ChuDe;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacchudeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    DanhsachcacchudeAdapter danhsachcacchudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacchude);
        anhxa();
        init();
        Getdata();
    }

    private void Getdata() {
        DataService dataService = APIService.getService1();
        Call<List<ChuDe>> callback = dataService.GetDanhSachCacChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhsachcacchudeAdapter = new DanhsachcacchudeAdapter(DanhsachcacchudeActivity.this,mangchude);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachcacchudeActivity.this,1));
                recyclerView.setAdapter(danhsachcacchudeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar=findViewById(R.id.toolbarChude);
        recyclerView=findViewById(R.id.recyclerDanhsachchude);
    }
}

package com.android.chungpro.appmusics.Activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.chungpro.appmusics.Adapter.DanhsachtheloaitheodhudeAdapter;
import com.android.chungpro.appmusics.Model.ChuDe;
import com.android.chungpro.appmusics.Model.TheLoai;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {
    ChuDe chuDe;
    RecyclerView recyclerViewTheloaitheochude;
    Toolbar toolbarTheloaitheochude;
    DanhsachtheloaitheodhudeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        Getintent();
        init();
        Getdata();

    }

    private void Getdata() {
        DataService dataService = APIService.getService1();
        Call<List<TheLoai>> callback = dataService.Gettheloaitheochude("1");
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                Log.d("xxxx",mangtheloai.size()+"");
                adapter = new DanhsachtheloaitheodhudeAdapter(DanhsachtheloaitheochudeActivity.this,mangtheloai);
                recyclerViewTheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));
                recyclerViewTheloaitheochude.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTheloaitheochude = findViewById(R.id.recyclertheloaitheochude);
        toolbarTheloaitheochude = findViewById(R.id.toolbartheloaitheochude);

        setSupportActionBar(toolbarTheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarTheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });
    }

    private void Getintent() {
        Intent intent = getIntent();
        if (intent.hasExtra("theloaitheochude")){
            chuDe= (ChuDe) intent.getSerializableExtra("theloaitheochude");


        }

    }
}

package com.android.chungpro.appmusics.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.chungpro.appmusics.Adapter.DanhsachcacplaylistAdapter;
import com.android.chungpro.appmusics.Model.PlayList;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhsachcacplaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    DanhsachcacplaylistAdapter danhsachcacplaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        anhxa();
        init();
        getdata();
    }

    private void getdata() {
        DataService dataService = APIService.getService1();
        Call<List<PlayList>> callback = dataService.Getdanhxemthemplaylist();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> mangplaylist = (ArrayList<PlayList>) response.body();

                danhsachcacplaylistAdapter = new DanhsachcacplaylistAdapter(DanhsachcacplaylistActivity.this,mangplaylist);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachcacplaylistActivity.this,2));
                recyclerView.setAdapter(danhsachcacplaylistAdapter);

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void init() {
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setTitle("Play List");
       toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }

    private void anhxa() {
        toolbar=findViewById(R.id.toolbarplaylist);
        recyclerView=findViewById(R.id.recyclerDanhsachplaylist);
    }
}

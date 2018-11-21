package com.android.chungpro.appmusics.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.chungpro.appmusics.Adapter.DanhsachbaihatAdapter;
import com.android.chungpro.appmusics.Model.Albumhot;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.Model.ChuDe;
import com.android.chungpro.appmusics.Model.PlayList;
import com.android.chungpro.appmusics.Model.QuangCao;
import com.android.chungpro.appmusics.Model.TheLoai;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    QuangCao quangCao;
    PlayList playList;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imgdanhsachcakhuc;
    TheLoai theLoai;
    ChuDe chuDe;
    Albumhot album;

    DanhsachbaihatAdapter danhsachbaihatAdapter;
    ArrayList<Baihat> mangbaihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        setContentView(R.layout.activity_danhsachbaihat);
        DataIntent();
        Anhxa();
        init();
        if(quangCao !=null && !quangCao.getTenBaiHat().equals("")){
            setValueInView(quangCao.getTenBaiHat(),quangCao.getHinhBaiHat());// set du lieu len danh sach bai hat
            GetDataQuangCao(quangCao.getIdQuangCao());
        }
        if (playList != null && !playList.getTenPlaylist().equals("")){
            setValueInView(playList.getTenPlaylist(),playList.getHinhPlaylist());
            GetdataPlaylist(playList.getIDPlaylist());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            Getdatatheotheloai(theLoai.getIdTheLoai());
        }
        if (chuDe != null && !chuDe.getTenChuDe().equals("")){
            //setValueInView(chuDe.getTenChuDe(),chuDe.getHinhChuDe());
            //Getdatatheochude(chuDe.getIdChuDe());

        }
        if (album !=null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            GetdatatheoAlbum(album.getIdAlbum());
        }
    }

    private void GetdatatheoAlbum(String idalbum) {
        DataService dataService = APIService.getService1();
        Call<List<Baihat>> callback = dataService.GetbaihattheoAlbum(idalbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this);
                DanhsachbaihatAdapter.baihatArrayList=mangbaihat;
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenclicl();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void Getdatatheochude(String idchude){
        DataService dataService = APIService.getService1();
        Call<List<Baihat>> callback = dataService.Getdanhsachbaihattheochude(idchude);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this);
                DanhsachbaihatAdapter.baihatArrayList=mangbaihat;
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenclicl();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });



    }

    private void Getdatatheotheloai(String idtheloai){
        DataService dataService = APIService.getService1();
        Call<List<Baihat>> callback = dataService.Getdanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this);
                DanhsachbaihatAdapter.baihatArrayList=mangbaihat;
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenclicl();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetdataPlaylist(String idplaylist) {
        DataService dataService = APIService.getService1();
        Call<List<Baihat>> callback = dataService.Getdanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this);
                DanhsachbaihatAdapter.baihatArrayList=mangbaihat;
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenclicl();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(final String idquangcao) {
        DataService dataService = APIService.getService1();
        Call<List<Baihat>> callback = dataService.Getdanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
               mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this);
                DanhsachbaihatAdapter.baihatArrayList=mangbaihat;
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenclicl();

            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });

    }

    private void setValueInView(String ten, String hinhanh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinhanh);
            Bitmap  bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());// chuyen url sang bitmap thi moi co the gan len image view
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinhanh).into(imgdanhsachcakhuc);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

    }

    private void Anhxa() {
        coordinatorLayout=findViewById(R.id.coordinatordanhsachbaihat);
        collapsingToolbarLayout=findViewById(R.id.collapsingtoolbar);
        toolbar=findViewById(R.id.toolbarDanhsach);
        recyclerViewDanhsachbaihat=findViewById(R.id.recyclerDanhsachbaihat);
        floatingActionButton=findViewById(R.id.floatingactionbottom);
        imgdanhsachcakhuc=findViewById(R.id.imgdanhsachcakhuc);
        floatingActionButton.setEnabled(false);

    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent!=null){
            if (intent.hasExtra("Banner")){
                quangCao= (QuangCao) intent.getSerializableExtra("Banner");
               // Toast.makeText(this, ""+quangCao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(this,""+quangCao.getIdQuangCao(),Toast.LENGTH_SHORT).show();
            }
            if (intent.hasExtra("itemplaylist")){
                playList= (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("idchude")){
                chuDe = (ChuDe) intent.getSerializableExtra("idchude");
            }
            if (intent.hasExtra("album")){
                album = (Albumhot) intent.getSerializableExtra("album");
            }
        }
    }
    private void evenclicl(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachbaihatActivity.this,PlaynhacActivity.class);
                intent.putExtra("cacbaihat",mangbaihat);
                startActivity(intent);
            }
        });
    }
}

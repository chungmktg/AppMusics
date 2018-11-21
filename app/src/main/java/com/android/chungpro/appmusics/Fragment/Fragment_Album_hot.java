package com.android.chungpro.appmusics.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.chungpro.appmusics.Activity.DanhsachtatcaalbumActivity;
import com.android.chungpro.appmusics.Adapter.AlbumAdapter;
import com.android.chungpro.appmusics.Model.Albumhot;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album_hot extends Fragment {
    RecyclerView rcvAlbum;
    TextView txtXemthem;
    View view;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_album_hot,container,false);
        anhxa();
        txtXemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DanhsachtatcaalbumActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void anhxa() {
        rcvAlbum=view.findViewById(R.id.recyclerAlbum);
        txtXemthem=view.findViewById(R.id.txtxemthemAlbumhot);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Albumhot>> callback=dataService.GetAlbumhot();
        callback.enqueue(new Callback<List<Albumhot>>() {
            @Override
            public void onResponse(Call<List<Albumhot>> call, Response<List<Albumhot>> response) {
                ArrayList<Albumhot> albumhots= (ArrayList<Albumhot>) response.body();
                albumAdapter=new AlbumAdapter(getActivity(),albumhots);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rcvAlbum.setLayoutManager(linearLayoutManager);
                rcvAlbum.setAdapter(albumAdapter);

            }

            @Override
            public void onFailure(Call<List<Albumhot>> call, Throwable t) {

            }
        });
    }
}

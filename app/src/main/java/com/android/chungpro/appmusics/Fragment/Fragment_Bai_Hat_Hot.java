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

import com.android.chungpro.appmusics.Activity.PlaynhacActivity;
import com.android.chungpro.appmusics.Adapter.BaihathotAdapter;
import com.android.chungpro.appmusics.Model.Albumhot;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewBaihathot;
    BaihathotAdapter baihathotAdapter;
    TextView txtphattatca;
    ArrayList<Baihat> baihatArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_bai_hat_hot,container,false);
        recyclerViewBaihathot=view.findViewById(R.id.recyclerBaihathot);
        txtphattatca = view.findViewById(R.id.txt_baihatyeuthich_phattatca);
        GetData();

        txtphattatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PlaynhacActivity.class);
                intent.putExtra("cacbaihat",baihatArrayList);
                startActivity(intent);
            }
        });

        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback= dataService.GetBaihatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList= (ArrayList<Baihat>) response.body();
                baihathotAdapter=new BaihathotAdapter(getActivity(),baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaihathot.setLayoutManager(linearLayoutManager);
                recyclerViewBaihathot.setAdapter(baihathotAdapter);

            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });

    }
}

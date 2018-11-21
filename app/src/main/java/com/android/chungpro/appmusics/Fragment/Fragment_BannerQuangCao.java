package com.android.chungpro.appmusics.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.chungpro.appmusics.Adapter.BannerAdapter;
import com.android.chungpro.appmusics.Model.QuangCao;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BannerQuangCao extends Fragment {
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;

    Runnable runnable;
    Handler handler;
    int currentitem;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_bannerquangcao,container,false);
        getData();
        anhxa();
        return view;
    }

    private void anhxa() {
       viewPager=view.findViewById(R.id.viewpager_quangcao);
       circleIndicator=view.findViewById(R.id.indicator_quangcao);
    }

    public void getData() {
        DataService dataService = APIService.getService();
        Call<List<QuangCao>> callBack= dataService.GetDataBanner();
        callBack.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banners= (ArrayList<QuangCao>) response.body();

                bannerAdapter=new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler=new Handler();
                runnable=new Runnable() {
                    @Override
                    public void run() {
                        currentitem=viewPager.getCurrentItem();
                        currentitem++;
                        if (currentitem>=viewPager.getAdapter().getCount()){
                            currentitem=0;

                        }
                        viewPager.setCurrentItem(currentitem,true);
                        handler.postDelayed(runnable,5000);
                    }
                };
                handler.postDelayed((Runnable) runnable,5000);
            }


            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });
    }
}

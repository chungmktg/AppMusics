package com.android.chungpro.appmusics.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chungpro.appmusics.Activity.DanhsachbaihatActivity;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Model.QuangCao;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_banner,container,false);

        ImageView imgBackGroundBanner= view.findViewById(R.id.imgbackground_banner);
        ImageView imgHinhBaiHatBanner=view.findViewById(R.id.img_baihatbanner);;
        TextView txtTenBaiHat=view.findViewById(R.id.txt_title_bannerbaihat);


        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnh()).into(imgBackGroundBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imgHinhBaiHatBanner);
        txtTenBaiHat.setText(arrayListBanner.get(position).getTenBaiHat());
        container.addView(view);

        // bắt sự kiện khi click bào banner quảng cáo
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DanhsachbaihatActivity.class);
                intent.putExtra("Banner", (Serializable) arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

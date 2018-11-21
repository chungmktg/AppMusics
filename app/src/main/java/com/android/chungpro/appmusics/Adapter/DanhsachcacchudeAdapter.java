package com.android.chungpro.appmusics.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.chungpro.appmusics.Activity.DanhsachbaihatActivity;
import com.android.chungpro.appmusics.Activity.DanhsachtheloaitheochudeActivity;
import com.android.chungpro.appmusics.Model.ChuDe;
import com.android.chungpro.appmusics.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhsachcacchudeAdapter extends  RecyclerView.Adapter<DanhsachcacchudeAdapter.ViewHoler>{
    Context context;
    ArrayList<ChuDe> mangchude;

    public DanhsachcacchudeAdapter(Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_chu_de,viewGroup,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        ChuDe chuDe = mangchude.get(i);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(viewHoler.imgHinhchude);

    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView imgHinhchude;


        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imgHinhchude = itemView.findViewById(R.id.imgdanhsachcacchude);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("theloaitheochude", (Serializable) mangchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}

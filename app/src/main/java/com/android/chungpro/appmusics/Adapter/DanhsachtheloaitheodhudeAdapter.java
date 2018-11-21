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
import com.android.chungpro.appmusics.Model.TheLoai;
import com.android.chungpro.appmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtheloaitheodhudeAdapter extends RecyclerView.Adapter<DanhsachtheloaitheodhudeAdapter.ViewHolder>{
    Context context;
    ArrayList<TheLoai> mangTheloai;

    public DanhsachtheloaitheodhudeAdapter(Context context, ArrayList<TheLoai> mangTheloai) {
        this.context = context;
        this.mangTheloai = mangTheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_the_loai_theo_chu_de,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(mangTheloai.get(i).getHinhTheLoai()).into(viewHolder.imgHinhtheloaitheochude);
        viewHolder.txtTentheloaitheodhude.setText(mangTheloai.get(i).getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return mangTheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhtheloaitheochude;
        TextView txtTentheloaitheodhude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhtheloaitheochude = itemView.findViewById(R.id.img_danhsachtheloaitheochude);
            txtTentheloaitheodhude = itemView.findViewById(R.id.txt_tentheloaitheodhude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhsachbaihatActivity.class);
                    intent.putExtra("idtheloai",mangTheloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

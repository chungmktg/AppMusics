package com.android.chungpro.appmusics.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.chungpro.appmusics.Activity.PlaynhacActivity;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;

    public PlaynhacAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = mangbaihat.get(i);
        viewHolder.txtTenCaSi.setText(baihat.getCasi());
        viewHolder.txtTenBaiHat.setText(baihat.getTenbaihat());
        viewHolder.txtIndexBaihat.setText(i+1+"");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBaiHat, txtTenCaSi,txtIndexBaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.txt_tenbaihat_playnhac);
            txtTenCaSi = itemView.findViewById(R.id.txt_casi_playnhac);
            txtIndexBaihat = itemView.findViewById(R.id.txt_index_playnhac);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

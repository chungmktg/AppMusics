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

import com.android.chungpro.appmusics.Activity.PlaynhacActivity;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SeachBaiHatAdapter  extends RecyclerView.Adapter<SeachBaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;

    public SeachBaiHatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_seach_bai_hat,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = mangbaihat.get(i);
        Picasso.with(context).load(baihat.getHinhbaihat()).into(viewHolder.imghinhbaihat);
        viewHolder.txttenbaihat.setText(baihat.getTenbaihat());
        viewHolder.txttencasi.setText(baihat.getCasi());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttenbaihat,txttencasi;
        ImageView imghinhbaihat, imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.txt_seach_tenbaihat);
            txttencasi = itemView.findViewById(R.id.txt_seach_casibaihat);
            imghinhbaihat = itemView.findViewById(R.id.img_seach_hinhbaihat);
            imgluotthich = itemView.findViewById(R.id.img_seach_iconlove);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PlaynhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}

package com.android.chungpro.appmusics.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.chungpro.appmusics.Activity.DanhsachbaihatActivity;
import com.android.chungpro.appmusics.Model.Albumhot;
import com.android.chungpro.appmusics.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class AllAlbumAdapter extends  RecyclerView.Adapter<AllAlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Albumhot> mangAlbum;

    public AllAlbumAdapter(Context context, ArrayList<Albumhot> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(mangAlbum.get(i).getHinhAlbum()).into(viewHolder.imgHinhAllalbum);
        viewHolder.txtTenAllalbum.setText(mangAlbum.get(i).getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhAllalbum;
        TextView txtTenAllalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAllalbum = itemView.findViewById(R.id.img_All_album);
            txtTenAllalbum = itemView.findViewById(R.id.txt_title_All_album);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhsachbaihatActivity.class);
                    intent.putExtra("album", (Serializable) mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

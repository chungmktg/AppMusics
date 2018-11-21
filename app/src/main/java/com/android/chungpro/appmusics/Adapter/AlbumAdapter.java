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
import com.android.chungpro.appmusics.Model.Albumhot;
import com.android.chungpro.appmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Albumhot> albumhotsList;

    public AlbumAdapter(Context context, ArrayList<Albumhot> albumhots) {
        this.context = context;
        this.albumhotsList = albumhots;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Albumhot albumhot = albumhotsList.get(i);
        viewHolder.tenAlbum.setText(albumhot.getTenAlbum());
        viewHolder.tenCasi.setText(albumhot.getTencasiAlbum());
        Picasso.with(context).load(albumhot.getHinhAlbum()).into(viewHolder.hinhAlbum);

    }

    @Override
    public int getItemCount() {
        return albumhotsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinhAlbum;
        TextView tenAlbum, tenCasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhAlbum=itemView.findViewById(R.id.imgAlbum);
            tenAlbum=itemView.findViewById(R.id.txtTenAlbum);
            tenCasi=itemView.findViewById(R.id.txtTencasiAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhsachbaihatActivity.class);
                    intent.putExtra("album",albumhotsList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

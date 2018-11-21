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
import com.android.chungpro.appmusics.Activity.DanhsachcacplaylistActivity;
import com.android.chungpro.appmusics.Model.PlayList;
import com.android.chungpro.appmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachcacplaylistAdapter extends  RecyclerView.Adapter<DanhsachcacplaylistAdapter.ViewHolder> {
    Context context;
    ArrayList<PlayList> mangPlaylist;
    PlayList playList;

    public DanhsachcacplaylistAdapter(Context context, ArrayList<PlayList> mangPlaylist) {
        this.context = context;
        this.mangPlaylist = mangPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        playList=mangPlaylist.get(i);
        Picasso.with(context).load(playList.getICon()).into(viewHolder.imgHinhnen);
        viewHolder.txtTenplaylist.setText(playList.getTenPlaylist());


    }

    @Override
    public int getItemCount() {
        return mangPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhnen;
        TextView txtTenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhnen = itemView.findViewById(R.id.imgdanhsachcacplaylist);
            txtTenplaylist = itemView.findViewById(R.id.txtdanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",mangPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

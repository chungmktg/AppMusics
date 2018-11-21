package com.android.chungpro.appmusics.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.chungpro.appmusics.Model.PlayList;
import com.android.chungpro.appmusics.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground,imgplaylist;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist,null);
            viewHolder=new ViewHolder();
            viewHolder.txttenplaylist = convertView.findViewById(R.id.txttenplaylist);
            viewHolder.imgplaylist= convertView.findViewById(R.id.imageviewPlaylist);
            viewHolder.imgbackground=convertView.findViewById(R.id.imageview_background_playlit);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhPlaylist()).into(viewHolder.imgbackground);
        Picasso.with(getContext()).load(playList.getICon()).into(viewHolder.imgplaylist);
        viewHolder.txttenplaylist.setText(playList.getTenPlaylist());


        return convertView;
    }
}

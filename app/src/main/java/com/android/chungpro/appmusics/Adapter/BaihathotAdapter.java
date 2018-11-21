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
import android.widget.Toast;

import com.android.chungpro.appmusics.Activity.PlaynhacActivity;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> arrayListBaihat;

    public BaihathotAdapter(Context context, ArrayList<Baihat> arrayListBaihat) {
        this.context = context;
        this.arrayListBaihat = arrayListBaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.dong_bai_hat_hot,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat=arrayListBaihat.get(i);
        viewHolder.tenBaihat.setText(baihat.getTenbaihat());
        viewHolder.tenCasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(viewHolder.hinhBaiHat);



    }

    @Override
    public int getItemCount() {
        return arrayListBaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tenBaihat, tenCasi;
        ImageView hinhBaiHat, hinhLuotThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenBaihat=itemView.findViewById(R.id.txt_tenbaihathot);
            tenCasi=itemView.findViewById(R.id.tencasiBaihathot);
            hinhBaiHat=itemView.findViewById(R.id.imghinhbaihathot);
            hinhLuotThich=itemView.findViewById(R.id.iconlove);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PlaynhacActivity.class);
                    intent.putExtra("cakhuc",arrayListBaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            hinhLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hinhLuotThich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService1();
                    Call<String> callback = dataService.UpdateLuotthich("1",arrayListBaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Lỗi!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    hinhLuotThich.setEnabled(false);
                }
            });
        }
    }

}

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
import android.widget.Toast;

import com.android.chungpro.appmusics.Activity.PlaynhacActivity;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.Model.PlayList;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{
    Context context;
    public static ArrayList<Baihat> baihatArrayList;


    public DanhsachbaihatAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       LayoutInflater layoutInflater = LayoutInflater.from(context);
       View view =layoutInflater.inflate(R.layout.dong_danh_sach_bai_hat,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder Holder, int i) {
        Baihat baihat = baihatArrayList.get(i);
        Holder.txtindexbaihat.setText(i+ 1 + "");
        Holder.txttenbaihat.setText(baihat.getTenbaihat());
        Holder.txttencasi.setText(baihat.getCasi());
    }

    @Override
    public int getItemCount() {

        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenbaihat,txttencasi,txtindexbaihat;
        ImageView imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenbaihat=itemView.findViewById(R.id.txtdanhsachTenbaihat);
            txttencasi=itemView.findViewById(R.id.txtdanhsachTencasi);
            txtindexbaihat=itemView.findViewById(R.id.txtdanhsachIndex);
            imgluotthich=itemView.findViewById(R.id.imgdanhsachLove);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PlaynhacActivity.class);
                    intent.putExtra("cakhuc",baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService1();
                    Call<String> callback = dataService.UpdateLuotthich("1",baihatArrayList.get(getPosition()).getIdbaihat());
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
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}

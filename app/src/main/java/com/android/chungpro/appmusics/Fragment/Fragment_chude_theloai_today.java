package com.android.chungpro.appmusics.Fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.chungpro.appmusics.Activity.DanhsachbaihatActivity;
import com.android.chungpro.appmusics.Activity.DanhsachcacchudeActivity;
import com.android.chungpro.appmusics.Activity.DanhsachtheloaitheochudeActivity;
import com.android.chungpro.appmusics.Model.ChuDe;
import com.android.chungpro.appmusics.Model.Chudetheloaitrongngay;
import com.android.chungpro.appmusics.Model.TheLoai;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_chude_theloai_today extends Fragment {
    TextView txtXemThem;
    HorizontalScrollView horizontalScrollView;
    View view;

    public Fragment_chude_theloai_today() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_chude_theloai_today,container,false);
        anhxa();
        getData();
        txtXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachcacchudeActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    private void getData() {
        DataService dataService = APIService.getService();
       Call<Chudetheloaitrongngay> callback=dataService.GetChudeTheloaitrongngay();
       callback.enqueue(new Callback<Chudetheloaitrongngay>() {
           @Override
           public void onResponse(Call<Chudetheloaitrongngay> call, Response<Chudetheloaitrongngay> response) {
               Chudetheloaitrongngay chudetheloaitrongngay = response.body();
               final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
               chuDeArrayList.addAll(chudetheloaitrongngay.getChuDe());

               final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
               theLoaiArrayList.addAll(chudetheloaitrongngay.getTheLoai());

               LinearLayout linearLayout = new LinearLayout(getActivity());
               linearLayout.setOrientation(linearLayout.HORIZONTAL);
               LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
               layout.setMargins(10,20,10,30);
               for (int i=0; i<(chuDeArrayList.size());i++){
                   CardView cardView = new CardView(getActivity());
                   cardView.setRadius(10);
                   ImageView imageView = new ImageView(getActivity());
                   imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                   if (chuDeArrayList.get(i).getHinhChuDe()!=null){
                       Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);

                   }
                   cardView.setLayoutParams(layout);
                   cardView.addView(imageView);
                   linearLayout.addView(cardView);
                   final int finalI = i;
                   imageView.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(getActivity(),DanhsachtheloaitheochudeActivity.class);
                           intent.putExtra("theloaitheochude",chuDeArrayList.get(finalI));
                           startActivity(intent);
                       }
                   });
               }

               for (int j=0; j<(theLoaiArrayList.size());j++){
                   CardView cardView = new CardView(getActivity());
                   cardView.setRadius(10);
                   ImageView imageView = new ImageView(getActivity());
                   imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                   if (theLoaiArrayList.get(j).getHinhTheLoai()!=null){
                       Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);

                   }
                   cardView.setLayoutParams(layout);
                   cardView.addView(imageView);
                   linearLayout.addView(cardView);

                   final int finalJ = j;
                   imageView.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(getActivity(),DanhsachbaihatActivity.class);
                           intent.putExtra("idtheloai", (Serializable) theLoaiArrayList.get(finalJ));
                           startActivity(intent);
                       }
                   });
               }
                horizontalScrollView.addView(linearLayout);
           }

           @Override
           public void onFailure(Call<Chudetheloaitrongngay> call, Throwable t) {

           }
       });


    }

    private void anhxa() {
         txtXemThem=view.findViewById(R.id.txtXemthem);
         horizontalScrollView = view.findViewById(R.id.HorizontalScrollViewchudetheloai);

    }

}

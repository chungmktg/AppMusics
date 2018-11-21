package com.android.chungpro.appmusics.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chungpro.appmusics.Adapter.SeachBaiHatAdapter;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewSeachbaihat;
    TextView txtkhongcodulieu;
    SeachBaiHatAdapter seachBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar = view.findViewById(R.id.toolbarSeachbaihat);
        recyclerViewSeachbaihat = view.findViewById(R.id.recyclerSeachbaihat);
        txtkhongcodulieu = view.findViewById(R.id.txtkhongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.seachbaihat,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_seach);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
              SeachBaiHatByTuKhoa(s);
               return true;
           }

           @Override
           public boolean onQueryTextChange(String s) {
               return false;
           }
           });

    }
    private  void SeachBaiHatByTuKhoa(String s){
        DataService dataService = APIService.getService1();
        Call<List<Baihat>> callback = dataService.SeachBaiHattheotukhoa(s);

        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> mangbaihatseach = (ArrayList<Baihat>) response.body();
                if (mangbaihatseach.size()>0){
                    seachBaiHatAdapter = new SeachBaiHatAdapter(getActivity(),mangbaihatseach);
                    recyclerViewSeachbaihat.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerViewSeachbaihat.setAdapter(seachBaiHatAdapter);
                    txtkhongcodulieu.setVisibility(View.GONE);
                    recyclerViewSeachbaihat.setVisibility(View.VISIBLE);
                }else {
                    txtkhongcodulieu.setVisibility(View.VISIBLE);
                    recyclerViewSeachbaihat.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}

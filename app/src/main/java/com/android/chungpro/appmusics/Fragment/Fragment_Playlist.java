package com.android.chungpro.appmusics.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.chungpro.appmusics.Activity.DanhsachbaihatActivity;
import com.android.chungpro.appmusics.Activity.DanhsachcacplaylistActivity;
import com.android.chungpro.appmusics.Adapter.PlaylistAdapter;
import com.android.chungpro.appmusics.Model.PlayList;
import com.android.chungpro.appmusics.R;
import com.android.chungpro.appmusics.Service.APIService;
import com.android.chungpro.appmusics.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lvPlayList;
    TextView txtTitlePlaylist, txtXemThemPlaylist;

    ArrayList<PlayList> mangplaylist;
    PlaylistAdapter playlistAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fagment_playplist,container,false);
        lvPlayList=view.findViewById(R.id.lvDanhSachPlayList);
        txtTitlePlaylist=view.findViewById(R.id.txttitlePlayList);
        txtXemThemPlaylist=view.findViewById(R.id.txtMorePlaylist);

        Getdata();
        txtXemThemPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DanhsachcacplaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    // lấy dữ liệu từ api
    private void Getdata() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback=dataService.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangplaylist= (ArrayList<PlayList>) response.body();
                playlistAdapter= new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,mangplaylist);
                lvPlayList.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(lvPlayList);
                lvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(),DanhsachbaihatActivity.class);
                        intent.putExtra("itemplaylist",mangplaylist.get(position));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

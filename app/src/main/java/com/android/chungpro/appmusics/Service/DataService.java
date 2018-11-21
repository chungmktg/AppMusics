package com.android.chungpro.appmusics.Service;

import com.android.chungpro.appmusics.Model.Albumhot;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.Model.ChuDe;
import com.android.chungpro.appmusics.Model.Chudetheloaitrongngay;
import com.android.chungpro.appmusics.Model.PlayList;
import com.android.chungpro.appmusics.Model.QuangCao;
import com.android.chungpro.appmusics.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

   @GET("testSongBanner.php")
    Call<List<QuangCao>>  GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<Chudetheloaitrongngay> GetChudeTheloaitrongngay();

    @GET("albumhots.php")
    Call<List<Albumhot>> GetAlbumhot();

    @GET("baihathot.php")
    Call<List<Baihat>> GetBaihatHot();

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetDanhSachCacChuDe();

    @GET("tatcaalbum.php")
    Call<List<Albumhot>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<PlayList>> Getdanhxemthemplaylist();

    @GET("timkiembaihat.php")
    Call<List<Baihat>> GetSeachBaiHat();

    @FormUrlEncoded
    @POST("timbaitheotukhoa.php")
    Call<List<Baihat>> SeachBaiHattheotukhoa(@Field("tukhoa") String tukhoa);

   @FormUrlEncoded
   @POST("chiconnhungmuanho.php")
   Call<List<Baihat>> Getdanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

   @FormUrlEncoded
   @POST("takedanhsachchudetheoid.php")
   Call<List<Baihat>> Getdanhsachbaihattheochude(@Field("idchude") String idchude);

   @FormUrlEncoded
   @POST("theloaitheochude.php")
   Call<List<TheLoai>> Gettheloaitheochude(@Field("idchude") String idchude);

   @FormUrlEncoded
   @POST("getbaihatheoalbum.php")
   Call<List<Baihat>> GetbaihattheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotthich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);


}

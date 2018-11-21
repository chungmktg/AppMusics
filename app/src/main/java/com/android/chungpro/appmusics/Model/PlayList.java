package com.android.chungpro.appmusics.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable {

@SerializedName("IDPlaylist")
@Expose
private String iDPlaylist;
@SerializedName("TenPlaylist")
@Expose
private String tenPlaylist;
@SerializedName("HinhPlaylist")
@Expose
private String hinhPlaylist;
@SerializedName("ICon")
@Expose
private String iCon;

public String getIDPlaylist() {
return iDPlaylist;
}

public void setIDPlaylist(String iDPlaylist) {
this.iDPlaylist = iDPlaylist;
}

public String getTenPlaylist() {
return tenPlaylist;
}

public void setTenPlaylist(String tenPlaylist) {
this.tenPlaylist = tenPlaylist;
}

public String getHinhPlaylist() {
return hinhPlaylist;
}

public void setHinhPlaylist(String hinhPlaylist) {
this.hinhPlaylist = hinhPlaylist;
}

public String getICon() {
return iCon;
}

public void setICon(String iCon) {
this.iCon = iCon;
}

}

package com.android.chungpro.appmusics.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihat implements Parcelable {

@SerializedName("IdBaiHat")
@Expose
private String idbaihat;
@SerializedName("TenBaiHat")
@Expose
private String tenbaihat;
@SerializedName("HinhBaiHat")
@Expose
private String hinhbaihat;
@SerializedName("CaSi")
@Expose
private String casi;
@SerializedName("LinkBaiHat")
@Expose
private String linkbaihat;
@SerializedName("LuotThich")
@Expose
private String luotthich;

    protected Baihat(Parcel in) {
        idbaihat = in.readString();
        tenbaihat = in.readString();
        hinhbaihat = in.readString();
        casi = in.readString();
        linkbaihat = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<Baihat> CREATOR = new Creator<Baihat>() {
        @Override
        public Baihat createFromParcel(Parcel in) {
            return new Baihat(in);
        }

        @Override
        public Baihat[] newArray(int size) {
            return new Baihat[size];
        }
    };

    public String getIdbaihat() {
return idbaihat;
}

public void setIdbaihat(String idbaihat) {
this.idbaihat = idbaihat;
}

public String getTenbaihat() {
return tenbaihat;
}

public void setTenbaihat(String tenbaihat) {
this.tenbaihat = tenbaihat;
}

public String getHinhbaihat() {
return hinhbaihat;
}

public void setHinhbaihat(String hinhbaihat) {
this.hinhbaihat = hinhbaihat;
}

public String getCasi() {
return casi;
}

public void setCasi(String casi) {
this.casi = casi;
}

public String getLinkbaihat() {
return linkbaihat;
}

public void setLinkbaihat(String linkbaihat) {
this.linkbaihat = linkbaihat;
}

public String getLuotthich() {
return luotthich;
}

public void setLuotthich(String luotthich) {
this.luotthich = luotthich;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idbaihat);
        dest.writeString(tenbaihat);
        dest.writeString(hinhbaihat);
        dest.writeString(casi);
        dest.writeString(linkbaihat);
        dest.writeString(luotthich);
    }
}

package com.nextgeni.task.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class ImgName implements Parcelable {
    @SerializedName("fileID")
    @Expose
    private String fileID;
    @SerializedName("img_name")
    @Expose
    private String imgName;

    protected ImgName(Parcel in) {
        fileID = in.readString();
        imgName = in.readString();
    }

    public ImgName() {

    }

    public static final Creator<ImgName> CREATOR = new Creator<ImgName>() {
        @Override
        public ImgName createFromParcel(Parcel in) {
            return new ImgName(in);
        }

        @Override
        public ImgName[] newArray(int size) {
            return new ImgName[size];
        }
    };

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fileID);
        parcel.writeString(imgName);
    }
}

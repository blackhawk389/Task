package com.nextgeni.task.entities;

import android.arch.persistence.room.Entity;

import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "CategoryData")
public class Categories implements Parcelable{


    @SerializedName("categoryID")
    @Expose
    @NonNull
    @PrimaryKey
    private String categoryID;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("img_name")
    @Expose
    private String imgName;

    public Categories() {

    }

    protected Categories(Parcel in) {
        categoryID = in.readString();
        categoryName = in.readString();
        status = in.readString();
        imgName = in.readString();
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        parcel.writeString(categoryID);
        parcel.writeString(categoryName);
        parcel.writeString(status);
        parcel.writeString(imgName);
    }


}

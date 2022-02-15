package com.passcombine.accountbook.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountBookVo   implements Parcelable {


    @SerializedName("index")
    @Expose
    int index;

    @SerializedName("date")
    @Expose
    String date = "";

    @SerializedName("price")
    @Expose
    String price = "";

    @SerializedName("content")
    @Expose
    String content = "";

    @SerializedName("inOut")
    @Expose
    String inOut = "";


    protected AccountBookVo(Parcel in) {
        index = in.readInt();
        date = in.readString();
        price = in.readString();
        content = in.readString();
        inOut = in.readString();
    }

    public static final Creator<AccountBookVo> CREATOR = new Creator<AccountBookVo>() {
        @Override
        public AccountBookVo createFromParcel(Parcel in) {
            return new AccountBookVo(in);
        }

        @Override
        public AccountBookVo[] newArray(int size) {
            return new AccountBookVo[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public AccountBookVo(int index, String date, String price, String content, String inOut) {
        this.index = index;
        this.date = date;
        this.price = price;
        this.content = content;
        this.inOut = inOut;
    }

    public AccountBookVo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(index);
        parcel.writeString(date);
        parcel.writeString(price);
        parcel.writeString(content);
        parcel.writeString(inOut);
    }
}

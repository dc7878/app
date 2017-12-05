package com.cc.dc.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dc on 2017/11/21.
 */
public class Book implements Parcelable {

    private Long id;

    private String name;

    private String desc;

    private int price;

    public Book(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.desc = parcel.readString();
        this.price = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.desc);
        dest.writeInt(this.price);
    }

    public void readFromParcel(Parcel in){
        this.id = in.readLong();
        this.name = in.readString();
        this.desc = in.readString();
        this.price = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}

package com.example.ciber.and_foodproject;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Dish implements Parcelable {

    public String category;
    public String name;
    public String description;
    //public ImageView image;

    public Dish(String _category, String _name, String _description){
        this.category = _category;
        this.name = _name;
        this.description = _description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(name);
        dest.writeString(description);
    }

    private Dish(Parcel in){
        this.category = in.readString();
        this.name = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel source) {
            return new Dish(source);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };
}

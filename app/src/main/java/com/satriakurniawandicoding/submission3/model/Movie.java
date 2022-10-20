package com.satriakurniawandicoding.submission3.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String id;
    private String image;
    private String title;
    private String date;
    private String type;


    private String description;

    public Movie() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type= type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Movie(Parcel in){
        id = in.readString();
        image = in.readString();
        title = in.readString();
        date = in.readString();
        description = in.readString();
        type = in.readString();
    }


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(image);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(description);
        parcel.writeString(type);
    }
}

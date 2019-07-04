package com.ganargatul.submthree;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieTvItems implements Parcelable {
    String photo,title,overview;

    public MovieTvItems(String photo, String title, String overview) {
        this.photo = photo;
        this.title = title;
        this.overview = overview;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.title);
        dest.writeString(this.overview);
    }

    protected MovieTvItems(Parcel in) {
        this.photo = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<MovieTvItems> CREATOR = new Parcelable.Creator<MovieTvItems>() {
        @Override
        public MovieTvItems createFromParcel(Parcel source) {
            return new MovieTvItems(source);
        }

        @Override
        public MovieTvItems[] newArray(int size) {
            return new MovieTvItems[size];
        }
    };
}

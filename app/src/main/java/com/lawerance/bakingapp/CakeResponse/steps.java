package com.lawerance.bakingapp.CakeResponse;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class steps  implements Parcelable{


    @SerializedName("id")
    private long id;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("description")
    private String description;

    @SerializedName("videoURL")
    private String videoURL;

    @SerializedName("thumbnailURL")

    private String thumbnailURL;

    public steps() {
    }


    public steps(long id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    protected steps(Parcel in){
        id = in.readLong();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    public static final Creator<steps> CREATOR = new Creator<steps>() {
        @Override
        public steps createFromParcel(Parcel in) {
            return new steps(in);
        }

        @Override
        public steps[] newArray(int size) {
            return new steps[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(description);
        parcel.writeString(shortDescription);
        parcel.writeString(videoURL);
        parcel.writeString(thumbnailURL);
    }

}
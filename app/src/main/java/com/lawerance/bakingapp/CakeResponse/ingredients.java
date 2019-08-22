package com.lawerance.bakingapp.CakeResponse;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ingredients implements Parcelable  {
    public static final String LOG_TAG = ingredients.class.getSimpleName();

    private long id;

    @SerializedName("quantity")
    private double quantity;

    @SerializedName("measure")
    private String measure;

    @SerializedName("ingredient")
    private String ingredient;

    public ingredients() {
    }

    public ingredients(long id, double quantity, String measure, String ingredient) {
        this.id = id;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    protected ingredients(Parcel in){
        id = in.readLong();
        quantity = in.readDouble();
        measure = in.readString();
        ingredient=in.readString();

    }

    public static final Creator<ingredients> CREATOR = new Creator<ingredients>() {
        @Override
        public ingredients createFromParcel(Parcel in) {
            return new ingredients(in);
        }

        @Override
        public ingredients[] newArray(int size) {
            return new ingredients[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeDouble(quantity);
        parcel.writeString(ingredient);
        parcel.writeString(measure);
    }

}
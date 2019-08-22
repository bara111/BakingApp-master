package com.lawerance.bakingapp.CakeResponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class cake  {


    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getServings() {
        return servings;
    }

    public void setServings(long servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("ingredients")
    private ArrayList<ingredients> ingredients;

    public ArrayList<com.lawerance.bakingapp.CakeResponse.ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<com.lawerance.bakingapp.CakeResponse.ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<com.lawerance.bakingapp.CakeResponse.steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<com.lawerance.bakingapp.CakeResponse.steps> steps) {
        this.steps = steps;
    }

    @SerializedName("steps")
    private ArrayList<steps> steps;

    @SerializedName("servings")
    private long servings;

    @SerializedName("image")
    private String image;

    public cake() {
    }



}
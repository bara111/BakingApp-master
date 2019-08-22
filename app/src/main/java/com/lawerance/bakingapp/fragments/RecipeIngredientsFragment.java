package com.lawerance.bakingapp.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lawerance.bakingapp.CakeResponse.ingredients;
import com.lawerance.bakingapp.R;

import java.util.List;


public class RecipeIngredientsFragment extends Fragment {
    private List<ingredients> ingredients;

    public RecipeIngredientsFragment() {
    }


    public void setIngredients(List<ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View roorView = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);
        return roorView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


    }
}

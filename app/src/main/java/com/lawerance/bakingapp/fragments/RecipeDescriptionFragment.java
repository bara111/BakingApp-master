package com.lawerance.bakingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lawerance.bakingapp.CakeResponse.ingredients;
import com.lawerance.bakingapp.R;

import java.util.ArrayList;

public class RecipeDescriptionFragment extends Fragment {

    private ArrayList<ingredients> ingredients=new ArrayList<>();

    TextView textView;

    public void setDescription(ArrayList<ingredients> ingredients) {
        this.ingredients = ingredients;

    }
    public String getText(){
        return textView.getText().toString();
    }
    public void setText(String text){
         textView.setText(text);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_description, container, false);
        textView = rootView.findViewById(R.id.tv_recipe_description);
        for (int i = 0; i < ingredients.size(); i++)
            textView.append(" " + ingredients.get(i).getQuantity() + " " + ingredients.get(i).getMeasure() + " " + ingredients.get(i).getIngredient() + "\n");
        return rootView;
    }
}

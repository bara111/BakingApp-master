package com.lawerance.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lawerance.bakingapp.CakeResponse.ingredients;
import com.lawerance.bakingapp.CakeResponse.steps;
import com.lawerance.bakingapp.fragments.RecipeDescriptionFragment;
import com.lawerance.bakingapp.fragments.RecipeStepsFragment;

import java.util.ArrayList;

public class StepsIngredientsActivity extends AppCompatActivity {
    private TextView mIngredient;
    private ArrayList<ingredients> ingredients;
    private ArrayList<steps> steps;
    private String name;
    private RecipeDescriptionFragment RecipeDescriptionFragment;
    private TextView mFoodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_ingredients);
        mFoodName=findViewById(R.id.tv_food_name);

        Intent intent = getIntent();
        ingredients = intent.getParcelableArrayListExtra("ingredients");
        steps = intent.getParcelableArrayListExtra("steps");
        name = intent.getStringExtra("name");
        mFoodName.setText(name);
        RecipeDescriptionFragment = new RecipeDescriptionFragment();
        RecipeDescriptionFragment.setDescription(ingredients);
        FragmentManager descriptionFragmentManager = getSupportFragmentManager();

        descriptionFragmentManager.beginTransaction()
                .add(R.id.ingredients, RecipeDescriptionFragment)
                .commit();

        RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();
        recipeStepsFragment.setSteps(steps);

        FragmentManager stepsFragmentManager = getSupportFragmentManager();

        stepsFragmentManager.beginTransaction()
                .add(R.id.steps, recipeStepsFragment)
                .commit();


    }



}

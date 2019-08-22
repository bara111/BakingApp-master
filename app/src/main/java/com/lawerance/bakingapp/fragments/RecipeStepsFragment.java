package com.lawerance.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.lawerance.bakingapp.Adapters.RecycleViewStepsAdapter;
import com.lawerance.bakingapp.Adapters.RecyclerItemClickListener;
import com.lawerance.bakingapp.CakeResponse.steps;
import com.lawerance.bakingapp.ExoPlayer_Activity;
import com.lawerance.bakingapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeStepsFragment extends Fragment {
    private ArrayList<steps> steps;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private RecycleViewStepsAdapter recycleViewStepsAdapter;

    public void setSteps(ArrayList<steps> steps) {
        this.steps = new ArrayList<>(steps);
    }

    public RecipeStepsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View roorView = inflater.inflate(R.layout.fragement_recipe_steps, container, false);
        recyclerView = roorView.findViewById(R.id.rv_steps);
        recycleViewStepsAdapter = new RecycleViewStepsAdapter(getContext());
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recycleViewStepsAdapter);
        recycleViewStepsAdapter.addAll(steps);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ExoPlayer_Activity.class);
                        intent.putExtra("videoURL", recycleViewStepsAdapter.getItem(position).getVideoURL());
                        intent.putExtra("thumbnailURL", recycleViewStepsAdapter.getItem(position).getThumbnailURL());
                        intent.putExtra("shortDescription", recycleViewStepsAdapter.getItem(position).getShortDescription());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
        return roorView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

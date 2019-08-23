package com.lawerance.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lawerance.bakingapp.Adapters.RecycleViewStepsAdapter;
import com.lawerance.bakingapp.Adapters.RecyclerItemClickListener;
import com.lawerance.bakingapp.CakeResponse.steps;
import com.lawerance.bakingapp.ExoPlayer_Activity;
import com.lawerance.bakingapp.R;

import java.util.ArrayList;

public class tabletRecipeStepsFragment extends Fragment {
    private ArrayList<steps> steps=new ArrayList<>();
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private RecycleViewStepsAdapter recycleViewStepsAdapter;
    OnImageClickListener mCallback;
    private long distance;

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public interface OnImageClickListener {
        void onImageSelected(int position,Long point);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }
    public void setSteps(ArrayList<steps> steps) {
        this.steps = new ArrayList<>(steps);
    }

    public tabletRecipeStepsFragment() {

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
                        mCallback.onImageSelected(position,distance);


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

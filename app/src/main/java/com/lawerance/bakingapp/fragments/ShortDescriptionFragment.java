package com.lawerance.bakingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lawerance.bakingapp.R;


public class ShortDescriptionFragment extends Fragment {
    private String ShortDescription;

    private TextView textView;

    public void setDescription(String ShortDescription) {
        this.ShortDescription = ShortDescription;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_short_description, container, false);
        textView = rootView.findViewById(R.id.tv_short_description);
        textView.setText(ShortDescription);
        return rootView;
    }
}

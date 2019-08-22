package com.lawerance.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.lawerance.bakingapp.Adapters.RecycleViewAdapter;
import com.lawerance.bakingapp.Adapters.RecyclerItemClickListener;
import com.lawerance.bakingapp.CakeResponse.CakeService;
import com.lawerance.bakingapp.CakeResponse.cake;
import com.lawerance.bakingapp.CakeResponse.ingredients;
import com.lawerance.bakingapp.CakeResponse.steps;
import com.lawerance.bakingapp.Widget.UpdateUIWidgetService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private boolean mIsTablet;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<cake> list;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(this, UpdateUIWidgetService.class);


        mIsTablet = getResources().getBoolean(R.bool.isTablet);

        recyclerView = findViewById(R.id.rv_menu);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        getData();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        cake cake = recycleViewAdapter.getItem(position);
                        ArrayList<ingredients> ingredients = cake.getIngredients();
                        ArrayList<steps> steps = cake.getSteps();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("ingredients", ingredients);
                        bundle.putString("name", cake.getName());
                        i.putExtra("bundle", bundle);
                        i.setAction("android.appwidget.action.APPWIDGET_UPDATE");
                        Intent phoneIntent = new Intent(MainActivity.this, StepsIngredientsActivity.class);
                        phoneIntent.putExtra("name", cake.getName());

                        phoneIntent.putExtra("ingredients", ingredients);
                        phoneIntent.putExtra("steps", steps);


                        if (mIsTablet) {

                            startService(i);
                            Intent tabletIntent = new Intent(MainActivity.this, tablet_step_ingredient_exoplayer_Activity.class);
                            tabletIntent.putExtra("name", cake.getName());

                            tabletIntent.putExtra("ingredients", ingredients);
                            tabletIntent.putExtra("steps", steps);

                            startActivity(tabletIntent);

                        } else {

                            startService(i);
                            startActivity(phoneIntent);

                        }


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );


    }


    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CakeService service = retrofit.create(CakeService.class);
        service.getCakes().enqueue(new Callback<ArrayList<cake>>() {
            @Override
            public void onResponse(Call<ArrayList<cake>> call, Response<ArrayList<cake>> response) {
                list = response.body();
                recycleViewAdapter = new RecycleViewAdapter(list);

                recyclerView.setAdapter(recycleViewAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<cake>> call, Throwable t) {
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}


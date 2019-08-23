package com.lawerance.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.lawerance.bakingapp.CakeResponse.ingredients;
import com.lawerance.bakingapp.CakeResponse.steps;
import com.lawerance.bakingapp.Widget.IngredientsWidget;
import com.lawerance.bakingapp.Widget.UpdateUIWidgetService;
import com.lawerance.bakingapp.fragments.RecipeDescriptionFragment;
import com.lawerance.bakingapp.fragments.tabletRecipeStepsFragment;

import java.util.ArrayList;

public class tablet_step_ingredient_exoplayer_Activity extends AppCompatActivity implements tabletRecipeStepsFragment.OnImageClickListener {
    private final String INTENT = "intent";
    private final String POSITION = "position";
    private TextView mFoodName;
    private TextView shortDescription;
    private ArrayList<steps> steps;
    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private Intent intent;
    private ArrayList<ingredients> ingredients;
    private Gson gson;
    private String name;
    private boolean autoPlay = false;
    public static final String READY = "Ready";

    public static final String AUTOPLAY = "autoplay";
    public static final String CURRENT_WINDOW_INDEX = "current_window_index";
    public static final String PLAYBACK_POSITION = "playback_position";
    private int currentWindow;
    private long playbackPosition;
    private boolean playWhenReady;
    private String videoURL;
    private Long Postion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_steps_ingredients);
        mFoodName=findViewById(R.id.tv_food_name);
        gson=new Gson();

        if(savedInstanceState==null) {
            intent = getIntent();
            exoPlayerView = findViewById(R.id.exo_player);
            name=intent.getStringExtra("name");
            mFoodName.setText(name);
            shortDescription = findViewById(R.id.tv_short_description);
            ingredients = intent.getParcelableArrayListExtra("ingredients");
            steps = intent.getParcelableArrayListExtra("steps");
            RecipeDescriptionFragment RecipeDescriptionFragment = new RecipeDescriptionFragment();
            RecipeDescriptionFragment.setDescription(ingredients);
            FragmentManager descriptionFragmentManager = getSupportFragmentManager();

            descriptionFragmentManager.beginTransaction()
                    .add(R.id.ingredients, RecipeDescriptionFragment)
                    .commit();

            tabletRecipeStepsFragment recipeStepsFragment = new tabletRecipeStepsFragment();
            recipeStepsFragment.setSteps(steps);
            recipeStepsFragment.setDistance(0);

            FragmentManager stepsFragmentManager = getSupportFragmentManager();

            stepsFragmentManager.beginTransaction()
                    .add(R.id.steps, recipeStepsFragment)
                    .commit();


        }
        else {
            playbackPosition = savedInstanceState.getLong(PLAYBACK_POSITION, 0);
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW_INDEX, 0);
            autoPlay = savedInstanceState.getBoolean(AUTOPLAY, false);
            playWhenReady = savedInstanceState.getBoolean(READY);
            videoURL=savedInstanceState.getString("videoUrl");
            intent = getIntent();
            name=intent.getStringExtra("name");
            mFoodName.setText(name);

            exoPlayerView = findViewById(R.id.exo_player);

            shortDescription = findViewById(R.id.tv_short_description);
            steps = savedInstanceState.getParcelableArrayList("steps");
            ingredients = savedInstanceState.getParcelableArrayList("ingredients");

            RecipeDescriptionFragment RecipeDescriptionFragment = new RecipeDescriptionFragment();
            RecipeDescriptionFragment.setDescription(ingredients);
            FragmentManager descriptionFragmentManager = getSupportFragmentManager();

            descriptionFragmentManager.beginTransaction()
                    .add(R.id.ingredients, RecipeDescriptionFragment)
                    .commit();

            tabletRecipeStepsFragment recipeStepsFragment = new tabletRecipeStepsFragment();
            recipeStepsFragment.setSteps(steps);
            recipeStepsFragment.setDistance(playbackPosition);
            FragmentManager stepsFragmentManager = getSupportFragmentManager();

            stepsFragmentManager.beginTransaction()
                    .add(R.id.steps, recipeStepsFragment)
                    .commit();
            initializePlayer(videoURL, playbackPosition);



        }



    }
    @Override
    public void onImageSelected(int position,Long distance) {
        shortDescription.setText(steps.get(position).getShortDescription());
        releasePlayer();
        exoPlayerView.setVisibility(View.VISIBLE);
        findViewById(R.id.no_video_available).setVisibility(View.GONE);
        videoURL=steps.get(position).getVideoURL();
        initializePlayer(steps.get(position).getVideoURL(), 0);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("videoUrl",videoURL);
        outState.putParcelable(INTENT, intent);
        outState.putParcelableArrayList("steps",steps);
        outState.putParcelableArrayList("ingredients",ingredients);
        outState.putLong(PLAYBACK_POSITION, playbackPosition);
        outState.putInt(CURRENT_WINDOW_INDEX, currentWindow);
        outState.putBoolean(AUTOPLAY, autoPlay);
        outState.putBoolean(READY, playWhenReady);


    }

    private void initializePlayer(String video_url, long position) {


        if (mExoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();


            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

            mExoPlayer.seekTo(position);
            exoPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(this, "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(
                    Uri.parse(video_url),
                    new DefaultDataSourceFactory(this, userAgent),
                    new DefaultExtractorsFactory(), null, null);


            mExoPlayer.prepare(mediaSource);

            mExoPlayer.setPlayWhenReady(false);

        }
        else {
            exoPlayerView.setVisibility(View.GONE);
            findViewById(R.id.no_video_available).setVisibility(View.VISIBLE);
            return;
        }

    }


    private void releasePlayer() {

        if (mExoPlayer != null) {
            playbackPosition = mExoPlayer.getCurrentPosition();
            currentWindow = mExoPlayer.getCurrentWindowIndex();
            autoPlay = mExoPlayer.getPlayWhenReady();
            mExoPlayer.release();
            mExoPlayer = null;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(false);
            releasePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(playWhenReady);
            mExoPlayer.seekTo(playbackPosition);
        } else {
            initializePlayer(videoURL,playbackPosition);
            mExoPlayer.setPlayWhenReady(playWhenReady);
            mExoPlayer.seekTo(playbackPosition);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mExoPlayer != null)
            mExoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }




}

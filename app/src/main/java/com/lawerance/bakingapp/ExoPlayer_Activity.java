package com.lawerance.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
import com.lawerance.bakingapp.fragments.RecipeStepsFragment;
import com.lawerance.bakingapp.fragments.ShortDescriptionFragment;

public class ExoPlayer_Activity extends AppCompatActivity {
    private ImageView mNoVideoImageView;
    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private Intent intent;
    private boolean autoPlay = false;
    public static final String READY = "Ready";

    public static final String AUTOPLAY = "autoplay";
    public static final String CURRENT_WINDOW_INDEX = "current_window_index";
    public static final String PLAYBACK_POSITION = "playback_position";
    private int currentWindow;
    private long playbackPosition;
    private boolean playWhenReady;
    private String videoURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);
        mNoVideoImageView = findViewById(R.id.no_video_available);
        exoPlayerView = findViewById(R.id.exo_player);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_launcher_background);
        exoPlayerView.setDefaultArtwork(icon);

        intent = getIntent();
        String shortDescription = intent.getStringExtra("shortDescription");
        videoURL = intent.getStringExtra("videoURL");

        if (savedInstanceState != null) {
            playbackPosition = savedInstanceState.getLong(PLAYBACK_POSITION, 0);
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW_INDEX, 0);
            autoPlay = savedInstanceState.getBoolean(AUTOPLAY, false);
            playWhenReady = savedInstanceState.getBoolean(READY);

        } else {
            playbackPosition = 0;
            intent = getIntent();
        }
        ShortDescriptionFragment shortDescriptionFragment = new ShortDescriptionFragment();
        shortDescriptionFragment.setDescription(shortDescription);

        FragmentManager shortDescriptionFragmentManager = getSupportFragmentManager();

        shortDescriptionFragmentManager.beginTransaction()
                .add(R.id.fl_description, shortDescriptionFragment)
                .commit();
        initializePlayer(videoURL);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("intent", intent);
        outState.putLong(PLAYBACK_POSITION, playbackPosition);
        outState.putInt(CURRENT_WINDOW_INDEX, currentWindow);
        outState.putBoolean(AUTOPLAY, autoPlay);
        outState.putBoolean(READY, playWhenReady);


    }

    private void initializePlayer(String video_url) {
        if (video_url.equals("")) {
            exoPlayerView.setVisibility(View.GONE);
            findViewById(R.id.no_video_available).setVisibility(View.VISIBLE);
            return;
        }

        if (mExoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();


            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

            mExoPlayer.seekTo(currentWindow, playbackPosition);
            exoPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(this, "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(
                    Uri.parse(video_url),
                    new DefaultDataSourceFactory(this, userAgent),
                    new DefaultExtractorsFactory(), null, null);


            mExoPlayer.prepare(mediaSource);

            mExoPlayer.setPlayWhenReady(false);

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
    public void onResume() {
        super.onResume();
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(playWhenReady);
            mExoPlayer.seekTo(playbackPosition);
        } else {
            initializePlayer(videoURL);
            mExoPlayer.setPlayWhenReady(playWhenReady);
            mExoPlayer.seekTo(playbackPosition);
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

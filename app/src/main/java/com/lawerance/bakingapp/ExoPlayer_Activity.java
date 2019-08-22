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
        String thumbnailURL = intent.getStringExtra("thumbnailURL");
        String shortDescription = intent.getStringExtra("shortDescription");
        String videoURL = intent.getStringExtra("videoURL");
        long position;
        if (savedInstanceState != null) {
            position = savedInstanceState.getLong("video-position", 0);
            intent=savedInstanceState.getParcelable("intent");
        }
        else {
            position = 0;
            intent = getIntent();
        }
        ShortDescriptionFragment shortDescriptionFragment = new ShortDescriptionFragment();
        shortDescriptionFragment.setDescription(shortDescription);

        FragmentManager shortDescriptionFragmentManager = getSupportFragmentManager();

        shortDescriptionFragmentManager.beginTransaction()
                .add(R.id.fl_description, shortDescriptionFragment)
                .commit();
        initializePlayer(videoURL, position);


    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("intent",intent);
        if(mExoPlayer!=null)
            outState.putLong("video-position", mExoPlayer.getCurrentPosition());

    }
    private void initializePlayer(String video_url, long position) {
        if(video_url.equals("")){
            exoPlayerView.setVisibility(View.GONE);
            findViewById(R.id.no_video_available).setVisibility(View.VISIBLE);
            return;
        }

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

    }



    private void releasePlayer() {

        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mExoPlayer!=null){
            mExoPlayer.setPlayWhenReady(false);
            releasePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mExoPlayer!=null){
            releasePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mExoPlayer!=null){
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mExoPlayer!=null)
            mExoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}

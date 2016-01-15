package com.crust87.centercropvideoviewsample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.crust87.centercropvideoviewsample.widget.SquareCenterCropImageView;
import com.crust87.centercropvideoviewsample.widget.SquareCenterCropVideoView;

public class MainActivity extends AppCompatActivity {

    private SquareCenterCropVideoView mVideoView;
    private SquareCenterCropImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadGUI();
        init();
    }

    @Override
    public void onPause() {
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }

        super.onPause();
    }

    private void loadGUI() {
        setContentView(R.layout.activity_main);
        mVideoView = (SquareCenterCropVideoView) findViewById(R.id.videoClip);
        mImageView = (SquareCenterCropImageView) findViewById(R.id.imageClip);
    }

    private void init() {
        new LoadImageTask(mImageView, "http://1.255.56.21/media/clip/34/34_197_1bf25bc6fa99c1382d4ad0078d1e2a4b19937e38.jpg", this).execute();
        new LoadVideoTask(mVideoView, "http://1.255.56.21/media/clip/34/34_197_1bf25bc6fa99c1382d4ad0078d1e2a4b19937e38.mp4", this).execute();
        mVideoView.start();

        mImageView.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (mImageView != null && mVideoView != null) {
                    if (mVideoView.isPlaying() && mVideoView.getCurrentPosition() > 1) {
                        mImageView.setVisibility(View.GONE);
                    } else {
                        mImageView.postDelayed(this, 100);
                    }
                }
            }
        }, 100);
    }
}

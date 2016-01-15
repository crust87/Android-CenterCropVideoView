/*
 * Android-CenterCropVideoView
 * https://github.com/crust87/Android-FFmpegExecutor
 *
 * Mabi
 * crust87@gmail.com
 * last modify 2016-01-15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

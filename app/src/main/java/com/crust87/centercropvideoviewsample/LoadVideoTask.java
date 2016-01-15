package com.crust87.centercropvideoviewsample;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.AsyncTask;

import com.crust87.centercropvideoview.widget.CenterCropVideoView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoadVideoTask extends AsyncTask<String, Void, String> {
    private CenterCropVideoView mVideoView;
    private String mVideo;
    private Context mContext;

    public LoadVideoTask(CenterCropVideoView videoView, String video, Context context) {
		mVideoView = videoView;
		mVideo = video;
        mContext = context;
		mVideoView.setPlayable(false);
    }

	protected String doInBackground(String... urls) {
        String name = mVideo.substring(mVideo.lastIndexOf('/') + 1);
        
        if(mVideo == null || mVideo.equals("null") || mVideo.equals("")) {
        	return null;
        } 
        
        File lPictureFile = new File(mContext.getCacheDir() + "/" + name);
        
		if (!lPictureFile.exists()) {
			try {
				InputStream inputStream = new java.net.URL(mVideo).openStream();
				OutputStream outputStream = new FileOutputStream(lPictureFile);
				BufferedInputStream bin = new BufferedInputStream(inputStream);
				BufferedOutputStream bout = new BufferedOutputStream(outputStream);

				int bytesRead = 0;
				byte[] buffer = new byte[1024];

				while ((bytesRead = bin.read(buffer, 0, 1024)) != -1) {
					bout.write(buffer, 0, bytesRead);
				}

				bout.close();
				bin.close();
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
        
       return lPictureFile.getAbsolutePath().toString();
    }
    
    protected void onPostExecute(String pClip) {
		mVideoView.setVideoPath(pClip);

		mVideoView.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mVideoView.setPlayable(true);
			}
		});

		mVideoView.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return false;
			}
		});
    }

}

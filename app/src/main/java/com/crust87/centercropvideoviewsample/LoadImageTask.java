package com.crust87.centercropvideoviewsample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
	private static final LruCache<String, Bitmap> mMemoryCache = new LruCache<String, Bitmap>((int) ((Runtime.getRuntime().maxMemory() / 1024) / 8)) {
		@Override
		protected int sizeOf(String key, Bitmap bitmap) {
			return bitmap.getByteCount() / 1024;
		}
	};

    private ImageView mImageView;
    private String mPicture;
    private Context mContext;
    private boolean isFadeIn;

    public LoadImageTask(ImageView imageView, String picture, Context context) {
		mImageView = imageView;
    	mPicture = picture;
        mContext = context;
        isFadeIn = true;
    }

    @Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	protected Bitmap doInBackground(String... urls) {
        String name = mPicture.substring(mPicture.lastIndexOf('/') + 1);

		Bitmap lPicture = mMemoryCache.get(name);
            
        if(lPicture != null) {
			return lPicture;
        }

        File lPictureFile = new File(mContext.getCacheDir() + "/" + name);
        
        if(!lPictureFile.exists()) {
        	try {
				InputStream inputStream = new java.net.URL(mPicture).openStream();
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
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        try {
            InputStream in = new FileInputStream(lPictureFile);
            lPicture = BitmapFactory.decodeStream(in);
            
            if(mMemoryCache != null) {
            	mMemoryCache.put(name, lPicture);
            }
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        	return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return lPicture;
    }
    
    protected void onPostExecute(Bitmap pPicture) {
    	if(mImageView != null && pPicture != null) {
    		if(isFadeIn) {
    			setImageWithFadeIn(mImageView, pPicture);
    		} else {
				mImageView.setImageBitmap(pPicture);
    		}
    	}
    }

	private static void setImageWithFadeIn(ImageView imageView, Bitmap picture) {
		if(imageView != null && picture != null) {
			imageView.setImageBitmap(picture);
			fadeInView(imageView);
		}
	}

	private static void fadeInView(View view) {
		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1f);
		fadeIn.setDuration(500);
		AnimatorSet fadeSet = new AnimatorSet();
		fadeSet.play(fadeIn);
		fadeSet.start();
	}
    
}

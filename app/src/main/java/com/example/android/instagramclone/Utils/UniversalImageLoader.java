package com.example.android.instagramclone.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.android.instagramclone.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by shash on 9/7/2017.
 */

public class UniversalImageLoader {

    private static final String TAG = "UniversalImageLoader";
    private static final int DEFAULTIMAGE= R.drawable.ic_android;
    private Context mContext;

    public UniversalImageLoader(Context context){
        mContext= context;
    }

    public ImageLoaderConfiguration getConfiguration(){
        DisplayImageOptions defaultOptions= new DisplayImageOptions.Builder()
                                                .showImageForEmptyUri(DEFAULTIMAGE)
                                                .showImageOnLoading(DEFAULTIMAGE)
                                                .showImageOnFail(DEFAULTIMAGE)
                                                .cacheOnDisk(true).cacheInMemory(true)
                                                .cacheOnDisk(true).resetViewBeforeLoading(true)
                                                .imageScaleType(ImageScaleType.EXACTLY)
                                                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(mContext)
                                                            .defaultDisplayImageOptions(defaultOptions)
                                                            .memoryCache(new WeakMemoryCache())
                                                            .diskCacheSize(100*1024*1024).build();
        return imageLoaderConfiguration;
    }

    public static void setImage(String imgURL, ImageView image, final ProgressBar mProgressBar, String append){

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(append + imgURL, image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (mProgressBar!=null){
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (mProgressBar!=null){
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (mProgressBar!=null){
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (mProgressBar!=null){
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}

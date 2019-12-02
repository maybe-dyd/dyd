package com.example.jxxy.mobileshop_dyd.common;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.example.jxxy.mobileshop_dyd.R;
import com.example.jxxy.mobileshop_dyd.http.HttpMethods;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

//MobileShopApp用于存放一些全局使用到的方法和变量，所以在android:name属性上设为此类
public class MobileShopApp extends Application {
    public  static Handler handler=new Handler();
    private static Context sContext;
    public  void  onCreate(){
        super.onCreate();
        HttpMethods.getInstance();  //初始化执行网络请求
        sContext=getApplicationContext();
        initImageLoader();
    }
    private  void initImageLoader(){
        DisplayImageOptions defaule_options =new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image_loading)
                .showImageForEmptyUri(R.drawable.image_empty)
                .showImageOnFail(R.drawable.image_error)
                .resetViewBeforeLoading(false)
                .delayBeforeLoading(1000)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .displayer(new SimpleBitmapDisplayer())
                .handler(new Handler())
                .build();
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480,800)
                .memoryCacheSize(2*1024*1024)
                .memoryCacheSizePercentage(13)
                .diskCacheSize(50*1024*1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY-2)
                .denyCacheImageMultipleSizesInMemory()
                .imageDownloader(new BaseImageDownloader(getApplicationContext()))
                .defaultDisplayImageOptions(defaule_options)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }//加载图片的具体参数和存储方法等设置
    public  static Context getContext(){
        return sContext;
    }

}

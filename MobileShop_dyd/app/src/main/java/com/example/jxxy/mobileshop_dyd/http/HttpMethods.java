package com.example.jxxy.mobileshop_dyd.http;

import android.util.Log;

import com.example.jxxy.mobileshop_dyd.common.Constants;
import com.example.jxxy.mobileshop_dyd.http.entity.HttpResult;
import com.example.jxxy.mobileshop_dyd.http.service.CategoryService;
import com.example.jxxy.mobileshop_dyd.http.service.GoodsService;
import com.example.jxxy.mobileshop_dyd.http.service.MemberService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
//HttpMethods网络请求类
public class HttpMethods {
    private  static final  String TAG="HttpMethods";

    private static final String BASE_URL=Constants.BASE_URL;
    private  static final long DEFAULT_TIMEOUT=5;
    private static HttpMethods sInstance;
    protected static GoodsService goodsService;
    protected static MemberService memberService;
    private Retrofit retrofit;
    protected static CategoryService categoryService;

    public HttpMethods(){
        if(sInstance ==null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
//创建OkHttpClient对象
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)                                          //接口地址
                    .addConverterFactory(GsonConverterFactory.create())        //GSON数据解析支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //通过RxJavaCallAdapterFactory为Retrofit添加RxJava支持
                    .client(okHttpClient)
                    .build();


            goodsService = retrofit.create(GoodsService.class);
            memberService =retrofit.create(MemberService.class);
            categoryService = retrofit.create(CategoryService.class);


        }
    }

    public static HttpMethods getInstance(){
        if(sInstance==null){
            synchronized (HttpMethods.class){
                if(sInstance==null){
                    sInstance=new HttpMethods();
                }
            }
        }
        return sInstance;
    }//避免线程请求冲突，所以利用 synchronized进行加锁操作

    public static class HttpResultFunc<T> implements Func1<HttpResult<T>,T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            Log.i(TAG,"status:"+httpResult.getStatus());
            Log.i(TAG,"msg:"+httpResult.getMsg());
            Log.i(TAG,"data:"+httpResult.getData());
            return httpResult.getData();
        }
    }//对获取数据进行log打印

    protected static <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //绑定生命周期
                .subscribe(subscriber);
                //进行订阅
    }//RxJava 有四个基本概念：Observable (可观察者，即被观察者)、 Observer (观察者)、 subscribe (订阅)、事件。Observable 和 Observer 通过 subscribe() 方法实现订阅关系，从而 Observable 可以在需要的时候发出事件来通知 Observer。
}

package com.example.jxxy.mobileshop_dyd.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.jxxy.mobileshop_dyd.R;
import com.example.jxxy.mobileshop_dyd.common.BaseActivity;
//启动动画，因为是首先启动，所以这里除了添加activity到Androidmanifest中还要设置为main

public class SplashActivity extends BaseActivity {
 ImageView splash_loading_item;
 @Override
 public int getContentViewId(){
     return R.layout.activity_splash;
 }
    @Override
   protected void initView(){
     super.initView();
     splash_loading_item=(ImageView)findViewById(R.id.splash_loading_item);
        Animation translate=AnimationUtils.loadAnimation(this,R.anim.splash_loading); //使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件,这里的文件也是存放在anim文件中
        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,AdActivity.class));
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);//这个函数有两个参数，一个参数是第一个activity进入时的动画，另外一个参数则是第二个activity退出时的动画。
                // 这里需要特别说明的是，关于overridePendingTransition这个函数，有两点需要主意
                //1. 它必需紧挨着startActivity()或者finish()函数之后调用”
                //2. 它只在android2.0以及以上版本上适用

                finish();
            }//动画将近结束时执行跳转

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splash_loading_item.setAnimation(translate);    //将动画实例到布局中，实现视觉上的效果
    }
}

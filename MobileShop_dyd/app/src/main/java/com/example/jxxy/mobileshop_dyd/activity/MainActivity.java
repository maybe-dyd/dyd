package com.example.jxxy.mobileshop_dyd.activity;

import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.jxxy.mobileshop_dyd.R;

import com.example.jxxy.mobileshop_dyd.common.BaseActivity;
import com.example.jxxy.mobileshop_dyd.fragment.NavigationFragment;
//主活动
public class MainActivity extends BaseActivity {
   @Override
   public  @LayoutRes
   int getContentViewId(){
       return R.layout.activity_main;
   }

    @Override
    protected void initView() {
        super.initView();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.f1_main,new NavigationFragment());
        transaction.commit();
    }//利用碎片管理加载碎片，通过FragmentManager来取得FragmentTransaction的实例，然后执行事务的方法包括add(),remove(),replace()等，最后进行commit提交
}

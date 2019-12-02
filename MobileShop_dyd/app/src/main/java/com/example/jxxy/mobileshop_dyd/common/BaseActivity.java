package com.example.jxxy.mobileshop_dyd.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
//基本类
//建立baseActivity抽象类作为基类
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initData();
        initView();
    }

    // 在创建活动时会自动执行initView()和initData()方法，以及创建了抽象方法以设置ContentView
    protected void initData(){

    }
    protected void initView(){

    }
    public abstract  int getContentViewId();
    public void toastshort(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void toastlong(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }//这里有两个比较实用的方法toastshort和toastlong，用以快速的进行吐司编写输出。这样继承子类就不用完全写完吐司代码即可。
}

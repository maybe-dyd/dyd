package com.example.jxxy.mobileshop_dyd.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
//基本碎片布局，类似BaseActivity，这里注释下butterKnife使用方法。
//1.首先在项目中添加 ButterKnife 依赖
// 类似@BindView(R.id.button)
//    Button button;就可进行findviewbyid操作，其他还有资源绑定等简化用法等
//若在基类 BaseActivity 中做了初始化或绑定 ButterKnife，后面继承基类的 activity 中就不用再绑定了.

//除了这样还可以使用AS的插件Zelezny来自动为我们生成需要绑定的控件代码。
public abstract class BaseFragment  extends Fragment{

    private Unbinder mUnbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(getContentViewId(),container,false);
        mUnbinder = ButterKnife.bind(this,view);
        initView(view);
        initData();
        return view;
    }
    protected void initData(){

    }
    protected void initView(View view){

    }
    public abstract int getContentViewId();

    public void toastshort(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
    public void toastlong(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }//解绑和销毁操作
}

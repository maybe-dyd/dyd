package com.example.jxxy.mobileshop_dyd.activity;

import android.widget.TextView;

import com.example.jxxy.mobileshop_dyd.R;
import com.example.jxxy.mobileshop_dyd.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

//修改密码活动页操作
public class ChangePwdActivity extends BaseActivity {

    @BindView(R.id.tv_title)    //初始化跳过控件
    TextView tvTitle;

    @Override
    public int getContentViewId(){
        return R.layout.activity_change_pwd;
    }           //重写BaseActivity方法引入布局

    @Override
    protected void initView(){
        super.initView();
        tvTitle.setText("修改密码");
    }       //初始化视图，将标题设为修改密码

    @OnClick(R.id.iv_back)
    void close(){
        finish();
    }       //利用蝴蝶刀给iv_back调教销毁活动操作，也就是返回上一活动页
}


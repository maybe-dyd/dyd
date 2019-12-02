package com.example.jxxy.mobileshop_dyd.activity;

import android.widget.TextView;

import com.example.jxxy.mobileshop_dyd.R;
import com.example.jxxy.mobileshop_dyd.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
//修我的收藏活动操作页
public class MyCollectActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @Override
    public int getContentViewId(){
        return R.layout.activity_my_collect;
    }
    @Override
    protected void initView(){
        super.initView();
        tvTitle.setText("我的收藏");
    }
    @OnClick(R.id.iv_back)
    void close(){
        finish();
    }
}

package com.example.jxxy.mobileshop_dyd.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jxxy.mobileshop_dyd.R;

import com.example.jxxy.mobileshop_dyd.activity.ChangePwdActivity;
import com.example.jxxy.mobileshop_dyd.activity.LoginActivity;
import com.example.jxxy.mobileshop_dyd.activity.MyAddressActivity;
import com.example.jxxy.mobileshop_dyd.activity.MyCollectActivity;
import com.example.jxxy.mobileshop_dyd.activity.MyOrderActivity;
import com.example.jxxy.mobileshop_dyd.common.BaseFragment;
import com.example.jxxy.mobileshop_dyd.utils.SystemCofig;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;
//个人中心页
public class PersonalFragment extends BaseFragment {
    @BindView(R.id.personal_for_login)
    RelativeLayout personal_for_login;

    @BindView(R.id.user_img_view)
    ImageView user_img_view;

    @BindView(R.id.user_name)
    TextView user_name;

    @BindView(R.id.user_level)
    TextView user_level;

    @BindView(R.id.personal_for_not_login)
    RelativeLayout personal_for_not_login;

    @BindView(R.id.person_logout_layout)
    RelativeLayout person_logout_layout;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        resetUI();
    }

    private void resetUI(){
        if(SystemCofig.isLogin()){
            personal_for_login.setVisibility(View.VISIBLE);
            personal_for_not_login.setVisibility(View.GONE);
            person_logout_layout.setVisibility(View.VISIBLE);

            ImageLoader.getInstance().displayImage(SystemCofig.getLoginUserHead(),user_img_view);

            user_name.setText(SystemCofig.getLoginUserName());

            user_level.setText(SystemCofig.getLoginUserEmail());
        }else{
            personal_for_login.setVisibility(View.GONE);
            personal_for_not_login.setVisibility(View.VISIBLE);
            person_logout_layout.setVisibility(View.GONE);
        }
    }//重新设置UI,其中利用设置setVisibility属性进行展示和隐藏登录/注册布局，以此表现登录过程
    @OnClick(R.id.person_login)
    void login(){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivityForResult(intent,1000);
    }

    @OnClick(R.id.person_my_order)
    void person_my_order(){
        if (SystemCofig.isLogin()){
            startActivity(new Intent(getActivity(), MyOrderActivity.class));
        }else{
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivityForResult(intent,1001);
        }
    }

    @OnClick(R.id.my_collect)
    void my_collect(){
        if (SystemCofig.isLogin()){
            startActivity(new Intent(getActivity(), MyCollectActivity.class));
        }else{
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivityForResult(intent,1002);
        }
    }

    @OnClick(R.id.my_address)
    void my_address(){
        if(SystemCofig.isLogin()){
            startActivity(new Intent(getActivity(), MyAddressActivity.class));
        }else{
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivityForResult(intent,1003);
        }
    }

    @OnClick(R.id.my_account)
    void my_account(){
        if(SystemCofig.isLogin()){
            startActivity(new Intent(getActivity(), ChangePwdActivity.class));
        }else{
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivityForResult(intent,1004);
        }
    }
//利用startActivityForResult得到新打开Activity 关闭后返回的数据
    @OnClick(R.id.person_logout_layout)
    void logout(){
        SystemCofig.logout();
        resetUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode== Activity.RESULT_OK){
            resetUI();

            Intent intent = new Intent();
            if (requestCode==1000){

            }else if (requestCode==1001){
                intent.setClass(getActivity(),MyOrderActivity.class);
                startActivity(intent);
            }else if (requestCode==1002){
                intent.setClass(getActivity(),MyCollectActivity.class);
                startActivity(intent);
            }else if (requestCode==1003){
                intent.setClass(getActivity(),MyAddressActivity.class);
                startActivity(intent);
            }else if (requestCode==1004){
                intent.setClass(getActivity(),ChangePwdActivity.class);
                startActivity(intent);
            }
        }
    }//根据代码进入相应活动，这样用户登录后不用重复操作，加强了交互性
}

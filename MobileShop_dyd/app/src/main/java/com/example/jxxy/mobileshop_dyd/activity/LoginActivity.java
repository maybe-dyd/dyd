package com.example.jxxy.mobileshop_dyd.activity;


import android.text.TextUtils;
import android.widget.EditText;

import com.example.jxxy.mobileshop_dyd.R;
import com.example.jxxy.mobileshop_dyd.common.BaseActivity;
import com.example.jxxy.mobileshop_dyd.http.ProgressDialogSubscriber;
import com.example.jxxy.mobileshop_dyd.http.entity.MemberEntity;
import com.example.jxxy.mobileshop_dyd.http.presenter.MemberPresenter;
import com.example.jxxy.mobileshop_dyd.utils.SystemCofig;

import butterknife.BindView;
import butterknife.OnClick;
//登录活动
public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_pwd)
    EditText etPwd;


    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.iv_back)
    void close(){finish();}

    @OnClick(R.id.bt_login)
    void login(){
        String userName = etUsername.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        //利用TextUtils判断账号和密码是否为空
        if(TextUtils.isEmpty(userName)){
            toastshort("请输入用户名");
            return;
        }if (TextUtils.isEmpty(pwd)){
            toastshort("请输入密码");
            return;
        }

        MemberPresenter.login(new ProgressDialogSubscriber<MemberEntity>(this) {
            @Override
            public void onNext(MemberEntity memberEntity) {
                SystemCofig.setLogin(true);
                toastshort("登陆成功");
                SystemCofig.setLoginUserName(memberEntity.uname);
                SystemCofig.setLoginUserEmail(memberEntity.email);
                SystemCofig.setLoginUserHead(memberEntity.image);
                setResult(RESULT_OK);
                finish();
            }

        },userName,pwd);//先用 MemberPresenter执行登录方法，然后用SystemCofig进行本地存储登陆信息
    }
}


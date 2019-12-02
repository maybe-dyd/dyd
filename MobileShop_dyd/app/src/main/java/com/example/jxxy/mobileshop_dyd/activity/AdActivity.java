package com.example.jxxy.mobileshop_dyd.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jxxy.mobileshop_dyd.R;
import com.example.jxxy.mobileshop_dyd.common.BaseActivity;
import com.example.jxxy.mobileshop_dyd.common.MobileShopApp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

//广告页
public class AdActivity extends BaseActivity {
    private TextView tv_skip;

    @Override
    public int getContentViewId(){
        return R.layout.activity_ad;
    }
    @Override
    protected void initView(){
        super.initView();
        tv_skip=(TextView)findViewById(R.id.tv_skip);
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        ImageView imageView=(ImageView)findViewById(R.id.iv_image);
        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575256881671&di=79e5f1523414f8b19d9349e0d7d129be&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201701%2F11%2F20170111183425_TCxLR.thumb.700_0.jpeg";//图片链接，为Imageloader方式加载，其他还可通过HttpURLConnection、HttpClients、Volley、XUtils、OkHttp等方式进行加载网络图片。

        ImageLoader.getInstance().displayImage(url, imageView, new ImageLoadingListener() {     //将jumpThread传到handler中进行执行
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                MobileShopApp.handler.post(jumpThread);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                MobileShopApp.handler.post(jumpThread);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

                MobileShopApp.handler.post(jumpThread);
            }
        });
    }

    //点击跳过操作
    private void skip(){
        Intent intent=new Intent(AdActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        c_count=COUNT;
        MobileShopApp.handler.removeCallbacks(jumpThread); //removeCallbacks为自动移除jumpThread，以此实现跳过
    }

    private static final String SKIP_TEXT="点击跳过 %d";
    private final static int COUNT=5;
    private final static int DELAYED=1000;
    private int c_count=COUNT;

    private Runnable jumpThread=new Runnable() {
        @Override
        public void run() {
            if(c_count<=0){
                skip();
            }else {
                tv_skip.setText(String.format(SKIP_TEXT,c_count));
                c_count--;
                MobileShopApp.handler.postDelayed(jumpThread,DELAYED); //线程延迟一秒
            }
        }
    };
}

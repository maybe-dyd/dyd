package com.example.jxxy.mobileshop_dyd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import java.util.jar.Attributes;

public class MyWebView extends WebView {
    private IWebViewScroll mIwebViewScroll;
    public MyWebView(Context context) {super(context,null);}
    public MyWebView(Context context, AttributeSet attrs) {super(context,attrs,0);}
    public MyWebView(Context context,AttributeSet attrs,int defStyleAttr) {super(context,attrs,defStyleAttr);}
    @Override
    protected void onScrollChanged(int l,int t,int oldl,int oldt) {
        super.onScrollChanged(l,t,oldl,oldt);
        if (mIwebViewScroll != null && t ==0) {
            mIwebViewScroll.onTop();
        } else if (mIwebViewScroll != null && t != 0) {
            mIwebViewScroll.notOnTop();
        }
    }

    public void setOnCustomScrollChanged(IWebViewScroll listener) {
        this.mIwebViewScroll = listener;
    }

    public interface IWebViewScroll {
        void onTop();
        void notOnTop();
    }
}

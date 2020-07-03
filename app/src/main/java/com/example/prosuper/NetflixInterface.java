package com.example.prosuper;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;
    WebView mView;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c, WebView view) {
        mContext = c;
        mView = view;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void makeMSLRequest(String request) {
//        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        mView.post(new Runnable() {
            @Override
            public void run() {
                mView.loadUrl("javascript:window.enjoy.testConnection()");
            }
        });

    }

}

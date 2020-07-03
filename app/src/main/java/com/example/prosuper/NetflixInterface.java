package com.example.prosuper;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class NetflixInterface {
    Context mContext;
    WebView mView;
    WebView mEnjoySiteView;

    /** Instantiate the interface and set the context */
    NetflixInterface(Context c, WebView view, WebView enjoySiteView) {
        mContext = c;
        mView = view;
        mEnjoySiteView = enjoySiteView;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void relayMSLResponse(final String response) {
//        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        mEnjoySiteView.post(new Runnable() {
            @Override
            public void run() {
                Log.d("MSLResponse", response);
                String fixed = response.replace("'", "&apos;");
                fixed = fixed.replace("\\\"", "&quot;");
                Log.d("MSLResponse", "fixed" + fixed);
                mEnjoySiteView.loadUrl("javascript:window.enjoy.onMSLResponse('" + fixed + "')");
            }
        });

    }

}

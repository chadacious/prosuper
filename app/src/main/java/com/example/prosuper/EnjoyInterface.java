package com.example.prosuper;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class EnjoyInterface {
    Context mContext;
    WebView mView;
    WebView mProviderSiteView;

    /** Instantiate the interface and set the context */
    EnjoyInterface(Context c, WebView view, WebView providerSiteView) {
        mContext = c;
        mView = view;
        mProviderSiteView = providerSiteView;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void sendMSLRequest(final String url, final String mode, final String jsonRequest) {
        mProviderSiteView.post(new Runnable() {
            @Override
            public void run() {
                String fixed = jsonRequest.replace("'", "&apos;");
                fixed = fixed.replace("\\\"", "&quot;");
                Log.d("CHECK JSON", "fixed" + fixed);
                Log.d("CHECK JSON", String.format("javascript:window.enjoyNetflix.makeMSLRequest('%s', '%s', '%s')",
                        url,
                        mode,
                        fixed));
                mProviderSiteView.loadUrl(
                    String.format("javascript:window.enjoyNetflix.makeMSLRequest('%s', '%s', '%s')",
                        url,
                        mode,
                            fixed)
                );
            }
        });

    }

}

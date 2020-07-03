package com.example.prosuper;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Base64;

import static android.net.Uri.encode;

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
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
//                String fixed = jsonRequest.replace("'", "&apos;");
//                fixed = fixed.replace("\\\"", "&quot;");
//                Log.d("CHECK JSON", "fixed" + fixed);
//                Log.d("CHECK JSON", String.format("javascript:window.enjoyNetflix.makeMSLRequest('%s', '%s', '%s')",
//                        url,
//                        mode,
//                        fixed));
                String b64Request = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    b64Request = Base64.getEncoder().encodeToString(jsonRequest.getBytes());
                }
                Log.d("JS TO EXECUTE", b64Request);

                final String jsToExecute = String.format("window.enjoyNetflix.makeMSLRequest('%s', '%s', '%s')",
                        url,
                        mode,
                        b64Request);
                Log.d("JS TO EXECUTE", jsToExecute);

//                Log.d("JS TO EXECUTE", jsToExecute);
                mProviderSiteView.evaluateJavascript(jsToExecute, null);
//                , new ValueCallback<String>() {
//                    @Override
//                    public void onReceiveValue(String s) {
//                        Log.d("OnReceiveValue", s);
//                        String enjoyJSToExecute = String.format("window.enjoy.onMSLResponse('%s')", s);
//                        Log.d("OnReceiveValue", enjoyJSToExecute);
//                        mView.evaluateJavascript(enjoyJSToExecute, null);
//                    }
//                });
//                mProviderSiteView.loadUrl(
//                    String.format("javascript:window.enjoyNetflix.makeMSLRequest('%s', '%s', '%s')",
//                        url,
//                        mode,
//                        encode(jsonRequest))
//                );
            }
        });

    }

}

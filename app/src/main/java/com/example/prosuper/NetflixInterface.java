package com.example.prosuper;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Base64;

import static android.net.Uri.encode;

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
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                Log.d("MSLResponse", response);
                String b64Response = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    b64Response = Base64.getEncoder().encodeToString(response.getBytes());
                }
                Log.d("JS RESPONSE", b64Response);

                final String jsToExecute = String.format("window.enjoy.onMSLResponse('%s')",
                        b64Response);
                Log.d("JS TO EXECUTE", jsToExecute);
//                String fixed = response.replace("'", "&apos;");
//                fixed = fixed.replace("\\\"", "&quot;");
//                Log.d("MSLResponse", "fixed" + fixed);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mEnjoySiteView.evaluateJavascript(jsToExecute, null);
                }
            }
        });

    }

}

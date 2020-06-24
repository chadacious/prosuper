package com.example.prosuper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    ProgressBar superProgressBar;
    ImageView superImageView;
    WebView superWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superProgressBar = findViewById(R.id.myProgressBar);
        superImageView = findViewById(R.id.myImageView);
        superWebView = findViewById(R.id.myWebView);

        superProgressBar.setMax(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        String newUA = "Dalvik/2.1.0 (Linux; U; Android 9; SM-N950U1 Build/PPR1.180610.011)";
        superWebView.getSettings().setUserAgentString(newUA);
        superWebView.loadUrl("https://www.google.com");
        superWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        superWebView.getSettings().setJavaScriptEnabled(true);
//        superWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        superWebView.getSettings().setDomStorageEnabled(true);
        superWebView.getSettings().setDatabaseEnabled(true);
        superWebView.getSettings().setAppCacheEnabled(true);
        superWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        if (18 < Build.VERSION.SDK_INT ){
//            //18 = JellyBean MR2, KITKAT=19
//            superWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        }
//        if (Build.VERSION.SDK_INT >= 19) {
//            superWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }
        superWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String newUrl = url.replace("intent:", "https:").split("#")[0];
                Toast.makeText(getApplicationContext(), newUrl, Toast.LENGTH_LONG).show();
                Map<String, String> extraHeaders = new HashMap<String, String>();
//                extraHeaders.put("sec-ch-ua-mobile", "?0");
//                extraHeaders.put("sec-ch-ua", "\"Google Chrome\"; v=\"83\"");
                extraHeaders.put("Cache-Control", "max-age=0");
//                extraHeaders.put("Sec-Fetch-Site", "same-origin");
                view.loadUrl(newUrl, extraHeaders);

//                view.loadUrl(url);
                return true;
//                if (url.startsWith("http") || url.startsWith("https")) {
//                    return true;
//                } else {
//                    superWebView.stopLoading();
//                    superWebView.goBack();
//                    makeText(MainActivity.this, "Unknown Link, unable to handle", LENGTH_SHORT).show();
//                }
//                return false;
            }

//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//
//                try {
//                    DefaultHttpClient client = new DefaultHttpClient();
//                    HttpGet httpGet = new HttpGet(url);
//                    httpGet.setHeader("MY-CUSTOM-HEADER", "header value");
//                    httpGet.setHeader(HttpHeaders.USER_AGENT, "custom user-agent");
//                    HttpResponse httpReponse = client.execute(httpGet);
//
//                    PreferenceActivity.Header contentType = httpReponse.getEntity().getContentType();
//                    PreferenceActivity.Header encoding = httpReponse.getEntity().getContentEncoding();
//                    InputStream responseInputStream = httpReponse.getEntity().getContent();
//
//                    String contentTypeValue = null;
//                    String encodingValue = null;
//                    if (contentType != null) {
//                        contentTypeValue = contentType.getValue();
//                    }
//                    if (encoding != null) {
//                        encodingValue = encoding.getValue();
//                    }
//                    return new WebResourceResponse(contentTypeValue, encodingValue, responseInputStream);
//                } catch (ClientProtocolException e) {
//                    //return null to tell WebView we failed to fetch it WebView should try again.
//                    return null;
//                } catch (IOException e) {
//                    //return null to tell WebView we failed to fetch it WebView should try again.
//                    return null;
//                }
//            }
        });
        superWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                superImageView.setImageBitmap(icon);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                String[] resources;
                resources = request.getResources();
                for (int i = 0; i < resources.length; i++) {
                    if (PermissionRequest.RESOURCE_PROTECTED_MEDIA_ID.equals(resources[i])) {
                        request.grant(resources);
                        return;
                    }
                }
                super.onPermissionRequest(request);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (superWebView.canGoBack()) {
            superWebView.goBack();
        } else {
            finish();
        }
    }

}
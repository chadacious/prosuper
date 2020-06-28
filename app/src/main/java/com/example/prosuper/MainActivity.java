package com.example.prosuper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

//    ProgressBar superProgressBar;
//    ImageView superImageView;
    WebView superWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        superProgressBar = findViewById(R.id.myProgressBar);
//        superImageView = findViewById(R.id.myImageView);
        superWebView = findViewById(R.id.myWebView);

//        superProgressBar.setMax(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
//        String newUA = "Mozilla/5.0 (X11; U; Linux armv7l like Android; en-us) AppleWebKit/531.2+ (KHTML, like Gecko) Version/5.0 Safari/533.2+ Kindle/3.0+";
//        String newUA = "Dalvik/2.1.0 (Linux; U; Android 9; SM-N950U1 Build/PPR1.180610.011)";
//        String newUA = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.45 Safari/535.19";
//        String newUA = "Mozilla/5.0 (X11; CrOS armv7l 11895.95.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.125 Safari/537.36";
        String newUA = "Mozilla/5.0 (X11; CrOS armv7l 13020.82.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.112 Safari/537.36";
//        String newUA = "Mozilla/5.0 (X11; CrOS armv7l 6946.86.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
//        String newUA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36";
        //        String newUA = "Mozilla/5.0 (Linux; Android 5.1; AFTS Build/LMY47O) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/41.99900.2250.0242 Safari/537.36";
//        String newUA = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";
//        String newUA = "WeMesh/4.0.6 (Linux;Android 9) ExoPlayerLib/1.5.9";
        superWebView.getSettings().setUserAgentString(newUA);
//        superWebView.loadUrl("https://www.enjoymoviesyourway.net/tvapp");
        superWebView.loadUrl("https://www.google.com");
//        superWebView.loadUrl("https://www.netflix.com");
        superWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        superWebView.getSettings().setJavaScriptEnabled(true);
        superWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        superWebView.getSettings().setDomStorageEnabled(true);
        superWebView.getSettings().setDatabaseEnabled(true);
        superWebView.getSettings().setAppCacheEnabled(true);
        superWebView.getSettings().setLoadWithOverviewMode(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            superWebView.getSettings().setSafeBrowsingEnabled(false);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            superWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//            superWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }

//        superWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
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
                makeText(getApplicationContext(), newUrl, LENGTH_LONG).show();
                Map<String, String> extraHeaders = new HashMap<String, String>();
                extraHeaders.put("sec-ch-ua-mobile", "?0");
                extraHeaders.put("sec-ch-ua", "\"Google Chrome\"; v=\"83\"");
                extraHeaders.put("Cache-Control", "max-age=0");
                extraHeaders.put("Sec-Fetch-Site", "same-origin");
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
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageFinished(view, url);
//                view.loadUrl(
//                        "javascript:(function() { " +
//                                "console.log('Hey howdy hey 123!', window.document.title);" +
//                                "})()");
//            }
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                view.loadUrl(
//                        "javascript:(function() { " +
//                                "console.log('Hey howdy hey 321!', window.document.title);" +
//                                "})()");
//            }
//            // Handle API until level 21
//            @SuppressWarnings("deprecation")
//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//
//                if (url.indexOf("nflxvideo.net") > -1) {
//                    return getNewResponse(url);
//                } else {
//                    return super.shouldInterceptRequest(view, url);
//                }
//            }
//
//            // Handle API 21+
//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//
//                String url = request.getUrl().toString();
//                if (url.indexOf("nflxvideo.net") > -1) {
//                    Log.d("xhr", request.toString());
//                    return getNewResponse(url);
//                } else {
//                    return super.shouldInterceptRequest(view, request);
//                }
//
//            }
//
//            private WebResourceResponse getNewResponse(String url) {
//
//                try {
//                    OkHttpClient httpClient = new OkHttpClient();
//
//                    Request request = new Request.Builder()
//                            .url(url.trim())
//                            .addHeader("User-Agent", "User-Agent: WeMesh/4.0.6 (Linux;Android 9) ExoPlayerLib/1.5.9") // Example header
//                            .addHeader("Accept-Encoding", "identity") // Example header
//                            .addHeader("Host", "ipv4-c182-sea001-ix.1.oca.nflxvideo.net") // Example header
//                            .addHeader("Connection", "Keep-Alive") // Example header
//                            .addHeader("Access-Control-Allow-Origin", "*")
//                            .build();
//
//                    Response response = httpClient.newCall(request).execute();
//
//                    return new WebResourceResponse(
//                            null,
//                            response.header("content-encoding", "utf-8"),
//                            response.body().byteStream()
//                    );
//
//                } catch (Exception e) {
//                    return null;
//                }
//
//            }
        });
        superWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                getSupportActionBar().setTitle(title);
//            }

//            @Override
//            public void onReceivedIcon(WebView view, Bitmap icon) {
//                super.onReceivedIcon(view, icon);
//                superImageView.setImageBitmap(icon);
//            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                String[] resources;
                resources = request.getResources();
                for (int i = 0; i < resources.length; i++) {
//                    Log.d("PermissionRequest", resources[i].toString());
                    if (PermissionRequest.RESOURCE_PROTECTED_MEDIA_ID.equals(resources[i])) {
                        Log.d("Granted", resources[i].toString());
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
package com.example.ashutoshgautam.githubconsumer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ashutoshgautam on 16/12/16.
 */
public class FeedDetailsActivity extends AppCompatActivity{

    private FeedItem feed;
    private WebView mWebView;
    private boolean mIsWebViewAvailable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        feed = (FeedItem) this.getIntent().getSerializableExtra("feed");

        mWebView = (WebView) findViewById(R.id.webview);
        mIsWebViewAvailable = true;
        WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        mWebView.loadUrl(feed.html_url);

        mWebView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void enableBack(boolean enabled) {

            }
        }, "AndroidBinding");
    }
}

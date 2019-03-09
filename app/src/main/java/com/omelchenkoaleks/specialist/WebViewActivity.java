package com.omelchenkoaleks.specialist;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // так как мы получаем объект, который сам показывает...
        mWebView = new WebView(this);
        // можно обойтись без разметки и просто передать активити объект WebView:
        setContentView(mWebView);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.google.com");

        mWebView.setWebViewClient(new WebViewSampleClient());
    }

    private class WebViewSampleClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

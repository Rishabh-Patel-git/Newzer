package com.example.newzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MyWebPage extends AppCompatActivity {
private Toolbar mToolBar;
private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mToolBar = findViewById(R.id.webview_toolbar);

        mWebView = findViewById(R.id.web_view);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Toast.makeText(this,"Opening Link",Toast.LENGTH_SHORT).show();
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(url);

    }
}
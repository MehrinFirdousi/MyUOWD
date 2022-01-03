package com.example.myuowd;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class WebPageFragment extends Fragment implements View.OnClickListener{

    String url;
    WebView webView;
    ProgressBar spinner;
    boolean webViewInitialUse;
    int[] buttonIds = { R.id.backButton, R.id.backText, R.id.webBackButton, R.id.webForwardButton };

    public WebPageFragment(String url) {
        this.url = url;
        webViewInitialUse = true;
    }

    public static WebPageFragment newInstance(String url) {
        WebPageFragment fragment = new WebPageFragment(url);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_page, container, false);
        webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        spinner = (ProgressBar)view.findViewById(R.id.progressSpinner);
        webView.setWebViewClient(new CustomWebViewClient());
        webView.loadUrl(url);

        for (int id : buttonIds)
            view.findViewById(id).setOnClickListener(this::onClick);
        return view;
    }

    // This class allows for a loading progress spinner and hide the progress spinner once the webpage loads
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            if (webViewInitialUse)
                webview.setVisibility(webview.INVISIBLE); // making the webview invisible so that only the progress spinner is visible until the page loads
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            webViewInitialUse = false;
            spinner.setVisibility(View.GONE);
            view.setVisibility(webView.VISIBLE);
            super.onPageFinished(view, url);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
            case R.id.backText:
                getActivity().onBackPressed();
            case R.id.webBackButton:
                if (webView.canGoBack())
                    webView.goBack();
                break;
            case R.id.webForwardButton:
                if (webView.canGoForward())
                    webView.goForward();
                break;
        }
    }
}
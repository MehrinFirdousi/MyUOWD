package com.example.myuowd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WebPageFragment extends Fragment {

    String url;

    public WebPageFragment(String url) {
        this.url = url;
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
        WebView webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        ImageView backButton = view.findViewById(R.id.backButton);
        TextView backText = view.findViewById(R.id.backText);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "back clicked", Toast.LENGTH_SHORT).show();

                switch(v.getId()) {
                    case R.id.backButton:
                    case R.id.backText:
                        getActivity().onBackPressed();
                }
            }
        });

        ImageView webBackButton = view.findViewById(R.id.webBackButton);
        ImageView webForwardButton = view.findViewById(R.id.webForwardButton);

        webBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack())
                    webView.goBack();
            }
        });
        webForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward())
                    webView.goForward();
            }
        });
        return view;
    }
}
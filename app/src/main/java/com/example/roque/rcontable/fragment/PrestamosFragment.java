package com.example.roque.rcontable.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.roque.rcontable.R;


public class PrestamosFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    View vistap;

    WebView wv;
    String WEB_ROOT;
    SwipeRefreshLayout webRefresh;

    public PrestamosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vistap = inflater.inflate(R.layout.fragment_prestamos, container, false);

        WEB_ROOT = getResources().getString(R.string.web_root_prestamos);
        webRefresh = (SwipeRefreshLayout)vistap.findViewById(R.id.swipeRP);
        webRefresh.setOnRefreshListener(this);
        wv = (WebView) vistap.findViewById(R.id.webViewP);

        webAction();
        return vistap;
    }

    private void webAction() {


        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.loadUrl(WEB_ROOT);
        webRefresh.setRefreshing(true);
        wv.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                wv.loadUrl("file:///android_asset/error.html");
            }

            public void onPageFinished(WebView view, String url) {
                webRefresh.setRefreshing(false);
            }

        });

    }

    @Override
    public void onRefresh() {
        webAction();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}

package com.example.roque.rcontable.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.roque.rcontable.R;
import com.example.roque.rcontable.interfaces.OnBackPressedListener;


public class InicioFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener{

    View vista;

    WebView wv;
    String WEB_ROOT;
    SwipeRefreshLayout webRefresh;

    public InicioFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_inicio, container, false);

        WEB_ROOT = getResources().getString(R.string.web_root);
        webRefresh = (SwipeRefreshLayout)vista.findViewById(R.id.swipeR);
        webRefresh.setOnRefreshListener(this);
        wv = (WebView) vista.findViewById(R.id.webView);

        webAction();
        return vista;
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

package com.ultimatix.cashrich.ui;


import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ultimatix.cashrich.Myapp;
import com.ultimatix.cashrich.R;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {


    @BindView(R.id.webview)
    WebView webView;

    Unbinder unbinder;

    private String url;
    private ProgressDialog mProgress;

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = ItemFragmentArgs.fromBundle(getArguments()).getUrl();
        mProgress = new ProgressDialog(getActivity());
        mProgress.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        webView.getSettings().setAppCacheMaxSize( 50 * 1024 * 1024 ); // 50MB
        webView.getSettings().setAppCachePath( getActivity().getApplicationContext().getCacheDir().getAbsolutePath() );
        webView.getSettings().setAllowFileAccess( true );
        webView.getSettings().setAppCacheEnabled( true );
        webView.getSettings().setJavaScriptEnabled( true );
        webView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT ); // load online by default
        if ( !((Myapp)getActivity().getApplication()).isNetworkAvailable() ) { // loading offline
            webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }
        webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    mProgress.dismiss();
                }
                else{
                    mProgress.setMessage("Loading " + String.valueOf(progress) + "%");
                    mProgress.show();
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                return false;
            }
        });
        webView.loadUrl(url);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }
    }
}

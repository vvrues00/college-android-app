package com.raat.rat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class alert extends Fragment {
    WebView web;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_alert, container, false);
        web=view.findViewById(R.id.webv2);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("http://www.cusat.ac.in/exam_notif.php");
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event




}

package com.raat.rat;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class resu extends Fragment {
ProgressDialog progressDialog;
String a,b,c,send;
WebView web;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        a=getArguments().getString("sem");
        b=getArguments().getString("branch");
        c=getArguments().getString("roll");
        try {
            send=new vAsync(getContext()).execute().get();
        } catch (InterruptedException e) {
         Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        View v=inflater.inflate(R.layout.fragment_resu, container, false);
    web=v.findViewById(R.id.steam);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("http://exam.cusat.ac.in/");

        return v;}


         class vAsync extends AsyncTask<String,String,String>{
            public String url1="http://10.0.2.2/par/app_internal.php?sem="+a+"&branch="+b+"&roll="+c;
             Context context;
            vAsync(Context context){

                 this.context=context;

             }
             @Override
             protected void onPreExecute() {
                 super.onPreExecute();
                 progressDialog= ProgressDialog.show(context,"","loading..",false);

             }

             @Override
             protected void onPostExecute(String s) {
                 super.onPostExecute(s);
                 progressDialog.dismiss();
                 Toast.makeText(context,s,Toast.LENGTH_LONG).show();
             }

             @Override
             protected String doInBackground(String... strings) {
                 try {
                     URL url=new URL(url1);

                     HttpClient httpClient=new DefaultHttpClient();
                     HttpGet get=new HttpGet();
                     get.setURI(new URI(url1));
                     HttpResponse response=httpClient.execute(get);
                     BufferedReader br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                     StringBuffer sb=new StringBuffer("");
                     String line="";
                     while((line=br.readLine())!=null){

                         sb.append(line);
                         break;
                     }
                     br.close();
                     return sb.toString();
                 } catch (Exception e) {
                     Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG);
                 }
                 return null;
             }
         }


    }







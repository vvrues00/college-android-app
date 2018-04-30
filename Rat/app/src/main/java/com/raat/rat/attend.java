package com.raat.rat;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;


public class attend extends Fragment  {
private ProgressDialog progressDialog;

    String  a;
    String b;
    String c;
    String send;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


     a=getArguments().getString("sem");
     b=getArguments().getString("branch");
     c=getArguments().getString("roll");
     Toast.makeText(getContext(),a+" "+b+" "+c,Toast.LENGTH_LONG).show();
        try {
            send=new MyasyncTask(getContext()).execute().get();
            Toast.makeText(getContext(),send,Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
           Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


        View myFragmentView=inflater.inflate(R.layout.fragment_attend, container, false);

        TableLayout t1=(TableLayout)myFragmentView.findViewById(R.id.main_table);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);

        TableRow tr_head = new TableRow(getContext());
        tr_head.setLayoutParams(rowParams);

        tr_head.setId(View.generateViewId());
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView label_date = new TextView(getContext());
        label_date.setId(View.generateViewId());
        label_date.setText("DATE");
        label_date.setTextColor(Color.BLACK);
        label_date.setPadding(10, 5, 5, 5);
        tr_head.addView(label_date);                                            // add the column to the table row here

        TextView H_1 = new TextView(getContext());
        H_1.setId(View.generateViewId());                                              // define id that must be unique
        H_1.setText("1");                         // set the text for the header
        H_1.setTextColor(Color.BLACK);                  // set the color
        H_1.setPadding(80, 10, 80, 10); // set the padding (if required)
        tr_head.addView(H_1);                               // add the column to the table row here

        TextView H_2 = new TextView(getContext());
        H_2.setId(View.generateViewId());                                              // define id that must be unique
        H_2.setText("2");                         // set the text for the header
        H_2.setTextColor(Color.BLACK);                  // set the color
        H_2.setPadding(20, 10, 80, 10); // set the padding (if required)
        tr_head.addView(H_2);

        TextView H_3 = new TextView(getContext());
        H_3.setId(View.generateViewId());                                              // define id that must be unique
        H_3.setText("3");                         // set the text for the header
        H_3.setTextColor(Color.BLACK);                  // set the color
        H_3.setPadding(20 , 10, 80, 10); // set the padding (if required)
        tr_head.addView(H_3);

        TextView H_4 = new TextView(getContext());
        H_4.setId(View.generateViewId());                                              // define id that must be unique
        H_4.setText("4");                         // set the text for the header
        H_4.setTextColor(Color.BLACK);                  // set the color
        H_4.setPadding(20, 10, 80, 10); // set the padding (if required)
        tr_head.addView(H_4);

        TextView H_5 = new TextView(getContext());
        H_5.setId(View.generateViewId());                                              // define id that must be unique
        H_5.setText("5");                         // set the text for the header
        H_5.setTextColor(Color.BLACK);                  // set the color
        H_5.setPadding(20, 10, 80,10); // set the padding (if required)
        tr_head.addView(H_5);

        TextView H_6 = new TextView(getContext());
        H_6.setId(View.generateViewId());                                              // define id that must be unique
        H_6.setText("6");                         // set the text for the header
        H_6.setTextColor(Color.BLACK);                  // set the color
        H_6.setPadding(20, 10, 80, 10); // set the padding (if required)
        tr_head.addView(H_6);


        t1.addView(tr_head, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        //Toast.makeText(getContext(),a,Toast.LENGTH_LONG).show();

        //Add Content to Arraylist
        //List<List<String>> cursor= new ArrayList<>();
try{
    JSONArray jsonArray=new JSONArray(send);


        for(int count=0;count<jsonArray.length();count++) {
            JSONObject jsonObject=jsonArray.getJSONObject(count);
            String date = jsonObject.getString("date");  // get the first variable
            String h_1 = jsonObject.getString("hour1");                // get the second variable
            String h_2 = jsonObject.getString("hour2");
            String h_3 = jsonObject.getString("hour3");
            String h_4 = jsonObject.getString("hour4");
            String h_5 = jsonObject.getString("hour5");
            String h_6 = jsonObject.getString("hour6");
            // Create the table row
            TableRow tr = new TableRow(getContext());

            if (count % 2 != 0) tr.setBackgroundColor(Color.GRAY);

            tr.setId(100 + count);
            tr.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            //Create 6 columns to add as table data
            // Create a TextView to add date
            TextView labelDATE = new TextView(getContext());
            labelDATE.setId(200 + count);
            labelDATE.setText(date);
            labelDATE.setPadding(20, 0, 50, 0);
            labelDATE.setTextColor(Color.BLUE);
            tr.addView(labelDATE);

            TextView labelh_1 = new TextView(getContext());
            labelh_1.setId(200 + count);
            labelh_1.setText(h_1);
            labelh_1.setPadding(90, 0, 5, 0);
            labelh_1.setTextColor(Color.BLUE);
            tr.addView(labelh_1);

            TextView labelh_2 = new TextView(getContext());
            labelh_2.setId(200 + count);
            labelh_2.setText(h_2);
            labelh_2.setPadding(20, 0, 5, 0);
            labelh_2.setTextColor(Color.BLUE);
            tr.addView(labelh_2);

            TextView labelh_3 = new TextView(getContext());
            labelh_3.setId(200 + count);
            labelh_3.setText(h_3);
            labelh_3.setTextColor(Color.BLUE);
            labelh_3.setPadding(20, 0, 5, 0);
            tr.addView(labelh_3);

            TextView labelh_4 = new TextView(getContext());
            labelh_4.setId(200 + count);
            labelh_4.setText(h_4);
            labelh_4.setTextColor(Color.BLUE);
            tr.addView(labelh_4);
            labelh_4.setPadding(20, 0, 5, 0);

            TextView labelh_5 = new TextView(getContext());
            labelh_5.setId(200 + count);
            labelh_5.setText(h_5);
            labelh_5.setTextColor(Color.BLUE);
            tr.addView(labelh_5);
            labelh_5.setPadding(20, 0, 5, 0);

            TextView labelh_6 = new TextView(getContext());
            labelh_6.setId(200 + count);
            labelh_6.setText(h_6);
            labelh_6.setTextColor(Color.BLUE);
            tr.addView(labelh_6);
            labelh_6.setPadding(20, 0, 5, 0);

            // finally add this to the table row
            t1.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));


        }  }catch(Exception e){


}
        return myFragmentView;

    }

    class MyasyncTask extends AsyncTask<String,String,String>{
        public  String url1="http://10.0.2.2/par/app_attend.php?sem="+a+"&branch="+b+"&roll="+c;
        private Context context;
        MyasyncTask(Context context){

            this.context=context;

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        progressDialog=ProgressDialog.show(context,"","loading..",false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(context,s,Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
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
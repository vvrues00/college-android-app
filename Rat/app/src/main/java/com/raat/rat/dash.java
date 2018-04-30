package com.raat.rat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class dash extends Fragment {

TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v= inflater.inflate(R.layout.fragment_dash, container, false);
        t1=(TextView)v.findViewById(R.id.t1);
        t2=(TextView)v.findViewById(R.id.t2);
        t3=(TextView)v.findViewById(R.id.t3);
        t4=(TextView)v.findViewById(R.id.t4);
        t5=(TextView)v.findViewById(R.id.t5);
        t6=(TextView)v.findViewById(R.id.t6);
        t7=(TextView)v.findViewById(R.id.t7);
        t8=(TextView)v.findViewById(R.id.t8);
        t9=(TextView)v.findViewById(R.id.t9);

        try {
            String m=getArguments().getString("name");
             t1.setText("NAME      :"+m);
            t2.setText("BRANCH    :"+getArguments().getString("branch"));
    t3.setText("SEMESTER  :"+getArguments().getString("sem"));
    t5.setText("MOB NO    :"+getArguments().getString("mobile"));
    t6.setText("EMAIL ID  :"+getArguments().getString("email"));
    t4.setText("ROLL      :"+getArguments().getString("roll"));
    t7.setText("FATHER    :"+getArguments().getString("parent"));
    t8.setText("LANDLINE  :"+getArguments().getString("pphone"));
    t9.setText("P ADDRESS :"+getArguments().getString("address"));

        }catch (Exception e){

            t1.setText(e.toString());

        }


    return v;
    }




}

package com.raat.rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class login extends AppCompatActivity implements AsyncResponse, View.OnClickListener, AdapterView.OnItemSelectedListener {
   Button btn;
   EditText name,password,sem,roll;
   Spinner n;
  public String t;
   public static String rroll, rname, rcur, rbranch, rmob, rp_name, rp_phone, rperm_add, remail,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn=(Button)findViewById(R.id.login_btn);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        sem=(EditText)findViewById(R.id.semester );
        roll=(EditText)findViewById(R.id.roll);
        n=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        n.setAdapter(adapter);
        n.setOnItemSelectedListener(this);

        PostResponseAsyncTask post= new PostResponseAsyncTask(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void processFinish(String s) {
        //Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    if(!s.equals("failure") || !s.equals("dead")||!s.equals(" ")){
      try {
            JSONArray x=new JSONArray(s);


                JSONObject object=x.getJSONObject(0);

                rname=object.getString("name");
                remail=object.getString("email");
                rroll=object.getString("roll");
                rcur=object.getString("sem");
                rbranch=object.getString("branch");
                rmob=object.getString("mob");
                rp_name=object.getString("p_name");
                rp_phone=object.getString("p_phone");
                rperm_add=object.getString("perm_add");
                img=object.getString("img");
                Toast.makeText(this,"welcome",Toast.LENGTH_SHORT);
          Intent intent=new Intent(getApplicationContext(),MainActivity.class);
          intent.putExtra("name",rname);
          intent.putExtra("email",remail);
          intent.putExtra("roll",rroll);
          intent.putExtra("current",rcur);
          intent.putExtra("branch",rbranch);
          intent.putExtra("mobile",rmob);
          intent.putExtra("parent",rp_name);
          intent.putExtra("pphone",rp_phone);
          intent.putExtra("address",rperm_add);
          intent.putExtra("image",img);

          startActivity(intent);

           //Toast.makeText(this,rname+" "+remail, Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            Toast.makeText(this,"wrong", Toast.LENGTH_LONG).show();

        }



    }else{


        Toast.makeText(this,"not working", Toast.LENGTH_LONG).show();

    }
    }

    @Override
    public void onClick(View view) {
        String user=name.getText().toString();
        String pass=password.getText().toString();
        String semester=sem.getText().toString();
        String rollno=roll.getText().toString();
        HashMap<String, String> postData = new HashMap<String, String>();
        postData.put("branch", t);
        postData.put("sem", semester );
        postData.put("roll",rollno);
        PostResponseAsyncTask loginTask =
                new PostResponseAsyncTask(this, postData, this);
        loginTask.execute("http://10.0.2.2/par/app_index.php");
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        t=adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(),t,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

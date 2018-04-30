package com.raat.rat;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.widget.Button;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle action;
    private WebView webView;
    private NavigationView navigationView;
    private Button b;
     String iname="";
   String iemail="";
   String imob="";
   String isem="";
  String ipar="";
    String  iroll="";
    String branch="";
    String pphone="";
    String address="";
    String json="";
    String img="";
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        if(Build.VERSION.SDK_INT>=21){

            Window window= this.getWindow();
          window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
          window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          window.setStatusBarColor(this.getResources().getColor(R.color.barkbrown));


        }
       /* webView=(WebView)findViewById(R.id.web);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://cucek.in/");*/


        drawerLayout = (DrawerLayout) findViewById(R.id.hh);
       action=new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
       drawerLayout.addDrawerListener(action);
       action.syncState();
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       navigationView=(NavigationView)findViewById(R.id.navigaion_view);

        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
       TextView name = (TextView)header.findViewById(R.id.username);
        TextView email = (TextView)header.findViewById(R.id.email);
        ImageView rage=(ImageView)header.findViewById(R.id.pro);


        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            iname=extra.getString("name");
            iemail=extra.getString("email");
            imob=extra.getString("mobile");
            isem=extra.getString("current");
            ipar=extra.getString("parent");
            iroll=extra.getString("roll");
            branch=extra.getString("branch");
            pphone=extra.getString("pphone");
            address=extra.getString("address");
             img=extra.getString("image");
        }
        name.setText(iname);
        email.setText(iemail);
        byte phpimg[]= Base64.decode(img.getBytes(),0);
        bmp= BitmapFactory.decodeByteArray(phpimg,0,phpimg.length);
        rage.setImageBitmap(bmp);
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               android.support.v4.app.FragmentTransaction fragmentTransaction;
              switch (item.getItemId()){

                  case R.id.dashboard:

                      Bundle bundle=new Bundle();
                      bundle.putString("name",iname);
                      bundle.putString("email",iemail);
                      bundle.putString("mobile",imob);
                      bundle.putString("sem",isem);
                      bundle.putString("parent",ipar);
                      bundle.putString("roll",iroll);
                      bundle.putString("branch",branch);
                      bundle.putString("pphone",pphone);
                      bundle.putString("address",address);


                       fragmentTransaction=getSupportFragmentManager().beginTransaction();
                      dash d=new dash();
                      d.setArguments(bundle);
                       fragmentTransaction.replace(R.id.frame,d);
                       fragmentTransaction.commit();
                       item.setChecked(true);
                       getSupportActionBar().setTitle("DASHBOARD");
                       drawerLayout.closeDrawers();
                      break;
                  case R.id.results:
                      Bundle bad1=new Bundle();
                      bad1.putString("sem",isem);
                      bad1.putString("branch",branch);
                      bad1.putString("roll",iroll);
                      resu r=new resu();
                      r.setArguments(bad1);

                      fragmentTransaction=getSupportFragmentManager().beginTransaction();
                      fragmentTransaction.replace(R.id.frame,r);
                      fragmentTransaction.commit();
                      item.setChecked(true);
                      getSupportActionBar().setTitle("RESULTS");
                      drawerLayout.closeDrawers();
                      break;
                  case R.id.attendence:



                      Bundle bad=new Bundle();
                      bad.putString("sem",isem);
                      bad.putString("branch",branch);
                      bad.putString("roll",iroll);


                      attend adt=new attend();
                       adt.setArguments(bad);
                      fragmentTransaction=getSupportFragmentManager().beginTransaction();
                      fragmentTransaction.replace(R.id.frame,adt);
                      fragmentTransaction.commit();
                      item.setChecked(true);
                      getSupportActionBar().setTitle("ATTENDENCE");
                      /*Intent i=new Intent(getApplicationContext(),attendence.class);
                      i.putExtra("sem",isem);
                      i.putExtra("roll",iroll);
                      i.putExtra("branch",branch);
                      startActivity(i);*/
                      drawerLayout.closeDrawers();
                      break;
                  case R.id.fee:
                      fragmentTransaction=getSupportFragmentManager().beginTransaction();
                      fragmentTransaction.replace(R.id.frame,new fees());
                      fragmentTransaction.commit();
                      item.setChecked(true);
                      getSupportActionBar().setTitle("FEE");
                      drawerLayout.closeDrawers();
                      break;
                  case R.id.setting:
                      fragmentTransaction=getSupportFragmentManager().beginTransaction();
                      fragmentTransaction.replace(R.id.frame,new set());
                      fragmentTransaction.commit();
                      item.setChecked(true);
                      getSupportActionBar().setTitle("SETTING");
                      drawerLayout.closeDrawers();
                      break;
                  case R.id.exit:
                      showd();
                      break;







              }



               return true;
           }
       });

       Bundle bundle=new Bundle();
       bundle.putString("name",iname);
        bundle.putString("email",iemail);
        bundle.putString("mobile",imob);
        bundle.putString("sem",isem);
        bundle.putString("parent",ipar);
        bundle.putString("roll",iroll);
        bundle.putString("branch",branch);
        bundle.putString("pphone",pphone);
        bundle.putString("address",address);
        dash d=new dash();
        d.setArguments(bundle);

    }

    public void fun(View view){

        getSupportActionBar().setTitle("HOME");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightpurple)));
        if(Build.VERSION.SDK_INT>=21){

            Window window= this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple));

        }
        android.support.v4.app.FragmentTransaction fragmentTransaction1;

        fragmentTransaction1=getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.frame,new home());
        fragmentTransaction1.commit();


    }
    public void fun2(View view){

        getSupportActionBar().setTitle("ALLERTS");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightred)));
        if(Build.VERSION.SDK_INT>=21){

            Window window= this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));

        }
        android.support.v4.app.FragmentTransaction fragmentTransaction2;
        fragmentTransaction2=getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.frame,new alert());
        fragmentTransaction2.commit();
    }
    public void fun3(View view){

        getSupportActionBar().setTitle("ACADEMICS");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightyellow)));
        if(Build.VERSION.SDK_INT>=21){

            Window window= this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.yellow));

        }
        android.support.v4.app.FragmentTransaction fragmentTransaction3;
        fragmentTransaction3=getSupportFragmentManager().beginTransaction();
        fragmentTransaction3.replace(R.id.frame,new academics());
        fragmentTransaction3.commit();

    }
    public void fun4(View view){

        getSupportActionBar().setTitle("ABOUT US");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        if(Build.VERSION.SDK_INT>=21){

            Window window= this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        android.support.v4.app.FragmentTransaction fragmentTransaction4;
        fragmentTransaction4=getSupportFragmentManager().beginTransaction();
        fragmentTransaction4.replace(R.id.frame,new about());
        fragmentTransaction4.commit();
    }
    public void first(){
        HashMap<String,String> h=new HashMap<>();
        h.put("roll",iroll);
        h.put("branch",branch);
        h.put("sem",isem);
        PostResponseAsyncTask postResponseAsyncTask=new PostResponseAsyncTask(this,h,this);
        postResponseAsyncTask.execute("http://10.0.2.2/par/app_attend.php");


    }
    public void showd(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
    builder.setTitle("CUCEK");
    builder.setMessage("Do you want to exit? ");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        builder.create().show();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(action.onOptionsItemSelected(item)){


            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void processFinish(String s) {
   Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        json=s;
    }
}

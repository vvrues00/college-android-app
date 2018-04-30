package com.raat.rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class spash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        final Thread thread=new Thread(){


            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent=new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();


    }
}

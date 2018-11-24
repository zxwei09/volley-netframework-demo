package com.zxwei.netframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zxwei.netframework.http.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Volley.sendJsonRequest();Used the volley framework to send a request
    }
}

package com.davidhernandezvilaltagmail.projecte1.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.davidhernandezvilaltagmail.projecte1.R;
import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
import com.davidhernandezvilaltagmail.projecte1.activities.Calculator;
import com.davidhernandezvilaltagmail.projecte1.activities.ForgottenPassword;
import com.davidhernandezvilaltagmail.projecte1.database.MyDataBaseHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Loginfail extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginfail);
        setTitle("Loginfail");
        b = (Button) findViewById(R.id.retrylogin);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Loginfail.super.onBackPressed();
            }
        });
    }
}
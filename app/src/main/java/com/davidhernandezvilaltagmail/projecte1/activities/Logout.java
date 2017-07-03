package com.davidhernandezvilaltagmail.projecte1.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.davidhernandezvilaltagmail.projecte1.Login;
import com.davidhernandezvilaltagmail.projecte1.R;
import com.davidhernandezvilaltagmail.projecte1.BaseActivity;

public class Logout extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        setTitle("Logout");
        setItemChecked();
        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("logged", false);
        editor.apply();
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }

    @Override
    protected int whatIsMyId() {
        return R.id.activity3;
    }
}

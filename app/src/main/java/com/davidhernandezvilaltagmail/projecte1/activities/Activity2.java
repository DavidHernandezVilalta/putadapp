package com.davidhernandezvilaltagmail.projecte1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.davidhernandezvilaltagmail.projecte1.R;
import com.davidhernandezvilaltagmail.projecte1.BaseActivity;

public class Activity2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        setTitle("Activity2");
        setItemChecked();
    }

    @Override
    protected int whatIsMyId() {
        return R.id.activity2;
    }
}

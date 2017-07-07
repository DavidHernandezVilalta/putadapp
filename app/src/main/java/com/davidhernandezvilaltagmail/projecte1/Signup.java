package com.davidhernandezvilaltagmail.projecte1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.davidhernandezvilaltagmail.projecte1.R;


public class Signup extends Login{
    EditText firstname, surname, email, username, password;
    Button signejar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Signup");
        firstname = (EditText) findViewById(R.id.firstnameedit);
        surname = (EditText) findViewById(R.id.surnameedit);
        email = (EditText) findViewById(R.id.emailedit);
        username = (EditText) findViewById(R.id.usuariedit);
        password = (EditText) findViewById(R.id.contrasenyaedit);
        signejar = (Button) findViewById(R.id.signupejar);

        signejar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (firstname == null||surname == null||username == null||password == null||email == null){
                    Toast.makeText(getApplicationContext(), "Falta algun camp per emplenar", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s = username.getText().toString();
                String s1 = password.getText().toString();
                if (myDataBaseHelper.queryUser(s).equals("0")){
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_LONG).show();
                    return;
                }
                long id = myDataBaseHelper.createRow(s, s1);
                Toast.makeText(v.getContext(),"Signed successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

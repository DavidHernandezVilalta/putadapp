package com.davidhernandezvilaltagmail.projecte1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.davidhernandezvilaltagmail.projecte1.activities.Loginfail;
import com.davidhernandezvilaltagmail.projecte1.database.MyDataBaseHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Login extends AppCompatActivity{
    TextView tv;
    Button login, signup;
    Set<String> users = new HashSet<String>();
    EditText username, password;
    Map<String, String> hm = new HashMap<String, String>();
    private MyDataBaseHelper myDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setTitle("Login");

        SharedPreferences settings = getSharedPreferences("SharedLogin", Context.MODE_PRIVATE);
        boolean islogged = settings.getBoolean("logged", false);
        if (!islogged) {
            username = (EditText) findViewById(R.id.username);

            myDataBaseHelper = MyDataBaseHelper.getInstance(this);
            tv = (TextView) findViewById(R.id.passwordforg);
            tv.setText(R.string.passforg);
            final Context context = this;
            tv.setOnClickListener(new View.OnClickListener(){
                public void onClick (View v) {
                    Intent i = new Intent(context, ForgottenPassword.class);
                    startActivity(i);
                }
            });
            password = (EditText) findViewById(R.id.password);
            login = (Button) findViewById(R.id.login);
            signup = (Button) findViewById(R.id.signup);
            signup.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (username == null||password == null){
                        Toast.makeText(getApplicationContext(), "Falta algun camp per emplenar", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String s = username.getText().toString();
                    String s1 = password.getText().toString();
                    long id = myDataBaseHelper.createRow(s, s1);
                    Toast.makeText(v.getContext(),"Has singejat in!!", Toast.LENGTH_SHORT).show();
                }
            });
            login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (username == null||password == null){
                        Toast.makeText(getApplicationContext(), "Falta algun camp per emplenar", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String s = username.getText().toString();
                    String s1 = password.getText().toString();
                    String idnt = myDataBaseHelper.queryRow(s, s1);
                    if (idnt.equals("0")){
                        //Toast.makeText(v.getContext(),"Estàs en la base de dades", Toast.LENGTH_LONG).show();
                        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("logged", true);
                        editor.apply();
                        Intent i = new Intent(getApplicationContext(), Calculator.class);
                        startActivity(i);
                    }
                    else if (myDataBaseHelper.queryUser(s).equals("0")) Toast.makeText(v.getContext(), "Incorrect password", Toast.LENGTH_LONG).show();
                    else {
                        Intent i = new Intent(getApplicationContext(), Loginfail.class);
                        startActivity(i);
                        //Toast.makeText(v.getContext(),"INTRUSO JOPUTAS CABRON NO ESTAS EN LA BASE DE DADES", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else {
            Intent i = new Intent(getApplicationContext(), Calculator.class);
            startActivity(i);
        }
    }
}

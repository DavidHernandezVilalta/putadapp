package com.davidhernandezvilaltagmail.projecte1.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
import com.davidhernandezvilaltagmail.projecte1.Login;
import com.davidhernandezvilaltagmail.projecte1.R;
import com.davidhernandezvilaltagmail.projecte1.database.MyDataBaseHelper;

import java.io.IOException;

import static com.davidhernandezvilaltagmail.projecte1.R.id.username;

public class Profile extends BaseActivity {
    ImageView perfil, avatar;
    TextView userprofile, usernameheader, bestscore;
    EditText userneim;
    Uri selectedImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        setItemChecked();
        perfil = (ImageView) findViewById(R.id.perfilemtpy);
        perfil.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent getImageAsContent = new Intent(Intent.ACTION_GET_CONTENT, null);
                getImageAsContent.setType("image/*");
                startActivityForResult(getImageAsContent, 1);
            }
        });
        userprofile = (TextView) findViewById(R.id.usernameprofile);
        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
        String userlogged = settings.getString("userlogged", "noname");
        String imatgeselec = settings.getString("uri", "null");
        userprofile.append(userlogged);
        View navHeaderView = navigationView.getHeaderView(0);
        usernameheader = (TextView) navHeaderView.findViewById(R.id.usernameheader);
        usernameheader.setText(userlogged);
        avatar = (ImageView) navHeaderView.findViewById(R.id.avatar);/*
        if (!imatgeselec.equals("null")) {
            selectedImage = Uri.parse(imatgeselec);
            try {
                perfil.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                avatar.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*//*
        bestscore = (TextView) findViewById(R.id.bestscore);
        if (myDataBaseHelper.queryUser(userlogged).equals("0")) {
            String record = myDataBaseHelper.queryRecord(userlogged);
            if (record.equals("infinity")) bestscore.setText("NEVER SCORED");
            else bestscore.setText(record);
        }*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Como en este caso los 3 intents hacen lo mismo, si el estado es correcto recogemos el resultado
        //Aún así comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if(resultCode == RESULT_OK){
            if(requestCode >= 1 && requestCode <= 3){
                data.getData();
                selectedImage = data.getData();

                SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("uri", selectedImage.toString());
                editor.apply();

                Log.v("PICK","Selected image uri" + selectedImage);
                try {
                    perfil.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Log.v("Result","Something happened");
        }
    }

    @Override
    protected int whatIsMyId() {
        return R.id.profile;
    }
}

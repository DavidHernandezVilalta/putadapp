package com.davidhernandezvilaltagmail.projecte1.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
import com.davidhernandezvilaltagmail.projecte1.R;

import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class Profile extends BaseActivity implements EasyPermissions.PermissionCallbacks  {
    ImageView perfil, avatar;
    TextView userprofile, nameheader, bestscore;
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
                //PermissionUtils.checkReadExternalStoragePermissions(activity,MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                requestCameraPermission();
                changeProfile();
            }
        });
        userprofile = (TextView) findViewById(R.id.usernameprofile);
        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
        String userlogged = settings.getString("userlogged", "noname");
        String imatgeselec = settings.getString("uri", null);
        userprofile.append(userlogged);
        View navHeaderView = navigationView.getHeaderView(0);
        nameheader = (TextView) navHeaderView.findViewById(R.id.nameheader);
        nameheader.setText(userlogged);
        avatar = (ImageView) navHeaderView.findViewById(R.id.avatar);/*
        if (imatgeselec != null) {
            selectedImage = Uri.parse(imatgeselec);
            try {
                perfil.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                avatar.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        bestscore = (TextView) findViewById(R.id.bestscore);
        if (myDataBaseHelper.queryUser(userlogged).equals("0")) {
            String record = myDataBaseHelper.queryRecord(userlogged);
            if (record.equals("infinity")) bestscore.setText("Memory Best Score: Never Scored");
            else bestscore.setText("Memory Best Score: " + record);
        }
    }

    private void requestCameraPermission() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
            changeProfile();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "FUCK", 0, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
        changeProfile();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...

    }

    private void changeProfile() {
        Intent pickAnImage = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickAnImage.setType("image/*");

        startActivityForResult(pickAnImage, 2);
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
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected int whatIsMyId() {
        return R.id.profile;
    }
}

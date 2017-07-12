package com.davidhernandezvilaltagmail.projecte1.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
import com.davidhernandezvilaltagmail.projecte1.R;
import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.io.IOException;

public class Mediaplayer extends BaseActivity {
    Button play, pause;
    MediaPlayer mp = new MediaPlayer();
    CoolImageFlipper cif;
    Drawable playimage;
    Button pressme;
    Drawable pauseimage;

    ImageView avatar;
    TextView nameheader;
    Uri selectedImage = null;

    boolean sona = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        setTitle("Media Player");
        setItemChecked();

        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
        String userlogged = settings.getString("userlogged", "noname");
        String imatgeselec = settings.getString("uri", null);
        View navHeaderView = navigationView.getHeaderView(0);
        nameheader = (TextView) navHeaderView.findViewById(R.id.nameheader);
        nameheader.setText(userlogged);
        avatar = (ImageView) navHeaderView.findViewById(R.id.avatar);
        if (imatgeselec != null) {
            selectedImage = Uri.parse(imatgeselec);
            try {
                avatar.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cif = new CoolImageFlipper(this);
        playimage = getResources().getDrawable(R.drawable.play);
        pauseimage = getResources().getDrawable(R.drawable.pause);
        mp = MediaPlayer.create(this, R.raw.d);
        pressme = (Button) findViewById(R.id.surprise);
        pressme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), surprise.class);
                startActivity(i);
            }
        });
    }
    public void foodClicked(View view) {
        if (sona){
            cif.flipImage(playimage, ((ImageView) view));
            mp.pause();
        }
        else{
            cif.flipImage(pauseimage, ((ImageView) view));
            mp.start();
        }

        sona = !sona;
    }
    @Override
    protected int whatIsMyId() {
        return R.id.mediaplayer;
    }


}

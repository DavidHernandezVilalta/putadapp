package com.davidhernandezvilaltagmail.projecte1.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
import com.davidhernandezvilaltagmail.projecte1.Login;
import com.davidhernandezvilaltagmail.projecte1.R;
import com.example.material.joanbarroso.flipper.CoolImageFlipper;

public class Mediaplayer extends BaseActivity {
    Button play, pause;
    MediaPlayer mp = new MediaPlayer();
    CoolImageFlipper cif;
    Drawable playimage;
    Drawable pauseimage;
    boolean sona = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        setTitle("Media Player");
        setItemChecked();
        cif = new CoolImageFlipper(this);
        playimage = getResources().getDrawable(R.drawable.play);
        pauseimage = getResources().getDrawable(R.drawable.pause);
        mp = MediaPlayer.create(this, R.raw.d);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
        SharedPreferences.Editor editor = settings.edit();
        switch (item.getItemId()) {
            case R.id.logoutoptions:
                finish();
                editor.putBoolean("logged", false);
                editor.apply();
                Intent i1 = new Intent(getApplicationContext(), Login.class);
                startActivity(i1);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int whatIsMyId() {
        return R.id.mediaplayer;
    }


}

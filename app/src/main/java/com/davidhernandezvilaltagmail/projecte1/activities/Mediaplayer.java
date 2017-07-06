package com.davidhernandezvilaltagmail.projecte1.activities;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
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
        setTitle("activity_media_player");
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
    @Override
    protected int whatIsMyId() {
        return R.id.mediaplayer;
    }


}

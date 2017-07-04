package com.davidhernandezvilaltagmail.projecte1.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.davidhernandezvilaltagmail.projecte1.R;
import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.Iterator;
import java.util.Set;

public class Memory extends AppCompatActivity {
    ImageView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16;
    boolean flipped2 = false;
    CoolImageFlipper cif = new CoolImageFlipper(this);
    public Drawable charmander, squirtle, bulbasaur, totodile, cyndaquil, mudkip, torchic, treecko;

    private boolean isinset(int n, Set<String> posicions) {
        for (Iterator<String> it = posicions.iterator(); it.hasNext(); ) {
            String s = it.next();
            if (s.equals(Integer.toString(n))) return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        mudkip = getResources().getDrawable(R.drawable.mudkip);
        torchic = getResources().getDrawable(R.drawable.torchic);
        treecko = getResources().getDrawable(R.drawable.treecko);
        squirtle = getResources().getDrawable(R.drawable.squirtle);
        totodile = getResources().getDrawable(R.drawable.totodile);
        bulbasaur = getResources().getDrawable(R.drawable.bulbasaur);
        cyndaquil = getResources().getDrawable(R.drawable.cyndaquil);
        charmander = getResources().getDrawable(R.drawable.charmander);
        Log.v("lolete", "vnidsvidsjvnsduvidn");
    }
    public void memclick1(View view){
        Log.v("llego", "");
        cif.flipImage(mudkip, ((ImageView) view));
    }
    protected int whatIsMyId() {
        return R.id.memory;
    }
}

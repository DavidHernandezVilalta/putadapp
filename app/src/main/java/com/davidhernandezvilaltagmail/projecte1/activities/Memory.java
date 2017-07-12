package com.davidhernandezvilaltagmail.projecte1.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
import com.davidhernandezvilaltagmail.projecte1.Login;
import com.davidhernandezvilaltagmail.projecte1.R;
import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Memory extends BaseActivity {
    Map<String, String> ispokflip = new HashMap<String, String>();
    boolean flipped2 = false;
    int count = 0;
    String pokemon = "nothing", pokemon2 = "nothing";
    int imageid = 0;
    TextView moviments;
    CoolImageFlipper cif = new CoolImageFlipper(this);
    public Drawable charmander, squirtle, bulbasaur, totodile, cyndaquil, mudkip, torchic, treecko, androidimage;
    Map<String, String> pkmnpos = new HashMap<String, String>();
    Set<String> posicions = new HashSet<String>();
    int numcorrectes = 0;

    ImageView avatar;
    TextView nameheader;
    Uri selectedImage = null;


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_memory,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.restartgame:
                finish();
                Intent i = new Intent(getApplicationContext(), Memory.class);
                startActivity(i);
                return true;
            case R.id.logoutoptions:
                finish();
                SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("logged", false);
                editor.apply();
                Intent i1 = new Intent(getApplicationContext(), Login.class);
                startActivity(i1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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
        setTitle("Memory");
        setItemChecked();
        new Thread(new Runnable() {
            @Override
            public void run() {/*
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
                }*/

                ispokflip.put("mudkip", "no");
                ispokflip.put("torchic", "no");
                ispokflip.put("treecko", "no");
                ispokflip.put("cyndaquil", "no");
                ispokflip.put("totodile", "no");
                ispokflip.put("charmander", "no");
                ispokflip.put("squirtle", "no");
                ispokflip.put("bulbasaur", "no");

                moviments = (TextView) findViewById(R.id.movis);
                mudkip = getResources().getDrawable(R.drawable.mudkip);
                torchic = getResources().getDrawable(R.drawable.torchic);
                treecko = getResources().getDrawable(R.drawable.treecko);
                squirtle = getResources().getDrawable(R.drawable.squirtle);
                totodile = getResources().getDrawable(R.drawable.totodile);
                bulbasaur = getResources().getDrawable(R.drawable.bulbasaur);
                cyndaquil = getResources().getDrawable(R.drawable.cyndaquil);
                charmander = getResources().getDrawable(R.drawable.charmander);
                androidimage = getResources().getDrawable(R.drawable.androidmemory2);
                for (int i = 0; i < 2; ++i) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    while (isinset(randomNum, posicions))
                        randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    posicions.add(Integer.toString(randomNum));
                    if (i == 0) pkmnpos.put("mudkip", Integer.toString(randomNum));
                    else pkmnpos.put("mudkip2", Integer.toString(randomNum));
                }
                for (int i = 0; i < 2; ++i) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    while (isinset(randomNum, posicions))
                        randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    posicions.add(Integer.toString(randomNum));
                    if (i == 0) pkmnpos.put("charmander", Integer.toString(randomNum));
                    else pkmnpos.put("charmander2", Integer.toString(randomNum));
                }
                for (int i = 0; i < 2; ++i) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    while (isinset(randomNum, posicions))
                        randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    posicions.add(Integer.toString(randomNum));
                    if (i == 0) pkmnpos.put("squirtle", Integer.toString(randomNum));
                    else pkmnpos.put("squirtle2", Integer.toString(randomNum));
                }
                for (int i = 0; i < 2; ++i) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    while (isinset(randomNum, posicions))
                        randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    posicions.add(Integer.toString(randomNum));
                   if (i == 0) pkmnpos.put("bulbasaur", Integer.toString(randomNum));
                    else pkmnpos.put("bulbasaur2", Integer.toString(randomNum));
                }
                for (int i = 0; i < 2; ++i) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    while (isinset(randomNum, posicions))
                        randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    posicions.add(Integer.toString(randomNum));
                    if (i == 0) pkmnpos.put("cyndaquil", Integer.toString(randomNum));
                    else pkmnpos.put("cyndaquil2", Integer.toString(randomNum));
                }
                for (int i = 0; i < 2; ++i) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    while (isinset(randomNum, posicions))
                        randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    posicions.add(Integer.toString(randomNum));
                   if (i == 0) pkmnpos.put("totodile", Integer.toString(randomNum));
                    else pkmnpos.put("totodile2", Integer.toString(randomNum));
                }
                for (int i = 0; i < 2; ++i) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    while (isinset(randomNum, posicions))
                        randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    posicions.add(Integer.toString(randomNum));
                    if (i == 0) pkmnpos.put("torchic", Integer.toString(randomNum));
                    else pkmnpos.put("torchic2", Integer.toString(randomNum));
                }
                for (int i = 0; i < 2; ++i) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    while (isinset(randomNum, posicions))
                        randomNum = ThreadLocalRandom.current().nextInt(1, 17);
                    posicions.add(Integer.toString(randomNum));
                    if (i == 0) pkmnpos.put("treecko", Integer.toString(randomNum));
                    else pkmnpos.put("treecko2", Integer.toString(randomNum));
                }
            }
        }).start();
    }

    private Drawable triarpokemon(String s){
        Iterator it = pkmnpos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue().equals(s)) {
                switch (pair.getKey().toString()) {
                    case "mudkip":
                        if (count%2 == 0||count == 0) pokemon = "mudkip";
                        else pokemon2 = "mudkip";
                        return mudkip;
                    case "torchic":
                        if (count%2 == 0||count == 0) pokemon = "torchic";
                        else pokemon2 = "torchic";
                        return torchic;
                    case "treecko":
                        if (count%2 == 0||count == 0) pokemon = "treecko";
                        else pokemon2 = "treecko";
                        return treecko;
                    case "charmander":
                        if (count%2 == 0||count == 0) pokemon = "charmander";
                        else pokemon2 = "charmander";
                        return charmander;
                    case "squirtle":
                        if (count%2 == 0||count == 0) pokemon = "squirtle";
                        else pokemon2 = "squirtle";
                        return squirtle;
                    case "cyndaquil":
                        if (count%2 == 0||count == 0) pokemon = "cyndaquil";
                        else pokemon2 = "cyndaquil";
                        return cyndaquil;
                    case "totodile":
                        if (count%2 == 0||count == 0) pokemon = "totodile";
                        else pokemon2 = "totodile";
                        return totodile;
                    case "bulbasaur":
                        if (count%2 == 0||count == 0) pokemon = "bulbasaur";
                        else pokemon2 = "bulbasaur";
                        return bulbasaur;
                    case "mudkip2":
                        if (count%2 == 0||count == 0) pokemon = "mudkip";
                        else pokemon2 = "mudkip";
                        return mudkip;
                    case "torchic2":
                        if (count%2 == 0||count == 0) pokemon = "torchic";
                        else pokemon2 = "torchic";
                        return torchic;
                    case "treecko2":
                        if (count%2 == 0||count == 0) pokemon = "treecko";
                        else pokemon2 = "treecko";
                        return treecko;
                    case "charmander2":
                        if (count%2 == 0||count == 0) pokemon = "charmander";
                        else pokemon2 = "charmander";
                        return charmander;
                    case "squirtle2":
                        if (count%2 == 0||count == 0) pokemon = "squirtle";
                        else pokemon2 = "squirtle";
                        return squirtle;
                    case "cyndaquil2":
                        if (count%2 == 0||count == 0) pokemon = "cyndaquil";
                        else pokemon2 = "cyndaquil";
                        return cyndaquil;
                    case "totodile2":
                        if (count%2 == 0||count == 0) pokemon = "totodile";
                        else pokemon2 = "totodile";
                        return totodile;
                    case "bulbasaur2":
                        if (count%2 == 0||count == 0) pokemon = "bulbasaur";
                        else pokemon2 = "bulbasaur";
                        return bulbasaur;
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    private boolean edittext = false;


    public void memclick1(View view){
        if (imageid != view.getId()&&!flipped2){
            Drawable aux = null;
            switch (view.getId()){
                case R.id.mem1:
                    aux = triarpokemon("1");
                    break;
                case R.id.mem2:
                    aux = triarpokemon("2");
                    break;
                case R.id.mem3:
                    aux = triarpokemon("3");
                    break;
                case R.id.mem4:
                    aux = triarpokemon("4");
                    break;
                case R.id.mem5:
                    aux = triarpokemon("5");
                    break;
                case R.id.mem6:
                    aux = triarpokemon("6");
                    break;
                case R.id.mem7:
                    aux = triarpokemon("7");
                    break;
                case R.id.mem8:
                    aux = triarpokemon("8");
                    break;
                case R.id.mem9:
                    aux = triarpokemon("9");
                    break;
                case R.id.mem10:
                    aux = triarpokemon("10");
                    break;
                case R.id.mem11:
                    aux = triarpokemon("11");
                    break;
                case R.id.mem12:
                    aux = triarpokemon("12");
                    break;
                case R.id.mem13:
                    aux = triarpokemon("13");
                    break;
                case R.id.mem14:
                    aux = triarpokemon("14");
                    break;
                case R.id.mem15:
                    aux = triarpokemon("15");
                    break;
                case R.id.mem16:
                    aux = triarpokemon("16");
                    break;
                default:
                    break;
            }
            Iterator it = ispokflip.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (
                        (
                                (pokemon != null&&pair.getKey().toString().equals(pokemon))
                                ||(pokemon2 != null&&pair.getKey().toString().equals(pokemon2))
                        )
                        &&pair.getValue().toString().equals("si")
                    ) return;
            }


            cif.flipImage(aux, ((ImageView) view));
            count = count +1;

            if (count%2 == 0) {
                flipped2 = true;
                if (!edittext) {
                    edittext = true;
                    moviments.setText("1");
                }
                else{
                    int moves = Integer.parseInt(moviments.getText().toString());
                    moves = moves + 1;
                    moviments.setText(Integer.toString(moves));
                }
                if (!pokemon.equals(pokemon2)&&pokemon2!=null) {
                    final View vistaux = view;
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ImageView im = (ImageView) findViewById(imageid);
                            cif.flipImage(androidimage, ((ImageView) im));
                            cif.flipImage(androidimage, ((ImageView) vistaux));
                            imageid = 0;
                            flipped2 = false;
                        }
                    }, 2000);
                }
                else {
                    flipped2 = false;
                    numcorrectes = numcorrectes + 1;
                    Iterator it2 = ispokflip.entrySet().iterator();
                    while (it2.hasNext()) {
                        Map.Entry pair = (Map.Entry) it2.next();
                        if (pair.getKey().toString().equals(pokemon)){
                            ispokflip.put(pokemon, "si");
                            pokemon = null;
                            pokemon2 = null;
                        }
                    }
                    if (numcorrectes == 8) {
                        final Context context = this;

                        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
                        String userlogged = settings.getString("userlogged", "noname");
                        String moves = moviments.getText().toString();
                        if (myDataBaseHelper.queryUser(userlogged).equals("0")) {
                            String record = myDataBaseHelper.queryRecord(userlogged);
                            if (record.equals("infinity")) myDataBaseHelper.updateRecord(moves, userlogged);
                            else if (Integer.parseInt(record) > Integer.parseInt(moves)) myDataBaseHelper.updateRecord(moves, userlogged);
                        }

                        myDataBaseHelper.updateNotification("YOU FINISHED THE GAME!!", userlogged);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("YOU FINISHED THE GAME!!");
                        alertDialogBuilder
                                .setMessage("PLAY AGAIN?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, close
                                        // current activity
                                        finish();
                                        Intent i = new Intent(getApplicationContext(), Memory.class);
                                        startActivity(i);
                                    }
                                })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        finish();
                                        Intent i = new Intent(getApplicationContext(), Profile.class);
                                        startActivity(i);
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
            }
            else {
                imageid = view.getId();
            }
        }
    }


    protected int whatIsMyId() {
        return R.id.memory;
    }
}

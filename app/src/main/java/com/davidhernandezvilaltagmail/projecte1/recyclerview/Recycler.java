package com.davidhernandezvilaltagmail.projecte1.recyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
import com.davidhernandezvilaltagmail.projecte1.Login;
import com.davidhernandezvilaltagmail.projecte1.R;
import com.davidhernandezvilaltagmail.projecte1.activities.Profile;
import com.davidhernandezvilaltagmail.projecte1.database.MyDataBaseHelper;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Recycler extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    MyDataBaseHelper myDataBaseHelper;
    List<String> noms, records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setTitle("Ranking");
        //setItemChecked();
        //findViewById del layout activity_main
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        //LinearLayoutManager necesita el contexto de la Activity.
        //El LayoutManager se encarga de posicionar los items dentro del recyclerview
        //Y de definir la politica de reciclaje de los items no visibles.
        mLinearLayout = new LinearLayoutManager(this);

        //Asignamos el LinearLayoutManager al recycler:
        mRecyclerView.setLayoutManager(mLinearLayout);


        //El adapter se encarga de  adaptar un objeto definido en el c�digo a una vista en xml
        //seg�n la estructura definida.
        //Asignamos nuestro custom Adapter
        myDataBaseHelper = MyDataBaseHelper.getInstance(this);
        HashMap<String, String> all = myDataBaseHelper.queryAll();
        mRecyclerView.setAdapter(new MyCustomAdapter(all));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_ranking, menu);
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
                return true;
            case R.id.restartranking:
                myDataBaseHelper.deleteRecords();
                HashMap<String, String> all = new HashMap<>();
                mRecyclerView.setAdapter(new MyCustomAdapter(all));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(getApplicationContext(), Profile.class);
        startActivity(i);
    }

    protected int whatIsMyId() {
    return R.id.recycler;
}
}

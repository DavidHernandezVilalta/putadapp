package com.davidhernandezvilaltagmail.projecte1.recyclerview;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.davidhernandezvilaltagmail.projecte1.BaseActivity;
import com.davidhernandezvilaltagmail.projecte1.R;
import com.davidhernandezvilaltagmail.projecte1.activities.Profile;
import com.davidhernandezvilaltagmail.projecte1.database.MyDataBaseHelper;

import java.util.List;

public class Recycler extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    MyDataBaseHelper myDataBaseHelper;
    List<String> noms, records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

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
        List<String> noms = null, records = null;
        Log.v("llego", "ndosnvo");
        noms = myDataBaseHelper.queryAllUsers();
        records = myDataBaseHelper.queryAllRecords();
        mRecyclerView.setAdapter(new MyCustomAdapter(noms, records));
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    protected int whatIsMyId() {
    return R.id.recycler;
}
}

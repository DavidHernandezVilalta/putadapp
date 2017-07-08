package com.davidhernandezvilaltagmail.projecte1.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.davidhernandezvilaltagmail.projecte1.Login;
import com.davidhernandezvilaltagmail.projecte1.R;
import com.davidhernandezvilaltagmail.projecte1.BaseActivity;

import static android.R.attr.duration;

public class Calculator extends BaseActivity implements View.OnClickListener {
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19;
    TextView ops, res;
    protected Boolean hihazero = false, hihacoma = false, hihaop = false, toastejo = true;
    protected String num1 = null, num2 = null, simbol = null, ans = null;
    int itemaux = 0;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
        itemaux = settings.getInt("itemaux", 0);
        getMenuInflater().inflate(R.menu.base,menu);
        if (itemaux != 0) {
            menu.findItem(itemaux).setChecked(true);
        }
        return true;
    }
    final Context context = this;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
        SharedPreferences.Editor editor = settings.edit();
        switch (item.getItemId()) {
            case R.id.trucar:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:689073760"));
                startActivity(intent);
                return true;
            case R.id.chrome:
                Uri uri = Uri.parse("http://www.pornhub.com/");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent1);
                return true;
            case R.id.toastejar:
                item.setChecked(true);
                editor.putBoolean("toastejo", true);
                editor.putInt("itemaux", itemaux);
                editor.apply();
                itemaux = item.getItemId();
                toastejo = true;
                return true;
            case R.id.estatejar:
                itemaux = item.getItemId();
                editor.putInt("itemaux", itemaux);
                editor.putBoolean("toastejo", false);
                editor.apply();
                item.setChecked(true);
                Log.v("eieiei.set", Integer.toString(itemaux));
                toastejo = false;
                return true;
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
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        TextView t = (TextView) findViewById(R.id.operacions);
        TextView t1 = (TextView) findViewById(R.id.resultats);
        t.setText(savedInstanceState.getString("ops"));
        t1.setText(savedInstanceState.getString("result"));
        num1 = savedInstanceState.getString("numero1");
        num2 = savedInstanceState.getString("numero2");
        hihacoma = savedInstanceState.getBoolean("hihacoma");
        hihazero = savedInstanceState.getBoolean("hihazero");
        hihaop = savedInstanceState.getBoolean("hihaop");
        simbol = savedInstanceState.getString("simbol");
        toastejo = savedInstanceState.getBoolean("toastejo");
        itemaux = savedInstanceState.getInt("item");
    }
    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        TextView t = (TextView) findViewById(R.id.operacions);
        TextView t1 = (TextView) findViewById(R.id.resultats);
        outstate.putString("ops", t.getText().toString());
        outstate.putString("result", t1.getText().toString());
        outstate.putString("numero1", num1);
        outstate.putString("numero2", num2);
        outstate.putString("simbol", simbol);
        outstate.putBoolean("hihazero", hihazero);
        outstate.putBoolean("hihacoma", hihacoma);
        outstate.putBoolean("hihaop", hihaop);
        outstate.putBoolean("toastejo", toastejo);
        outstate.putInt("item", itemaux);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
        toastejo = settings.getBoolean("toastejo", false);
        if (rotation == 0) setContentView(R.layout.activity_calculator);
        else setContentView(R.layout.activity_calculator_landscape);
        setTitle("Calculator");
        setItemChecked();
        b0 = (Button) findViewById(R.id.num0);
        b1 = (Button) findViewById(R.id.num1);
        b2 = (Button) findViewById(R.id.num2);
        b3 = (Button) findViewById(R.id.num3);
        b4 = (Button) findViewById(R.id.num4);
        b5 = (Button) findViewById(R.id.num5);
        b6 = (Button) findViewById(R.id.num6);
        b7 = (Button) findViewById(R.id.num7);
        b8 = (Button) findViewById(R.id.num8);
        b9 = (Button) findViewById(R.id.num9);
        b10 = (Button) findViewById(R.id.borratot);
        b11 = (Button) findViewById(R.id.borrar);
        b12 = (Button) findViewById(R.id.mult);
        b13 = (Button) findViewById(R.id.div);
        b14 = (Button) findViewById(R.id.sum);
        b15 = (Button) findViewById(R.id.rest);
        b16 = (Button) findViewById(R.id.igual);
        b17 = (Button) findViewById(R.id.xquadrat);
        b18 = (Button) findViewById(R.id.coma);
        b19 = (Button) findViewById(R.id.ans);
        ops = (TextView) findViewById(R.id.operacions);
        res = (TextView) findViewById(R.id.resultats);
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b14.setOnClickListener(this);
        b15.setOnClickListener(this);
        b16.setOnClickListener(this);
        b17.setOnClickListener(this);
        b18.setOnClickListener(this);
        b19.setOnClickListener(this);

    }

    @Override
    protected int whatIsMyId() {
        return R.id.calculator;
    }

    private void guardarnumero(String s) {
        if (s.equals("num1")) num1 = ops.getText().toString();
        else num2 = ops.getText().toString();
    }

    private static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }

    private void opera(String s) {
        hihacoma = false;
        if (!hihaop&&!s.equals("=")) {
            if (string_valid()) {
                guardarnumero("num1");
                hihaop = true;
                ops.setText(s);
                simbol = s;
            }
        }
        else {
            if (string_valid()) {
                if (s.equals("-")) ops.setText("-");
                else if (!ops.getText().toString().equals(simbol)) {
                Log.v("MainActivity", "entroooooooooo");
                    guardarnumero("num2");
                    switch (simbol) {
                        case "+":
                            Log.v("MainActivity", num1);
                            num1 = Double.toString(Double.parseDouble(num1) + Double.parseDouble(num2));
                            break;
                        case "-":
                            num1 = Double.toString(Double.parseDouble(num1) - Double.parseDouble(num2));
                            break;
                        case "*":
                            num1 = Double.toString(Double.parseDouble(num1) * Double.parseDouble(num2));
                            break;
                        case "/":
                            num1 = Double.toString(Double.parseDouble(num1) / Double.parseDouble(num2));
                            break;
                        case "^2":
                            Log.v("MainActivity", "entro");
                            num1 = Double.toString(Double.parseDouble(num2) * Double.parseDouble(num2));
                            break;
                    }
                    if (num1.equals("Infinity")) {
                        res.setText("--> ∞");
                        if (toastejo){
                            Toast.makeText(getApplicationContext(), "Ets de magisteri o que?", Toast.LENGTH_LONG).show();
                        }
                        else {
                            //Instanciamos Notification Manager
                            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
                            // un Builder/contructor.
                            NotificationCompat.Builder mBuilder =
                                    (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                            .setSmallIcon(R.drawable.ic_calculator)
                                            .setContentTitle("You stupid")
                                            .setContentText("Why dividing by 0?");
                            // Creamos un intent explicito, para abrir la app desde nuestra notificación
                            Intent resultIntent = new Intent(getApplicationContext(), Calculator.class);
                            //Generamos la backstack y le añadimos el intent
                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                            stackBuilder.addParentStack(Calculator.class);
                            stackBuilder.addNextIntent(resultIntent);
                            //Obtenemos el pending intent
                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );

                            mBuilder.setContentIntent(resultPendingIntent);

                            // mId es un identificador que nos permitirá actualizar la notificación
                            // más adelante
                            mNotificationManager.notify(123, mBuilder.build());
                        }
                    }
                    else{
                        double resultat = Double.parseDouble(num1);
                        int enter = (int) Double.parseDouble(num1);
                        if (resultat-enter != 0){
                            resultat = redondearDecimales(resultat, 6);
                            num1 = Double.toString(resultat);
                        }
                        else {
                            num1 = Integer.toString(enter);
                        }
                        res.setText(num1);
                    }
                    if (!s.equals("="))ops.setText(s);
                    else {
                        ops.setText("");
                        ans = num1;
                    }
                    simbol = s;
                }
            }
        }
    }

    private Boolean string_valid() {
        String str = ops.getText().toString();
        if (str != null && str.length() > 0) return true;
        else return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.num0:
                if (!ops.getText().toString().equals("0")&&!ops.getText().toString().equals(null)) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("0");
                    else ops.append("0");
                    if (ops.getText().toString().equals("0")) hihazero = true;
                }
                break;
            case R.id.num1:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("1");
                    else ops.append("1");
                    hihazero = false;
                }
                else ops.append("1");
                break;
            case R.id.num2:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("2");
                    else ops.append("2");
                    hihazero = false;
                }
                else ops.append("2");
                break;
            case R.id.num3:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("3");
                    else ops.append("3");
                    hihazero = false;
                }
                else ops.append("3");
                break;
            case R.id.num4:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("4");
                    else ops.append("4");
                    hihazero = false;
                }
                else ops.append("4");
                break;
            case R.id.num5:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("5");
                    else ops.append("5");
                    hihazero = false;
                }
                else ops.append("5");
                break;
            case R.id.num6:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("6");
                    else ops.append("6");
                    hihazero = false;
                }
                else ops.append("6");
                break;
            case R.id.num7:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("7");
                    else ops.append("7");
                    hihazero = false;
                }
                else ops.append("7");
                break;
            case R.id.num8:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("8");
                    else ops.append("8");
                    hihazero = false;
                }
                else ops.append("8");
                break;
            case R.id.num9:
                if (hihazero||hihaop) {
                    if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText("9");
                    else ops.append("9");
                    hihazero = false;
                }
                else ops.append("9");
                break;

            case R.id.borratot:
                ops.setText("");
                hihacoma = false;
                hihaop = false;
                hihazero = false;
                num1 = null;
                res.setText("");
                num2 = null;
                break;
            case R.id.coma:
                if (!hihacoma) {
                    ops.append(".");
                    hihacoma = true;
                }
                break;
            case R.id.borrar:
                String str = ops.getText().toString();
                if (string_valid()) {
                    if (str.substring(str.length()-1, str.length()).equals(",")) hihacoma = false;
                    str = str.substring(0, str.length() -1);
                    ops.setText(str);
                }
                break;
            case R.id.sum:
                opera("+");
                break;
            case R.id.rest:
                opera("-");
                break;
            case R.id.mult:
                opera("*");
                break;
            case R.id.div:
                opera("/");
                break;
            case R.id.xquadrat:
                hihaop = true;
                simbol = "^2";
                opera("^2");
                break;
            case R.id.igual:
                opera("=");
                hihaop = false;
                break;
            case R.id.ans:
                if (ans != null&&!ans.isEmpty()){
                    if (hihazero||hihaop) {
                        if (ops.getText().toString().equals(null)||ops.getText().toString().equals(simbol)) ops.setText(ans);
                        else ops.append(ans);
                        hihazero = false;
                    }
                    else ops.append(ans);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}

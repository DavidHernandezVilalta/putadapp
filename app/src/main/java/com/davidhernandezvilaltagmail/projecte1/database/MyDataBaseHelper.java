package com.davidhernandezvilaltagmail.projecte1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.davidhernandezvilaltagmail.projecte1.activities.MyDataBaseContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    /*
    * DEFINITION:
    * This class is the one which talks with the DB.
    */
    private final String TAG = "MyDataBaseHelper";

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "MyDataBase.db";


    private static final String SQL_CREATE_TABLE1 =
            "CREATE TABLE " + MyDataBaseContract.Table1.TABLE_NAME + " (" +
                    MyDataBaseContract.Table1._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MyDataBaseContract.Table1.USER + " TEXT UNIQUE," +
                    MyDataBaseContract.Table1.PASSWORD + " TEXT," +
                    MyDataBaseContract.Table1.RECORD + " TEXT," +
                    MyDataBaseContract.Table1.LASTNOT + " TEXT)";

    private static final String SQL_DELETE_TABLE1 =
            "DROP TABLE IF EXISTS " + MyDataBaseContract.Table1.TABLE_NAME;

    private static MyDataBaseHelper instance;
    private static SQLiteDatabase writable;
    private static SQLiteDatabase readable;

    //We will use this method instead the default constructor to get a reference.
    //With this we will use all the time the same reference.
    public static MyDataBaseHelper getInstance(Context c){
        if(instance == null){
            instance = new MyDataBaseHelper(c);
            if (writable == null) writable = instance.getWritableDatabase();
            if (readable == null) readable = instance.getReadableDatabase();
        }
        return instance;
    }

    //With this, all must use getInstance(Context) to use this class
    private MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //We execute here the SQL sentence to create the DB
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //This method will be executed when the system detects that DATABASE_VERSION has been upgraded
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE1);
        onCreate(sqLiteDatabase);
    }

    public long createRow(String s, String s1) {
        ContentValues values = new ContentValues();
        values.put(MyDataBaseContract.Table1.USER,s);
        values.put(MyDataBaseContract.Table1.PASSWORD, s1);
        values.put(MyDataBaseContract.Table1.RECORD, "infinity");
        values.put(MyDataBaseContract.Table1.LASTNOT, "ANY");
        long newId = writable.insert(MyDataBaseContract.Table1.TABLE_NAME,null,values);
        return newId;
    }

    public int updateRecord(String newrrecord, String user) {
        ContentValues values = new ContentValues();
        values.put(MyDataBaseContract.Table1.RECORD, newrrecord);
        int rows_afected = writable.update(MyDataBaseContract.Table1.TABLE_NAME,    //Table name
                values,                                                             //New value for columns
                MyDataBaseContract.Table1.USER + " LIKE ? ",                 //Selection args
                new String[] {user});                                                  //Selection values

        return rows_afected;
    }

    public int updateNotification(String not, String user) {
        ContentValues values = new ContentValues();
        values.put(MyDataBaseContract.Table1.LASTNOT, not);
        int rows_afected = writable.update(MyDataBaseContract.Table1.TABLE_NAME,    //Table name
                values,                                                             //New value for columns
                MyDataBaseContract.Table1.USER + " LIKE ? ",                 //Selection args
                new String[] {user});                                                  //Selection values

        return rows_afected;
    }

    public int deleteRow(String s) {
        int afected = readable.delete(MyDataBaseContract.Table1.USER,         //Table name
                MyDataBaseContract.Table1.PASSWORD + " LIKE ? ",                 //Selection args
                new String[] {s});                                                  //Selection values

        return afected;
    }

    public void deleteRecords() {
        ContentValues values = new ContentValues();
        values.put(MyDataBaseContract.Table1.RECORD, "infinity");
        int rows_afected = writable.update(MyDataBaseContract.Table1.TABLE_NAME,    //Table name
                values,                                                             //New value for columns
                null,                 //Selection args
                null);                                                  //Selection values

    }

    public String queryRow(String s, String s1) {
        Cursor c;
        c = readable.query(MyDataBaseContract.Table1.TABLE_NAME,    //Table name
                new String[] {MyDataBaseContract.Table1.USER},       //Columns we select
                MyDataBaseContract.Table1.USER + " = ? AND " + MyDataBaseContract.Table1.PASSWORD + " = ? ",             //Columns for the WHERE clause
                new String[] {s, s1},                                   //Values for the WHERE clause
                null,                                               //Group By
                null,                                               //Having
                null);                                              //Sort

        String returnValue = "Not found";

        if (c.moveToFirst()) {
            do {
                //We go here if the cursor is not empty
                long l = c.getLong(c.getColumnIndex(MyDataBaseContract.Table1.USER));
                returnValue = String.valueOf(l);
            } while (c.moveToNext());
        }

        //Always close the cursor after you finished using it
        c.close();

        return returnValue;
    }

    public String queryUser(String s) {
        Cursor c;
        c = readable.query(MyDataBaseContract.Table1.TABLE_NAME,    //Table name
                new String[] {MyDataBaseContract.Table1.USER},       //Columns we select
                MyDataBaseContract.Table1.USER + " = ? ",             //Columns for the WHERE clause
                new String[] {s},                                   //Values for the WHERE clause
                null,                                               //Group By
                null,                                               //Having
                null);                                              //Sort

        String returnValue = "Not found";

        if (c.moveToFirst()) {
            do {
                //We go here if the cursor is not empty
                long l = c.getLong(c.getColumnIndex(MyDataBaseContract.Table1.USER));
                returnValue = String.valueOf(l);
            } while (c.moveToNext());
        }

        //Always close the cursor after you finished using it
        c.close();

        return returnValue;
    }


    public String queryRecord(String user) {
        Cursor c;
        c = readable.query(MyDataBaseContract.Table1.TABLE_NAME,    //Table name
                new String[] {MyDataBaseContract.Table1.RECORD},       //Columns we select
                MyDataBaseContract.Table1.USER + " = ? ",             //Columns for the WHERE clause
                new String[] {user},                                   //Values for the WHERE clause
                null,                                               //Group By
                null,                                               //Having
                null);                                              //Sort

        String record = "null";
        if (c.moveToFirst()) {
            do {
                //We go here if the cursor is not empty
                record = c.getString(c.getColumnIndex(MyDataBaseContract.Table1.RECORD));
            } while (c.moveToNext());
        }

        //Always close the cursor after you finished using it
        c.close();
        return record;
    }


    public HashMap<String, String> queryAll() {
        Cursor c;
        c = readable.query(MyDataBaseContract.Table1.TABLE_NAME,    //Table name
                new String[] {MyDataBaseContract.Table1.RECORD, MyDataBaseContract.Table1.USER},       //Columns we select
                null,             //Columns for the WHERE clause
                null,                                   //Values for the WHERE clause
                null,                                               //Group By
                null,                                               //Having
                null);                                              //Sort
        HashMap<String, String> all = new HashMap<>();
        if (c.moveToFirst()) {
            do {
                //We go here if the cursor is not empty
                String user = c.getString(c.getColumnIndex(MyDataBaseContract.Table1.USER));
                Log.v("Usuari", user);
                String record = c.getString(c.getColumnIndex(MyDataBaseContract.Table1.RECORD));
                all.put(user, record);
            } while (c.moveToNext());
        }

        //Always close the cursor after you finished using it
        c.close();
        return all;
    }

    public String queryNotification(String user) {
        Cursor c;
        c = readable.query(MyDataBaseContract.Table1.TABLE_NAME,    //Table name
                new String[] {MyDataBaseContract.Table1.LASTNOT},       //Columns we select
                MyDataBaseContract.Table1.USER + " = ? ",             //Columns for the WHERE clause
                new String[] {user},                                   //Values for the WHERE clause
                null,                                               //Group By
                null,                                               //Having
                null);                                              //Sort

        String lastnot = "null";
        if (c.moveToFirst()) {
            do {
                //We go here if the cursor is not empty
                lastnot = c.getString(c.getColumnIndex(MyDataBaseContract.Table1.LASTNOT));
            } while (c.moveToNext());
        }

        //Always close the cursor after you finished using it
        c.close();
        return lastnot;
    }

    @Override
    public synchronized void close() {
        super.close();
        //Always close the SQLiteDatabase
        writable.close();
        readable.close();
        Log.v(TAG,"close()");
    }
}

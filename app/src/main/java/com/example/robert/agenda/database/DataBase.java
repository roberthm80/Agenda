package com.example.robert.agenda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Robert Souza Duarte on 8/11/17.
 */

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context){
        super(context,"DB_CONTATOS",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCriaContato());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

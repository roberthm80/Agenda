package com.example.robert.agenda.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * Created by Robert Souza Duarte on 8/11/17.
 */

public class CRUD {
    private SQLiteDatabase conn;
    public CRUD(SQLiteDatabase conn){
        this.conn=conn;
    }
    public void AddContact(Contatos contatos){ //add item on the list
        ContentValues values = new ContentValues();
        values.put("NOME",contatos.getNome());
        values.put("NUMERO",contatos.getNumero());
        conn.insertOrThrow("TB_CONTATOS",null,values);
    }
    public void UpdateContact(Contatos contatos,int idAtual){ //update item selected information
        ContentValues values = new ContentValues();
        values.put("NOME",contatos.getNome());
        values.put("NUMERO",contatos.getNumero());
        String[] strID = new String[]{String.valueOf(idAtual)};

        conn.update("TB_CONTATOS", values, "ID=?",strID);
    }
    public void DeleteContact(int id){ // delete item from the list

        conn.delete("TB_CONTATOS","ID=?",new String[]{String.valueOf(id)});
    }
    public ArrayAdapter<Contatos> ListContacts(Context context){
        ArrayAdapter<Contatos>adpContatos = new ArrayAdapter<Contatos>(context,android.R.layout.simple_list_item_checked);
        Cursor cursor = conn.query("TB_CONTATOS", null,null,null,null,null,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                Contatos contatos=new Contatos();
                contatos.setId(Integer.valueOf(cursor.getString(0)));
                contatos.setNome(cursor.getString(1));
                contatos.setNumero(cursor.getString(2));
                adpContatos.add(contatos);
            }while (cursor.moveToNext());
        }
        return adpContatos;
    }
}

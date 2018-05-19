package com.example.robert.agenda.database;

/**
 * Created by Robert Souza Duarte on 8/11/17.
 */

public class ScriptSQL { //Structured Query Language script
    public static String getCriaContato(){
        StringBuilder SQLBuilder = new StringBuilder();

        SQLBuilder.append("CREATE TABLE IF NOT EXISTS TB_CONTATOS( ");
        SQLBuilder.append("ID INTEGER NOT NULL ");
        SQLBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        SQLBuilder.append("NOME VARCHAR(100), ");
        SQLBuilder.append("NUMERO VARCHAR(20) ");
        SQLBuilder.append("); ");
        return SQLBuilder.toString();

    }

}

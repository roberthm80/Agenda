package com.example.robert.agenda.control;

/**
 * Created by Robert Souza Duarte on 8/10/17.
 */

public class Contatos {
    private int id;
    private String nome;
    private String numero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    @Override
    public String toString(){
        return nome+" | "+numero;
    }
}

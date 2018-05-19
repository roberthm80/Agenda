package com.example.robert.agenda.model;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.robert.agenda.R;
import com.example.robert.agenda.control.CRUD;
import com.example.robert.agenda.control.Contatos;
import com.example.robert.agenda.database.DataBase;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private EditText edtNome, edtNumero;
    private Button btAdd, btDelete;

    private DataBase database;
    private CRUD crud;
    private SQLiteDatabase connection;
    private Contatos contatos;

    private ArrayAdapter<Contatos> adpContatos;
    private ListView lst_contatos;

    private int idAtual=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNome=(EditText)findViewById(R.id.edtNome);
        edtNumero=(EditText)findViewById(R.id.edtNumero);
        lst_contatos=(ListView)findViewById(R.id.listContatos);
        btAdd=(Button)findViewById(R.id.btAdd);
        btDelete=(Button)findViewById(R.id.btDelete);
        contatos = new Contatos();

        lst_contatos.setOnItemClickListener(this);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criaBanco();
                addContact();
                edtNome.setText("");
                edtNumero.setText("");
                edtNome.requestFocus();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criaBanco();
                deleteContact();
                edtNome.setText("");
                edtNumero.setText("");
                edtNome.requestFocus();

            }
        });
        criaBanco();
        Listar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //create options menu from menu_main
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelecionado=item.getItemId();
        itemMenuSelecionado(itemSelecionado);
        return super.onOptionsItemSelected(item);
    }

    public void itemMenuSelecionado(int itemSelecionado){
        switch (itemSelecionado){
            case R.id.mnAlterar:
                criaBanco();
                updateContact();
                break;
            case R.id.Sair:
                finish();break;
            case R.id.mnSobre:
                Intent intent = new Intent(this, Activity2Sobre.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }




    private void addContact(){
        try{
            contatos.setNome(edtNome.getText().toString());
            contatos.setNumero(edtNumero.getText().toString());
            crud.AddContact(contatos);
            Toast.makeText(this, "Salvo com sucesso!",Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            AlertDialog.Builder msg=new AlertDialog.Builder(this);
            msg.setTitle("Informação");
            msg.setMessage("Erro ao salvar dados: "+ex.getMessage());
            msg.setNeutralButton("Ok",null);
            msg.show();
        }
        Listar();
    }

    private void deleteContact(){
        try{
            crud.DeleteContact(idAtual);
            Toast.makeText(this,"Item deletado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch (Exception ex) {

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Alerta");
            msg.setMessage("Erro ao excluir dados: " + ex.getMessage());
            msg.setNeutralButton("Ok", null);
            msg.show();
        }
        Listar();
    }


    private void updateContact(){
        try{
            contatos.setNome(edtNome.getText().toString());
            contatos.setNumero(edtNumero.getText().toString());
            crud.UpdateContact(contatos,idAtual);
            Toast.makeText(this, "Alterado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch (Exception ex){

            AlertDialog.Builder msg=new AlertDialog.Builder(this);
            msg.setTitle("Informação");
            msg.setMessage("Erro ao alterar dados");
            msg.setNeutralButton("Ok",null);
            msg.show();
        }
        Listar();
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
        Contatos contatos = adpContatos.getItem(index);
        edtNome.setText(contatos.getNome());
        edtNumero.setText(contatos.getNumero());
        idAtual = contatos.getId();

    }


    private void Listar(){
        adpContatos=crud.ListContacts(this);
        lst_contatos.setAdapter(adpContatos);
    }

    private void criaBanco() {
        try {
            database = new DataBase(this);
            connection = database.getWritableDatabase();
            crud = new CRUD(connection);
        }catch (SQLException ex){
            AlertDialog.Builder msg=new AlertDialog.Builder(this);
            msg.setTitle("Alerta");
            msg.setMessage("Erro ao criar o banco: "+ex.getMessage());
            msg.setNeutralButton("Ok",null);
            msg.show();
        }
    }

}

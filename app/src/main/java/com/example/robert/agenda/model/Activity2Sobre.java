package com.example.robert.agenda.model;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.example.robert.agenda.R;

public class Activity2Sobre extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_sobre);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //create options menu from menu2.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
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
            case R.id.mnVoltar1:
                Intent intent = new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); break;
            case R.id.Sair1:
                finish();
        }
    }


}

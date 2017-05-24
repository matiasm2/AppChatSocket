package com.example.pow.appunaj;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMsj;
    public MensajesAdapter mensajesAdapter;

    public TextView titulo;
    private TextView msjError;
    private EditText campoTexto;
    private ProgressBar progressBar;
    private SocketCliente socketCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewMsj = (RecyclerView) findViewById(R.id.rv_mensajes);
        titulo = (TextView) findViewById(R.id.titulo);
        msjError = (TextView) findViewById(R.id.msjError);
        campoTexto = (EditText) findViewById(R.id.campoTexto);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewMsj.setLayoutManager(layoutManager);
        mensajesAdapter = new MensajesAdapter();

        recyclerViewMsj.setAdapter(mensajesAdapter);


        AsyncTask.execute(new SocketCliente(this));


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.boton){
//            String mensaje = campoTexto.getText().toString();
//            return socketCliente.enviarMensaje(mensaje);
        }
        return false;
    }

}

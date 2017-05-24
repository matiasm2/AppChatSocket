package com.example.pow.appunaj;

import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 * Created by pow on 23/05/17.
 */

public class SocketCliente extends Thread{
    BufferedReader entrada;
    MainActivity mainActivity;
    PrintWriter salida;

    public SocketCliente(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void run(){
        try{
            Socket conexion = new Socket("192.168.1.4", 1234);
            mainActivity.titulo.setText(conexion.getLocalAddress().toString());
            entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            salida = new PrintWriter(conexion.getOutputStream(), true);

            while (true){
                String lEntrada = entrada.readLine();
                if (!lEntrada.equals(null)){
//                    this.mensajes.append("Usuario: "+lEntrada+"\n");
                    mainActivity.mensajesAdapter.agregarMensaje(lEntrada);
                }
            }
        } catch (ConnectException e) {
            String msj = "Se ha rechazado la conexion o el destino no existe";
            Toast toast = Toast.makeText(this.mainActivity,msj,Toast.LENGTH_LONG);
            toast.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean enviarMensaje(String mensaje){
        try{
            salida.println(mensaje);
            return true;
        } catch (Exception e){
            String msj = "Ha ocurrido un error al enviar el mensaje";
            Toast toast = Toast.makeText(this.mainActivity,msj,Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }
}

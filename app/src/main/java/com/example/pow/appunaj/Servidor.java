import com.sun.security.ntlm.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;

/**
 * Created by pow on 14/05/17.
 */
public class Servidor extends Thread {

    private ServerSocket socketSv;

    public Servidor (int puerto) throws IOException {
        this.socketSv = new ServerSocket(puerto);
        this.socketSv.setSoTimeout(42000);
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.print("Esperando conexion\n");
                Socket socketAtendido = this.socketSv.accept();
                if (socketAtendido.isConnected()){
                    System.out.println("Conexion establecida con "+socketAtendido.getInetAddress());
                    PrintWriter salida = new PrintWriter(socketAtendido.getOutputStream(), true);
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socketAtendido.getInputStream()));
//                    salida.println("cabeza e chancho");
                    // TODO: 16/05/17 Hacer una clase manejador que organice el broadcast de msj
                    String lEntrada = entrada.readLine();
                    if (!lEntrada.equals(null)){
                        System.out.println(lEntrada);
                        salida.println(lEntrada);
                    }
                }

            } catch (SocketTimeoutException e){
                System.out.println("\nSe ha terminado el tiempo de espera\n");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Servidor sv = new Servidor(1234);
        sv.start();
    }
}

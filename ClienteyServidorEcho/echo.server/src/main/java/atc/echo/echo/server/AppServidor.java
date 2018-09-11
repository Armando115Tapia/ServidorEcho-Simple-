package atc.echo.echo.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.sampled.Port;

public class AppServidor 
{
	private static int port = 50000;
	
    public static void main( String[] args ) throws IOException
    {
    	DataInputStream flujo_entrada;
    	DataOutputStream flujo_salida = null;
    	ServerSocket serverSocket= null;
    	Socket socket = null;
    	String mensajeRecibido = "";
    	
    	try {
    		serverSocket = new ServerSocket(port);
    		System.out.println("El servidor esta en escucha en el puerto: " +port);
    		socket=serverSocket.accept();
    		System.out.println("Un cliente se a conectado ");
    		
    		while(true) {
    			flujo_entrada = new DataInputStream(socket.getInputStream());
    			mensajeRecibido = flujo_entrada.readUTF();
        		flujo_salida = new DataOutputStream(socket.getOutputStream());
        		flujo_salida.writeUTF("Servidor dice: " +mensajeRecibido+"\n");		
    		}
    		
    
    	} catch (Exception e) {
    		System.out.println("Error de entrada/salida " + e.getMessage());
    		e.printStackTrace();
			
		}/*finally{
			socket.close();
		}*/
        
        
    }
}

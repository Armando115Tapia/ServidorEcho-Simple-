package atc.echo.echo.server;

/**
 * @see http://www.jtech.ua.es/j2ee/publico/lja-2012-13/sesion05-apuntes.html
 * */
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
    	/**Se coloca las declaraciones en este sector para poder usar correctamente el bloque try-catch-finally*/
    	
    	/**Para transferir datos mediante cualquier flujo de E/S, debemos codificar estos datos 
    	 * en forma de array de bits 
    	 * Los flujos de DataInputStream y DataOutputStream nos permiten codificar y decodificar 
    	 * respectivamente los tipos de datos simples en forma de array de bytes */
    	DataInputStream flujo_entrada=null;
    	DataOutputStream flujo_salida = null;
    	/**Un socket servidor es el que escucha en un puerto definido*/
    	ServerSocket serverSocket= null;
       /**Cuando se conecte un cliente, el server socket le pasara la conexion a un nuevo socket*/
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

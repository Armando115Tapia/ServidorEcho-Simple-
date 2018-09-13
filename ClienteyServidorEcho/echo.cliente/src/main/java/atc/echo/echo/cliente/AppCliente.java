package atc.echo.echo.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AppCliente 
{
	private static String host = "172.29.40.240";
	private static int port =50000;
	
    public static void main( String[] args ) throws IOException
    {
    	Socket socket = null;
    	DataOutputStream flujo_salida=null;
    	DataInputStream flujo_entrada = null;
    	String flag = "";
    	String msmRecibido = "";
    	
    	try {
    		socket = new Socket(host, port);
    		do {
    			flujo_salida = new DataOutputStream(socket.getOutputStream());	
				System.out.println ("Por favor introduzca una msm para enviar al servidor (\"EXIT\" para salir ): ");
				String entradaTeclado = "";
				Scanner entradaEscaner = new Scanner (System.in);
		        entradaTeclado = entradaEscaner.nextLine ();
		        flujo_salida.writeUTF(entradaTeclado);
		        flujo_entrada = new DataInputStream(socket.getInputStream());
		        msmRecibido = flujo_entrada.readUTF();
		        
		        if(cuentaRepeticionesCaracter(msmRecibido,':')==1);
		        flag=msmRecibido.substring(msmRecibido.indexOf(":")+1);
		        
		        System.out.println(msmRecibido);
		        System.out.println(flag.trim());
		        
				}while(!"EXIT".equals(flag.trim()));
			
		}catch(UnknownHostException e1) {
			System.out.println("El host no está disponible ");
			e1.printStackTrace();
		} catch(IOException e2) {
			e2.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			socket.close();
			
		}
    	
        
    }
    /**Cuenta las veces que se repiten los dos puntos, para el caso de EXIT */
    public static int cuentaRepeticionesCaracter(String inRespuesaServidor, char inCaracter) {
    	int posicion=0, contador=0;
    	posicion=inRespuesaServidor.indexOf(inCaracter);
    	while(posicion != -1) {
    		contador++;
    		posicion=inRespuesaServidor.indexOf(inCaracter,posicion+1);
    	}
    	
    	return contador;
    }
    
    
}

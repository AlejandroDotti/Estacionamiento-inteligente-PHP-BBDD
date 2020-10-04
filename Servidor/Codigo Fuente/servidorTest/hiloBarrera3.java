package servidorTest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class hiloBarrera3 extends Thread {
	
	private String ipBarrera3;
	
	
	public hiloBarrera3(String str) {
		 ipBarrera3 = str;
		 } 
	
	
	public void run() {
		
		while(true)
		{
			
			InetAddress dirIP_Barrera3;
			Integer puerto_hiloBarrera3, puerto_Barrera3;
			
			try{
				//System.out.println("Bienvenido al hilo Barrera 1\n");
				
				
				String puertoB = "5055";
				
				String puertoA = "5053";
				
				String mensaje = "OK";
				
				//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
				dirIP_Barrera3 = InetAddress.getByName(ipBarrera3+".unm.ar");//cambio de tipos
				puerto_hiloBarrera3 = Integer.parseInt(puertoA);//para direcciones IP
				puerto_Barrera3 = Integer.parseInt(puertoB);// y puertos
				
				DatagramSocket socketA = new DatagramSocket (puerto_hiloBarrera3);
				
				int MAX_LONGITUD = 100;
				
				byte [] bufferRecepcion = new byte[MAX_LONGITUD];//Preparamos el buffer de recepcion
				//Preparamos el datagrama de respuesta a recibir
				DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
				
				//Recibimos la respuesta del proceso B, la presentamos al usuario y cerramos la conexion.
				socketA.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
				
				//String respuesta = new String(bufferRecepcion);
				
				//String respuesta= Arrays.toString(bufferRecepcion);
				
				//System.arraycopy(datagrama_respuesta.getData(), datagrama_respuesta.getOffset(), bufferRecepcion, 0, datagrama_respuesta.getLength());
				
				
				
				String respuesta = new String(datagrama_respuesta.getData(), datagrama_respuesta.getOffset(), datagrama_respuesta.getLength());
				
				//System.out.println("La respuesta de B: " + respuesta);
				
				
				if(respuesta.equalsIgnoreCase("OK"))
				{
				
				byte [] bufferEnvio = mensaje.getBytes();//Preparamos el vector a enviar
				
				DatagramPacket datagrama_peticion = new DatagramPacket (//Preparamos el envio
							bufferEnvio,
							bufferEnvio.length,
							dirIP_Barrera3,
							puerto_Barrera3
						);
				socketA.send(datagrama_peticion);//Realizamos el envio asicrono
				
				
				
				}
				
				socketA.close();

			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			
		}
		
	}

}


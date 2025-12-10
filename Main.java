package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) throws IOException 
	{
		ServerSocket socketServidor = new ServerSocket(9000);
		Socket socketCliente;
		Tablon tablon = new Tablon();
		Hilo hilo;
		boolean fin = false;
		int numeroCliente = 1;
		
		while (!fin)
		{
			socketCliente = socketServidor.accept();
			hilo = new Hilo(tablon, socketCliente,numeroCliente++);
			hilo.start();
		}
		socketServidor.close();
	}

}

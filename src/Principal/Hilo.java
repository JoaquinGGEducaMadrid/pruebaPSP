package Principal;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Hilo extends Thread
{
	private Tablon tablon;
	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	private int identificador;
	private boolean fin;
	
	public Hilo (Tablon tablon, Socket socket,int identificador) throws IOException
	{
		this.tablon = tablon;
		this.socket = socket;
		this.fin = false;
		this.identificador = identificador;
		
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(this.flujoEntrada);
		this.salida = new ObjectOutputStream(this.flujoSalida);
	}
	
	public void run()
	{
		Mensaje mensaje;
		tablon.suscribirse(this);
		while (!fin)
		{
			try 
			{
				mensaje = recibirMensaje();
				mensaje.setEmisor(identificador);
				tablon.difundir(this,mensaje);
			} catch (ClassNotFoundException | IOException e) 
			{
				System.out.println("Posble desconexión de cliente "+this.identificador);
				fin = true;
			}
		}
		tablon.desuscribirse(this);
		terminar();
	}
	
	public Mensaje recibirMensaje() throws ClassNotFoundException, IOException
	{
		Mensaje mensaje;
		mensaje = (Mensaje) entrada.readObject();
		return mensaje;
	}
	
	public void enviarMensaje(Mensaje mensaje) throws IOException
	{
		salida.writeObject(mensaje);
	}
	
	public void terminar() 
	{
		try 
		{
			salida.close();
			entrada.close();
			flujoSalida.close();
			flujoEntrada.close();
			socket.close();
		} 
		catch (IOException e) 
		{
				//e.printStackTrace();
		}
	}
}
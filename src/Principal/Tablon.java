package Principal;

import java.io.IOException;
import java.util.ArrayList;

public class Tablon 
{
	private ArrayList<Hilo> hilos;
	
	public Tablon()
	{
		this.hilos = new ArrayList<>();
	}
	
	public synchronized void suscribirse(Hilo hilo)
	{
		this.hilos.add(hilo);
	}
	
	public synchronized void desuscribirse(Hilo hilo)
	{
		this.hilos.remove(hilo);
	}
	
	public synchronized void difundir(Hilo hilo, Mensaje mensaje) throws IOException
	{
		for (Hilo h: this.hilos)
			if (h!=hilo)
				h.enviarMensaje(mensaje);
	}
}

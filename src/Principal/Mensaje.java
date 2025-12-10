package Principal;

import java.io.Serializable;

public class Mensaje implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int emisor;
	private String contenido;
	
	public Mensaje(String contenido)
	{
		this.contenido = contenido;
	}

	public void setEmisor(int emisor)
	{
		this.emisor = emisor;
	}
	
	public String toString()
	{
		return this.emisor + " dice: "+this.contenido;
	}
}

package Paquete;

import java.util.ArrayList;

public abstract class Promocion extends Componente{
	protected ArrayList<Atraccion> atracciones;
	
	public Promocion(String nombre, ArrayList<Atraccion> atracciones) {
		
		super(nombre);
		this.atracciones=atracciones;
		this.tipoAtraccion=atracciones.get(0).tipoAtraccion;
		
		double costoTotal = 0;
		double tiempoTotal = 0;
		for (Atraccion atraccion : this.atracciones) {
			costoTotal += atraccion.getCosto();
			tiempoTotal += atraccion.tiempo;
		}
		
		this.costo=costoTotal;
		this.tiempo=tiempoTotal;
		
	}
	
	@Override
	public double getTiempo() {
		return this.tiempo;
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	public ArrayList<Atraccion> getAtracciones(){
		return this.atracciones;
	}
	
	public TipoAtraccion getTipoAtraccion() {
		return this.tipoAtraccion;
	}
	
	public int compareTo(Promocion other) {		
		Double costo = this.getCosto();
		Double otroCosto = other.getCosto();
		
		return otroCosto.compareTo(costo);
	}
	
	public void decrementarCupo() {
		for(Atraccion atraccion: atracciones) {
			atraccion.decrementarCupo();
		}
	}
	
	@Override
	public String toString() {
		String msj="Promocion "+nombre+ 
				"\n-Atracciones incluidas: [ ";
		for(Atraccion atraccion : atracciones) {
			msj+=atraccion.getNombre()+", ";
		}
		msj+="]\n-Duracion: "+tiempo+"hs"+
				"\n-Precio Total: $"+costo;
		
		return msj;
	}
}

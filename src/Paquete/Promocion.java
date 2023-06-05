package Paquete;

import java.util.ArrayList;

public abstract class Promocion extends Componente{
	protected ArrayList<Atraccion> atracciones;
	
	public Promocion(String nombre, TipoAtraccion tipo) {
		super(nombre, 0, 0, tipo);
		this.atracciones = new ArrayList<>();
	}
	
	public void addAtraccion(Atraccion atraccion) {
		this.atracciones.add(atraccion);
		this.costo+=atraccion.costo;
		this.tiempo+=atraccion.tiempo;
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
	
	@Override
	public TipoAtraccion getTipoAtraccion() {
		return this.tipoAtraccion;
	}
	
	@Override
	public boolean sinCupo() {
		boolean sinCupo=false;
		for(Atraccion atraccion: atracciones) {
			if(atraccion.sinCupo()==true) {
				sinCupo=true;
				break;	
			}	
		}
		return sinCupo;
	}
	
	public int compareTo(Promocion other) {		
		Double costo = this.getCosto();
		Double otroCosto = other.getCosto();
		
		return otroCosto.compareTo(costo);
	}
	
	@Override
	public void decrementarCupo() {
		for(Atraccion atraccion: atracciones) {
			atraccion.decrementarCupo();
		}
	}
	
	@Override
	public String toString() {
		String msj="Promocion "+nombre+ 
				"\n-Tipo de Atracciones:\t"+this.tipoAtraccion+
				"\n-Atracciones incluidas:\t[ ";
		for(Atraccion atraccion : atracciones) {
			msj+=atraccion.getNombre()+", ";
		}
		msj+="]";
		
		return msj;
	}
}

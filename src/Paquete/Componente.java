package Paquete;

import java.util.ArrayList;

public abstract class Componente{
	protected String nombre;
	protected double costo;
	protected double tiempo;
	protected TipoAtraccion tipoAtraccion;
	
	Componente(String nombre){
		this.nombre=nombre;
	}
	
	Componente(String nombre, double costo, double tiempo, TipoAtraccion tipoAtraccion) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.tipoAtraccion = tipoAtraccion;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public double getTiempo() {
		return this.tiempo;
	}
	
	public TipoAtraccion getTipoAtraccion() {
		return this.tipoAtraccion;
	}
	
	public int compareTo(Componente other) {
		Double costo = this.getCosto();
		Double otroCosto = other.getCosto();
		
		int ret = otroCosto.compareTo(costo);
		
		if(ret==0) {
			Double tiempo = this.tiempo;
			Double otroTiempo = other.tiempo;
			
			ret= otroTiempo.compareTo(tiempo);
		}
		
		return ret; 
	}
	
	public abstract double getCosto();
	
	public abstract void decrementarCupo();
	
	public abstract boolean sinCupo();
	
	public abstract boolean hayAtraccionAceptada(Atraccion otraAtraccion);
}

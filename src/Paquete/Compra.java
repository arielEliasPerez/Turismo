package Paquete;

import java.util.ArrayList;

public class Compra {
	private Usuario usuario;
	private ArrayList<Componente>sugerenciasDiaria;
	private double costoTotal;
	private double tiempoTotal;
	
	public Compra(Usuario usuario) {
		this.usuario=usuario;
		this.sugerenciasDiaria = new ArrayList<>();
		this.costoTotal=0;
		this.tiempoTotal=0;
	}
	
	public void addSugerenciaDiaria(Componente sugerencia) {
		this.sugerenciasDiaria.add(sugerencia);
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public double getTiempo() {
		return this.tiempoTotal;
	}
	
	public double getCosto() {
		return this.costoTotal;
	}

	public void incrementarCostoTotal(double costo) {
		this.costoTotal+=costo;
		
	}

	public void incrementarTiempoTotal(double tiempo) {
		this.tiempoTotal+=tiempo;
		
	}
	
	public String toString() {
		String cadena = this.usuario.toString()+
				"\nCompras:";
		for(Componente compra : sugerenciasDiaria) {
			cadena += "\n"+compra.getNombre()+
					"\n-Costo:"+compra.getCosto()+
					"\n-Tiempo:"+compra.getTiempo()+"\n";
		}
		cadena += "\nTotal a pagar: $"+this.costoTotal+
				"\nTiempo a invertir: "+this.tiempoTotal+" hs\n";
		cadena +="-------------------------------------------------------\n";
		
		return cadena;		
	}
}

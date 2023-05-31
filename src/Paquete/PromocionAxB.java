package Paquete;

import java.util.ArrayList;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionFree;

	PromocionAxB(String nombre, ArrayList<Atraccion> atracciones, Atraccion atraccionFree) {
		super(nombre, atracciones);
		this.atraccionFree = atraccionFree;

		this.atracciones.add(atraccionFree);
	}

	@Override
	public double getCosto() {
		return this.costo;
	}

	@Override
	public double getTiempo() {
		return this.tiempo + this.atraccionFree.tiempo;
	}
	
	@Override
	public String toString() {
		return	super.toString()+
				"\n-Atraccion Gratis: "+atraccionFree.getNombre();
				
	}
	
}

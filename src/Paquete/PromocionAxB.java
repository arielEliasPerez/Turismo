package Paquete;

import java.util.ArrayList;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionFree;

	public PromocionAxB(String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones, int tipoPromo, Atraccion atraccionFree) {
		super(nombre, tipoAtraccion, atracciones, tipoPromo);
		this.atraccionFree=atraccionFree;
		this.atracciones.add(atraccionFree);
		this.tiempo+=atraccionFree.tiempo;
	}

	@Override
	public double getCosto() {
		return this.costo;
	}
	
	@Override
	public String toString() {
		return	super.toString()+
				"\n-Duracion:\t\t"+tiempo+"hs"+
				"\n-Precio Total:\t\t$"+costo+
				"\n-Atraccion Gratis:\t"+atraccionFree.getNombre();
				
	}
	
}

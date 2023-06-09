package Paquete;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {
	private double costoPaquete;

	public PromocionAbsoluta(String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones, int tipoPromo, double costoPaquete) {
		super(nombre, tipoAtraccion, atracciones, tipoPromo);
		this.costoPaquete=costoPaquete;
	}
	
	@Override
	public double getCosto() {
		return this.costoPaquete;
	}

	
	@Override
	public String toString() {
		return super.toString()+
				"\n-Duracion:\t\t"+tiempo+"hs"+
				"\n-Precio Total:\t\t$"+costoPaquete;
	}
}

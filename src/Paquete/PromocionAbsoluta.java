package Paquete;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {
	private double costoPaquete;

	public PromocionAbsoluta(String nombre, ArrayList<Atraccion> atracciones, double costoPaquete) {
		super(nombre, atracciones);
		this.costoPaquete = costoPaquete;
	}
	
	@Override
	public double getCosto() {
		return this.costoPaquete;
	}

	
	
}

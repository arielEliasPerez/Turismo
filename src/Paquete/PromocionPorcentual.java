package Paquete;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion{
	private double porcentajeDescuento;
	
	public PromocionPorcentual(String nombre, ArrayList<Atraccion> atracciones, double porcentajeDescuento) {
		super(nombre, atracciones);
		this.porcentajeDescuento=porcentajeDescuento;
	}
	
	@Override
	public double getCosto() {
		return this.costo*(1 - this.porcentajeDescuento/100);
	}
	
	@Override
	public String toString() {
		return super.toString()+
				"\n-Precio con descuento: "+ getCosto();
	}
}

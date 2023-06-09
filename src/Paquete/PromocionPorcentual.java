package Paquete;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion{
	private double porcentajeDescuento;
	
	public PromocionPorcentual(String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones, int tipoPromo, double porcentajeDescuento) {
		super(nombre, tipoAtraccion, atracciones, tipoPromo);
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	@Override
	public double getCosto() {
		return this.costo*(1 - this.porcentajeDescuento/100);
	}
	
	@Override
	public String toString() {
		return super.toString()+
				"\n-Duracion:\t\t"+tiempo+"hs"+
				"\n-Precio Total:\t\t$"+costo+
				"\n-Precio con descuento:\t\t"+ getCosto();
	}
}

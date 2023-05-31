package Paquete;

public class PromocionPorcentual extends Promocion{
	private double porcentajeDescuento;
	
	public PromocionPorcentual(String nombre, TipoAtraccion tipo, double porcentajeDescuento) {
		super(nombre, tipo);
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	@Override
	public double getCosto() {
		return this.costo*(1 - this.porcentajeDescuento/100);
	}
	
	@Override
	public String toString() {
		return super.toString()+
				"\n-Duracion: "+tiempo+"hs"+
				"\n-Precio Total: $"+costo+
				"\n-Precio con descuento: "+ getCosto();
	}
}

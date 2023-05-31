package Paquete;

public class PromocionAbsoluta extends Promocion {
	private double costoPaquete;

	public PromocionAbsoluta(String nombre, TipoAtraccion tipo, double costoPaquete) {
		super(nombre, tipo);
		this.costoPaquete=costoPaquete;
	}
	
	@Override
	public double getCosto() {
		return this.costoPaquete;
	}

	
	
}

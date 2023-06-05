package Paquete;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionFree;

	public PromocionAxB(String nombre, TipoAtraccion tipo, Atraccion atraccionFree) {
		super(nombre, tipo);
		this.atraccionFree=atraccionFree;
		atracciones.add(atraccionFree);
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

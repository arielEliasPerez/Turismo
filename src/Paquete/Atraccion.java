package Paquete;

public class Atraccion extends Componente {
	private int cupo;

	public Atraccion(String nombre, double costo, double tiempo, int cupo, TipoAtraccion tipo) {
		super(nombre, costo, tiempo,tipo);
		this.cupo = cupo;
	}

	@Override
	public double getCosto() {
		return this.costo;
	}
	
	@Override
	public void decrementarCupo() {
		this.cupo--;
	}

	@Override
	public boolean sinCupo() {
		return this.cupo==0;
	}

	@Override
	public String toString() {
		return "Atraccion\t\t" + nombre +
				"\n-Costo:\t\t\t$" + costo + 
				"\n-Tiempo:\t\t" + tiempo+" hs"+
				"\n-tipo de Atraccion:\t" + tipoAtraccion;
	}
	
	//@Override
	public boolean equals(Atraccion other) {
		return this.nombre.equals(other.nombre);
	}
	
	@Override
	public boolean hayAtraccionAceptada(Atraccion otraAtraccion) {
		return this.equals(otraAtraccion);
	}
	
}

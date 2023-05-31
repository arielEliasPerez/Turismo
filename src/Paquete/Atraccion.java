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
	public double getTiempo() {
		return this.tiempo;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	public int getCupo() {
		return this.cupo;
	}
	
	public int compareTo(Atraccion other) {
		Double costo = this.getCosto();
		Double otroCosto = other.costo;
		
		return otroCosto.compareTo(costo);
	}

	@Override
	public String toString() {
		return "Atraccion " + nombre +
				"\n-Costo: $" + costo + 
				"\n-Tiempo=" + tiempo+
				"\n-tipo de Atraccion: " + tipoAtraccion;
	}
	
	public TipoAtraccion getTipoAtraccion() {
		return this.tipoAtraccion;
	}
	
	@Override
	public void decrementarCupo() {
		this.cupo--;
	}
	
	//@Override
	public boolean equals(Atraccion other) {
		return this.nombre.equals(other.nombre);
	}
}
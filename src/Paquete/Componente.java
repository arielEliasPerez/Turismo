package Paquete;

public abstract class Componente{
	protected String nombre;
	protected double costo;
	protected double tiempo;
	protected TipoAtraccion tipoAtraccion;
	
	Componente(String nombre){
		this.nombre=nombre;
	}
	
	Componente(String nombre, double costo, double tiempo, TipoAtraccion tipoAtraccion) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.tipoAtraccion=tipoAtraccion;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public double getTiempo() {
		return this.tiempo;
	}
	
	public TipoAtraccion getTipoAtraccion() {
		return this.tipoAtraccion;
	}
	
	public int compareTo(Componente other) {
		Double costo = this.getCosto();
		Double otroCosto = other.costo;
		
		return otroCosto.compareTo(costo);
	}
	
	public abstract double getCosto();
	
	public abstract void decrementarCupo();
	
	public abstract boolean sinCupo();
}

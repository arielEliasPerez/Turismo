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

	public abstract double getCosto();

	public abstract double getTiempo();

	public abstract String getNombre();
	
	public abstract void decrementarCupo();
}

package Paquete;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempo;
	private TipoAtraccion atraccionPreferida;

	public Usuario(String nombre, double presupuesto, double tiempo, TipoAtraccion atraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.atraccionPreferida = atraccionPreferida;
	}
	
	public TipoAtraccion getAtraccionPreferida() {
		return this.atraccionPreferida;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public double getPresupuesto(){
		return this.presupuesto;
	}

	public void restarTiempo(double tiempo) {
		this.tiempo-=tiempo;
	}

	public void restarPresupuesto(double costo) {
		this.presupuesto-=costo;
	}

	public double getTiempo() {
		return this.tiempo;
	}
}

package Paquete;

import java.util.ArrayList;

public class Compra {
	private Usuario usuario;
	private ArrayList<Componente>sugerenciaDiaria;
	private double costoTotal;
	private double tiempoTotal;
	
	public Compra(Usuario usuario) {
		this.usuario=usuario;
		this.sugerenciaDiaria = new ArrayList<>();
		this.costoTotal=0;
		this.tiempoTotal=0;
	}
	
	public void addSugerenciaDiaria(Componente sugerencia) {
		this.sugerenciaDiaria.add(sugerencia);
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void incrementarCostoTotal(double costo) {
		this.costoTotal+=costo;
		
	}

	public void incrementarTiempoTotal(double tiempo) {
		this.tiempoTotal+=tiempo;
		
	}
}

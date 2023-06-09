package Paquete;

import java.util.ArrayList;

public abstract class Promocion extends Componente {
	protected int tipoPromo;
	protected ArrayList<Atraccion> atracciones;

	public Promocion(String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones, int tipoPromo) {
		super(nombre, 0, 0, tipoAtraccion);
		this.tipoPromo = tipoPromo;
		this.atracciones = atracciones;

		for (Atraccion atraccion : atracciones) {
			this.costo += atraccion.costo;
			this.tiempo += atraccion.tiempo;
		}
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	@Override
	public boolean sinCupo() {
		boolean sinCupo = false;
		for (Atraccion atraccion : atracciones) {
			if (atraccion.sinCupo() == true) {
				sinCupo = true;
				break;
			}
		}
		return sinCupo;
	}

	@Override
	public void decrementarCupo() {
		for (Atraccion atraccion : atracciones) {
			atraccion.decrementarCupo();
		}
	}

	@Override
	public String toString() {
		String msj = "Promocion " + nombre + "\n-Tipo de Atracciones:\t" + this.tipoAtraccion
				+ "\n-Atracciones incluidas:\t[ ";
		for (Atraccion atraccion : atracciones) {
			msj += atraccion.getNombre() + ", ";
		}
		msj += "]";

		return msj;
	}

	@Override
	public boolean hayAtraccionAceptada(Atraccion otraAtraccion) {
		boolean atraccionAceptada=false;
		
		for(Atraccion myAtraccion : this.atracciones) {
			if(myAtraccion.equals(otraAtraccion)) {
				atraccionAceptada=true;
				break;
			}
		}
		return atraccionAceptada;
	}
}

package Paquete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SistemaTurismo {
	private ArrayList<Usuario> usuarios;
	private ArrayList<Atraccion> atracciones;
	private ArrayList<Promocion> promociones;

	public SistemaTurismo() {
		this.atracciones = new ArrayList<>();
		this.promociones = new ArrayList<>();
	}

	public void generarListas() {
		Atraccion atr1 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		Atraccion atr2 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		Atraccion atr3 = new Atraccion("La Comarca", 3, 6.5, 150, TipoAtraccion.DEGUSTACION);
		Atraccion atr4 = new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA);
		Atraccion atr5 = new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE);
		Atraccion atr6 = new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION);
		Atraccion atr7 = new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.AVENTURA);
		Atraccion atr8 = new Atraccion("Bosque Negro", 3, 3, 12, TipoAtraccion.PAISAJE);

		atracciones.add(atr1);
		atracciones.add(atr2);
		atracciones.add(atr3);
		atracciones.add(atr4);
		atracciones.add(atr5);
		atracciones.add(atr6);
		atracciones.add(atr7);
		atracciones.add(atr8);

		Promocion promo1 = new PromocionPorcentual("Pack Aventura", TipoAtraccion.AVENTURA, 10);
		promo1.addAtraccion(atr1);
		promo1.addAtraccion(atr4);
		Promocion promo2 = new PromocionAbsoluta("Pack Degustacion", TipoAtraccion.DEGUSTACION, 36);
		promo2.addAtraccion(atr3);
		promo2.addAtraccion(atr6);
		Promocion promo3 = new PromocionAxB("Pack Paisaje", TipoAtraccion.PAISAJE, atr8);
		promo3.addAtraccion(atr2);
		promo3.addAtraccion(atr5);

		promociones.add(promo1);
		promociones.add(promo2);
		promociones.add(promo3);
	}

	public void ordenarListas() {
		atracciones.sort(new Comparator<Atraccion>() {
			@Override
			public int compare(Atraccion first, Atraccion second) {
				return first.compareTo(second);
			}
		});

		promociones.sort(new Comparator<Promocion>() {
			@Override
			public int compare(Promocion first, Promocion second) {
				return first.compareTo(second);
			}
		});
	}

	public void generarItinerarioAUsuarios() {
		// for(Usuario usuario : usuarios){
		Usuario usuario = new Usuario("Pepe", 1000, 8, TipoAtraccion.PAISAJE);

		System.out.println("Nombre del visitante: " + usuario.getNombre() + "\n");

		ArrayList<Atraccion> atraccionesAceptadas = iniciarSugerencias(usuario);

		// mostrarItinerario()
		System.out.println("\n--------------------------------------------");
		System.out.println("Itinerario de " + usuario.getNombre());
		for (Atraccion atraccion : atraccionesAceptadas) {
			System.out.println(
					"-- Atraccion: " + atraccion.getNombre() + "\tHoras de atracción: " + atraccion.getTiempo() + "hs");
		}

		// }
	}

	private ArrayList<Atraccion> iniciarSugerencias(Usuario usuario) {
		ArrayList<Componente> listaSugerencias = this.generarListaSugerencias(usuario);
		ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<>();

		for (Componente sugerencia : listaSugerencias) {

			if (usuario.getPresupuesto() == 0 || usuario.getTiempo() == 0)
				break;

			if (sePuedeSugerir(usuario, sugerencia, atraccionesAceptadas) == true) {

				mensajeDeSugerencia(sugerencia);

				boolean respuesta = obtenerRespuesta();

				if (respuesta == true) {
					System.out.println("¡Aceptado!");

					atraccionesAceptadas = guardarAtraccionesAceptadas(sugerencia, atraccionesAceptadas);
					usuario = actualizarDatosUsuario(usuario, sugerencia);
					decrementarCupo(sugerencia);

				}
			}
		}

		return atraccionesAceptadas;
	}

	private ArrayList<Componente> generarListaSugerencias(Usuario usuario) {
		ArrayList<Componente> listaSugerida = new ArrayList<Componente>();
		int i = 0;
		while (i < promociones.size()) {
			if (promociones.get(i).getTipoAtraccion() == usuario.getAtraccionPreferida())
				listaSugerida.add(promociones.remove(i));
			else
				i++;
		}

		i = 0;
		while (i < atracciones.size()) {
			if (atracciones.get(i).getTipoAtraccion() == usuario.getAtraccionPreferida())
				listaSugerida.add(atracciones.remove(i));
			else
				i++;
		}

		listaSugerida.addAll(promociones);
		listaSugerida.addAll(atracciones);

		return listaSugerida;
	}

	private boolean sePuedeSugerir(Usuario usuario, Componente sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {
		boolean atraccionAceptada = esAtraccionAceptada(sugerencia, atraccionesAceptadas);

		return atraccionAceptada == false && usuario.getPresupuesto() - sugerencia.getCosto() >= 0
				&& usuario.getTiempo() - sugerencia.getTiempo() >= 0;
	}

	private static boolean esAtraccionAceptada(Componente sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {

		boolean atraccionAceptada = false;
		if (sugerencia.getClass() == Atraccion.class) {
			for (Atraccion atraccion : atraccionesAceptadas) {
				if (((Atraccion) sugerencia).equals(atraccion)) {
					atraccionAceptada = true;
					break;
				}
			}
		}

		return atraccionAceptada;
	}

	private void mensajeDeSugerencia(Componente sugerencia) {
		System.out.println("---------------------------------------------------------------");
		System.out.println(sugerencia.toString());
		System.out.println("\nAcepta la sugerecia? Ingrese S o N");
	}

	private boolean obtenerRespuesta() {
		char opc;

		do {
			Scanner entrada = new Scanner(System.in);
			opc = entrada.next().charAt(0);

		} while (opc != 's' & opc != 'S' && opc != 'n' && opc != 'N');

		return opc == 's' || opc == 'S';
	}

	private ArrayList<Atraccion> guardarAtraccionesAceptadas(Componente sugerencia,
			ArrayList<Atraccion> atraccionesAceptadas) {
		if (sugerencia.getClass() == Atraccion.class)
			atraccionesAceptadas.add((Atraccion) sugerencia);
		else
			atraccionesAceptadas.addAll(((Promocion) sugerencia).getAtracciones());

		return atraccionesAceptadas;
	}

	private Usuario actualizarDatosUsuario(Usuario usuario, Componente sugerencia) {
		usuario.restarTiempo(sugerencia.getTiempo());
		usuario.restarPresupuesto(sugerencia.getCosto());

		return usuario;
	}

	private void decrementarCupo(Componente sugerencia) {

	}
}

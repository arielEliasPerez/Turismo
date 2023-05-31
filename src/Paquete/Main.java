package Paquete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// ArrayList<Usuario> usuarios = generarListaUsuario();
		ArrayList<Promocion> promociones = generarListaPromociones();
		ArrayList<Atraccion> atracciones = generarListaAtracciones();

		// Acontinuacion se ordenan las listas promociones y atracciones:
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

		// Acá arrancaría el ciclo por cada usuario
		Usuario usuario = new Usuario("Pepe", 10, 8, TipoAtraccion.PAISAJE);

		ArrayList<Componente> listaSugerencias = generarListaSugerencia(usuario, promociones, atracciones);

		ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<>();

		System.out.println("Nombre del visitante: " + usuario.getNombre() + "\n");

		for (Componente sugerencia : listaSugerencias) {
			if (usuario.getPresupuesto() == 0)
				break;

			boolean atraccionAceptada = esAtraccionAceptada(sugerencia, atraccionesAceptadas);

			if (atraccionAceptada == false && usuario.getPresupuesto() - sugerencia.getCosto() >= 0
					&& usuario.getTiempo() - sugerencia.getTiempo() >= 0) {
				System.out.println("---------------------------------------------------------------");
				System.out.println(sugerencia.toString());
				System.out.println("\nAcepta la sugerecia? Ingrese S o N");
				
				Scanner entrada = new Scanner(System.in);
				char opc;
				opc = entrada.next().charAt(0);

				if (opc == 's' || opc == 'S') {
					System.out.println("¡Aceptado!");

					if (sugerencia.getClass() == Atraccion.class)
						atraccionesAceptadas.add((Atraccion) sugerencia);
					else
						atraccionesAceptadas.addAll(((Promocion) sugerencia).getAtracciones());

					usuario.restarTiempo(sugerencia.getTiempo());
					usuario.restarPresupuesto(sugerencia.getCosto());
					sugerencia.decrementarCupo();

				}
			}
		}

		System.out.println("Itinerario de " + usuario.getNombre());
		for (Atraccion atraccion : atraccionesAceptadas) {
			System.out.println(
					"-- Atraccion: " + atraccion.getNombre() + "\tHoras de atracción: " + atraccion.getTiempo() + "hs");
		}

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

	private static ArrayList<Componente> generarListaSugerencia(Usuario usuario, ArrayList<Promocion> promociones,
			ArrayList<Atraccion> atracciones) {
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

	private static ArrayList<Promocion> generarListaPromociones() {

		ArrayList<Atraccion> atracciones1 = new ArrayList<>();
		atracciones1.add(new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA));
		atracciones1.add(new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA));

		ArrayList<Atraccion> atracciones2 = new ArrayList<>();
		atracciones2.add(new Atraccion("La Comarca", 3, 6.5, 150, TipoAtraccion.DEGUSTACION));
		atracciones2.add(new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION));

		ArrayList<Atraccion> atracciones3 = new ArrayList<>();
		atracciones3.add(new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE));
		atracciones3.add(new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE));

		ArrayList<Promocion> promociones = new ArrayList<>();

		promociones.add(new PromocionPorcentual("Pack Aventura", atracciones1, 10));
		promociones.add(new PromocionAbsoluta("Pack Degustacion", atracciones2, 36));
		promociones.add(new PromocionAxB("Pack Paisaje", atracciones3,
				new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.PAISAJE)));

		return promociones;
	}

	private static ArrayList<Atraccion> generarListaAtracciones() {
		ArrayList<Atraccion> atracciones = new ArrayList<>();
		atracciones.add(new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE));
		atracciones.add(new Atraccion("La Comarca", 3, 6.5, 150, TipoAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE));
		atracciones.add(new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.PAISAJE));

		return atracciones;
	}
}

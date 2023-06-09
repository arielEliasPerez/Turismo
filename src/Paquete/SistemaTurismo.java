package Paquete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class SistemaTurismo {
	public ArrayList<Usuario> usuarios;
	private ArrayList<Atraccion> atracciones;
	private ArrayList<Promocion> promociones;
	private ArrayList<Compra> compras;

	private ArrayList<Componente> sugerenciasPaisaje;
	private ArrayList<Componente> sugerenciasAventura;
	private ArrayList<Componente> sugerenciasDegustacion;

	public SistemaTurismo() {
		this.atracciones = new ArrayList<>();
		this.promociones = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		this.compras = new ArrayList<>();
		
		this.sugerenciasAventura= new ArrayList<Componente>();
		this.sugerenciasPaisaje= new ArrayList<Componente>();
		this.sugerenciasDegustacion= new ArrayList<Componente>();
	}

	public void leerArchivos(String ruta) {
		LecturaArchivos archivo = new LecturaArchivos(ruta);
		
		usuarios = archivo.leerArchivoUsuarios();
		atracciones = archivo.leerArchivoAtracciones();
		promociones = archivo.leerArchPromociones();
	}
	/*public void generarListas() {
		Atraccion atr1 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		Atraccion atr2 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		Atraccion atr3 = new Atraccion("La Comarca", 3, 6.5, 150, TipoAtraccion.DEGUSTACION);
		Atraccion atr4 = new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA);
		Atraccion atr5 = new Atraccion("Abismo de Helm", 5, 2, 1, TipoAtraccion.PAISAJE);
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

		Usuario usuario1 = new Usuario("Julian Alvarez", 100, 9, TipoAtraccion.PAISAJE);
		Usuario usuario2 = new Usuario("Enzo Fernandez", 150, 50, TipoAtraccion.AVENTURA);
		Usuario usuario3 = new Usuario("Alexis McAlister", 5.5, 15, TipoAtraccion.PAISAJE);

		usuarios.add(usuario1);
		usuarios.add(usuario2);
		usuarios.add(usuario3);
	}*/

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

	public void generarListasSugerencias() {
		// Primera versión para probrar:
		ordenarSugerenciasPorTipo(TipoAtraccion.PAISAJE, sugerenciasPaisaje);
		ordenarSugerenciasPorTipo(TipoAtraccion.AVENTURA, sugerenciasAventura);
		ordenarSugerenciasPorTipo(TipoAtraccion.DEGUSTACION, sugerenciasDegustacion);

	}

	private void ordenarSugerenciasPorTipo(TipoAtraccion tipo, ArrayList<Componente> sugerencias) {
		ArrayList<Componente> buffer = new ArrayList<Componente>();

		conmutarTipoAlComienzo(tipo, sugerencias, promociones, buffer);
		conmutarTipoAlComienzo(tipo, sugerencias, atracciones, buffer);

		sugerencias.addAll(buffer);
	}

	private void conmutarTipoAlComienzo(TipoAtraccion tipo, ArrayList<Componente> sugerencias,
			ArrayList<? extends Componente> lista, ArrayList<Componente> buffer) {
		int i = 0;
		while (i < lista.size()) {
			if (lista.get(i).getTipoAtraccion() == tipo)
				sugerencias.add(lista.get(i));
			else
				buffer.add(lista.get(i));
			i++;
		}
	}

	public ArrayList<Atraccion> sugerirAlUsuario(Usuario usuario) {

		System.out.println("Nombre del visitante: " + usuario.getNombre());

		ArrayList<Atraccion> atraccionesAceptadas = iniciarSugerencias(usuario);

		return atraccionesAceptadas;
	}

	private ArrayList<Atraccion> iniciarSugerencias(Usuario usuario) {
		ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<>();
		Compra compra = new Compra(usuario);

		ArrayList<Componente> sugerencias = buscarListaSugerenciasSegunTipo(usuario.getAtraccionPreferida());

		for (Componente sugerencia : sugerencias) {

			if (usuario.getPresupuesto() == 0 || usuario.getTiempo() == 0)
				break;

			if (sePuedeSugerir(usuario, sugerencia, atraccionesAceptadas) == true) {

				mostrarDatosActulizadosUsuario(usuario);
				mostrarSugerencia(sugerencia);

				boolean respuesta = obtenerRespuesta();
				if (respuesta == true) {
					System.out.println("¡Aceptado!");
					compra = procesarCompra(compra, sugerencia, atraccionesAceptadas);
					this.compras.add(compra);
				}
			}
		}

		return atraccionesAceptadas;
	}

	ArrayList<Componente> buscarListaSugerenciasSegunTipo(TipoAtraccion tipo) {
		// primera version:
		switch (tipo) {
		case PAISAJE:
			return sugerenciasPaisaje;
		case DEGUSTACION:
			return sugerenciasDegustacion;
		default:
			return sugerenciasAventura;
		}
	}

	private void mostrarDatosActulizadosUsuario(Usuario usuario) {
		System.out.println("\n\n-----------------------------------------------------------------------");
		System.out.println("Presupuesto actual de " + usuario.getNombre() + ": $" + usuario.getPresupuesto());
		System.out.println("Tiempo disponible de " + usuario.getNombre() + ": " + usuario.getTiempo() + " hs");
		// System.out.println("*************************************************************");
	}

	private boolean sePuedeSugerir(Usuario usuario, Componente sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {
		boolean atraccionAceptada = esAtraccionAceptada(sugerencia, atraccionesAceptadas);

		return atraccionAceptada == false && usuario.getPresupuesto() >= sugerencia.getCosto()
				&& usuario.getTiempo() >= sugerencia.getTiempo() && sugerencia.sinCupo() == false;
	}

	private boolean esAtraccionAceptada(Componente sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {

		boolean atraccionAceptada = false;
			for (Atraccion atraccion : atraccionesAceptadas) {
				if (sugerencia.hayAtraccionAceptada(atraccion)) {
					atraccionAceptada = true;
					break;
				}
			}

		return atraccionAceptada;
	}

	private void mostrarSugerencia(Componente sugerencia) {
		System.out.println("*************************************************************");
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

	private Compra procesarCompra(Compra compra, Componente sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {
		guardarAtraccionesAceptadas(sugerencia, atraccionesAceptadas);
		compra.setUsuario(actualizarDatosUsuario(compra.getUsuario(), sugerencia));

		compra.addSugerenciaDiaria(sugerencia);
		compra.incrementarCostoTotal(sugerencia.getCosto());
		compra.incrementarTiempoTotal(sugerencia.getTiempo());

		sugerencia.decrementarCupo();

		return compra;
	}

	private void guardarAtraccionesAceptadas(Componente sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {
		if (sugerencia.getClass() == Atraccion.class)
			atraccionesAceptadas.add((Atraccion) sugerencia);
		else
			atraccionesAceptadas.addAll(((Promocion) sugerencia).getAtracciones());
	}

	private Usuario actualizarDatosUsuario(Usuario usuario, Componente sugerencia) {
		usuario.restarTiempo(sugerencia.getTiempo());
		usuario.restarPresupuesto(sugerencia.getCosto());

		return usuario;
	}

	public void generarItinerario(Usuario usuario, ArrayList<Atraccion> atraccionesAceptadas) {
		System.out.println("\n------------------------------------------------------------------");
		System.out.println("Itinerario de " + usuario.getNombre());
		for (Atraccion atraccion : atraccionesAceptadas) {
			System.out.println(
					"-- Atraccion: " + atraccion.getNombre() + "\tHoras de atracción: " + atraccion.getTiempo() + "hs");

		}
		System.out.println("\n------------------------------------------------------------------");
		Scanner entrada = new Scanner(System.in);
		entrada.nextLine();
	}

}

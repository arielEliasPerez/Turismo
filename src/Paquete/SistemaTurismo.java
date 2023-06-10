package Paquete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import procesoArchivos.EscrituraArchivo;
import procesoArchivos.LecturaArchivos;

public class SistemaTurismo {
	public ArrayList<Usuario> usuarios;
	private ArrayList<Atraccion> atracciones;
	private ArrayList<Promocion> promociones;

	private ArrayList<Componente> sugerenciasPaisaje;
	private ArrayList<Componente> sugerenciasAventura;
	private ArrayList<Componente> sugerenciasDegustacion;

	public SistemaTurismo() {
		this.atracciones = new ArrayList<>();
		this.promociones = new ArrayList<>();
		this.usuarios = new ArrayList<>();

		this.sugerenciasAventura = new ArrayList<Componente>();
		this.sugerenciasPaisaje = new ArrayList<Componente>();
		this.sugerenciasDegustacion = new ArrayList<Componente>();
	}

	public void leerArchivos(String ruta) {
		LecturaArchivos archivo = new LecturaArchivos(ruta);

		usuarios = archivo.leerArchivoUsuarios();
		atracciones = archivo.leerArchivoAtracciones();
		promociones = archivo.leerArchPromociones();
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

	public ArrayList<Atraccion> sugerirAlUsuario(Usuario usuario, Compra compra) {

		System.out.println("Nombre del visitante: " + usuario.getNombre());

		ArrayList<Atraccion> atraccionesAceptadas = iniciarSugerencias(usuario, compra);

		return atraccionesAceptadas;
	}

	private ArrayList<Atraccion> iniciarSugerencias(Usuario usuario, Compra compra) {
		ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<>();

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

	public void generarItinerario(Usuario usuario, Compra compra, ArrayList<Atraccion> atraccionesAceptadas) {

		System.out.println("\n------------------------------------------------------------------");
		System.out.println("Itinerario de " + usuario.getNombre());
		for (Atraccion atraccion : atraccionesAceptadas) {
			System.out.println(
					"-- Atraccion: " + atraccion.getNombre() + "\tHoras de atracción: " + atraccion.getTiempo() + "hs");

		}
		System.out.println("\nHoras Necesarias: " + compra.getTiempo() + " hs\tMonto necesario: $" + compra.getCosto());
		System.out.println("\n------------------------------------------------------------------");
		Scanner entrada = new Scanner(System.in);
		entrada.nextLine();
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public void generarArchivoDeCompras(String ruta, ArrayList<Compra> compras) {
		EscrituraArchivo archivo = new EscrituraArchivo(ruta);

		archivo.guardarArchivoCompras(compras);
	}

}

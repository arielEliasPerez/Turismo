package Paquete;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		SistemaTurismo sistemaTurismo = new SistemaTurismo();

		sistemaTurismo.leerArchivos("casos de prueba/in/");

		sistemaTurismo.ordenarListas();

		sistemaTurismo.generarListasSugerencias();

		ArrayList<Compra> compras = new ArrayList<>();

		for (Usuario usuario : sistemaTurismo.usuarios) {

			Compra compra = new Compra(usuario);

			ArrayList<Atraccion> atraccionesAceptadas = sistemaTurismo.sugerirAlUsuario(usuario, compra);

			sistemaTurismo.generarItinerario(usuario, compra, atraccionesAceptadas);

			compras.add(compra);
		}

		sistemaTurismo.generarArchivoDeCompras("casos de prueba/out/", compras);

	}

}

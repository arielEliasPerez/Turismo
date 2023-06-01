package Paquete;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		SistemaTurismo sistemaTurismo = new SistemaTurismo();

		sistemaTurismo.generarListas(); // leer listas desde Archivos

		sistemaTurismo.ordenarListas();

		for (Usuario usuario : sistemaTurismo.usuarios) {
			
			ArrayList<Atraccion> atraccionesAceptadas = sistemaTurismo.sugerirAlUsuario(usuario);
			sistemaTurismo.generarItinerario(usuario, atraccionesAceptadas);

		}
		
		//generarArchivoDeCompras

	}

}

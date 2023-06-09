package Paquete;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		SistemaTurismo sistemaTurismo = new SistemaTurismo();
		
		sistemaTurismo.leerArchivos("casos de prueba/in/");
		//sistemaTurismo.generarListas();

		sistemaTurismo.ordenarListas();
		
		sistemaTurismo.generarListasSugerencias();
		
		for (Usuario usuario : sistemaTurismo.usuarios) {
			
			ArrayList<Atraccion> atraccionesAceptadas = sistemaTurismo.sugerirAlUsuario(usuario);
			sistemaTurismo.generarItinerario(usuario, atraccionesAceptadas);
			
		}
		
		//generarArchivoDeCompras

	}

}

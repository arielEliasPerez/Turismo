package procesoArchivos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Paquete.Compra;

public class EscrituraArchivo {
	private String ruta;

	public EscrituraArchivo(String ruta) {
		this.ruta = ruta;
	}

	public void guardarArchivoCompras( ArrayList<Compra> compras) {
		
		FileWriter file = null; 
		PrintWriter escritura; 
	
		try{
			file = new FileWriter( this.ruta + "compras.out" );
			escritura = new PrintWriter(file); //Referenciamos enviando el obj file donde queremos escribir.
			
			//Ahora sí, escrimos dato por dato:
			for(Compra compra : compras) {
				escritura.println(compra);
			}
		} catch( Exception e) {
			e.printStackTrace();
		} finally {
			if( file!= null ) {
				try {
					file.close();
				} catch( IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Archivo de Compras procesado");
	}
}

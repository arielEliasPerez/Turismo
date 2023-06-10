package procesoArchivos;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Paquete.Atraccion;
import Paquete.Promocion;
import Paquete.PromocionAbsoluta;
import Paquete.PromocionAxB;
import Paquete.PromocionPorcentual;
import Paquete.TipoAtraccion;
import Paquete.Usuario;
import excepciones.ExceptionTipoAtraccion;
import excepciones.ExceptionTipoPromocion;

public class LecturaArchivos {
	private String ruta;
	private HashMap<String, Atraccion> mapaAtracciones;

	// Constructor
	public LecturaArchivos(String ruta) {
		this.ruta = ruta;
		mapaAtracciones = new HashMap<>();
	}

	public ArrayList<Atraccion> leerArchivoAtracciones() {
		Scanner scanner = null;

		String nombre;
		double costo;
		double tiempo;
		int cupo;
		TipoAtraccion tipo;

		try {
			File file = new File(this.ruta + "atracciones.in");
			scanner = new Scanner(file);

			while (scanner.hasNext()) {

				nombre = scanner.nextLine();
				costo = Double.parseDouble(scanner.nextLine());
				tiempo = Double.parseDouble(scanner.nextLine());
				cupo = Integer.parseInt(scanner.nextLine());
				try {
					tipo = verificarTipoAtraccion(scanner.nextLine());
					Atraccion atraccion = new Atraccion(nombre, costo, tiempo, cupo, tipo);
					mapaAtracciones.put(nombre, atraccion);
				} catch (ExceptionTipoAtraccion e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrar el archivo, eso es mucho muy importante
			scanner.close();
		}

		return new ArrayList<>(mapaAtracciones.values());
	}

	public ArrayList<Promocion> leerArchPromociones() {
		Scanner scanner = null;
		ArrayList<Promocion> listaPromociones = new ArrayList<>();
		String nombre;
		int idPromo;
		TipoAtraccion tipoAtraccion;

		try {
			File file = new File(this.ruta + "promociones.in");
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				nombre = scanner.nextLine();
				try {
					tipoAtraccion = verificarTipoAtraccion(scanner.nextLine());
					idPromo = Integer.parseInt(scanner.nextLine());
					String[] atraccionesArray = scanner.nextLine().split(",");

					ArrayList<Atraccion> atracciones = new ArrayList<>();
					
					for (String atraccion : atraccionesArray) {
						atracciones.add(mapaAtracciones.get(atraccion));
					}

					Promocion promo;

					switch (idPromo) {
					case 1:
						int descuento = Integer.parseInt(scanner.nextLine());
						promo = new PromocionPorcentual(nombre, tipoAtraccion, atracciones, idPromo, descuento);
						break;
					case 2:
						double precioFinal = Double.parseDouble(scanner.nextLine());
						promo = new PromocionAbsoluta(nombre, tipoAtraccion, atracciones, idPromo, precioFinal);
						break;
					case 3:
						String nombreAtrFree = scanner.nextLine();
						Atraccion atrFree = mapaAtracciones.get(nombreAtrFree);
						promo = new PromocionAxB(nombre, tipoAtraccion, atracciones, idPromo, atrFree);
						break;
					default:
						throw new ExceptionTipoPromocion("Tipo de Promocion invalido!!!");
					}
				
					listaPromociones.add(promo);
					
				} catch(ExceptionTipoAtraccion e) {
					e.printStackTrace();
					scanner.nextLine();
					scanner.nextLine();
					scanner.nextLine();
				}catch(ExceptionTipoPromocion e) {
					e.printStackTrace();
					scanner.nextLine();
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrar el archivo, eso es mucho muy importante
			scanner.close();
		}

		return listaPromociones;
	}

	public ArrayList<Usuario> leerArchivoUsuarios() {
		Scanner scanner = null;
		ArrayList<Usuario> usuarios = new ArrayList<>();

		String nombre;
		double presupuesto;
		double tiempoDisponible;
		TipoAtraccion atraccionPref;

		try {
			File file = new File(this.ruta + "usuarios.in");
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				nombre = scanner.nextLine();
				presupuesto = Double.parseDouble(scanner.nextLine());
				tiempoDisponible = Double.parseDouble(scanner.nextLine());
				try {
					atraccionPref = verificarTipoAtraccion(scanner.nextLine());

					Usuario usuario = new Usuario(nombre, presupuesto, tiempoDisponible, atraccionPref);
					usuarios.add(usuario);
				}catch(ExceptionTipoAtraccion e) {
					e.printStackTrace();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrar el archivo, eso es mucho muy importante
			scanner.close();
		}

		return usuarios;
	}

	private TipoAtraccion verificarTipoAtraccion(String tipo) throws ExceptionTipoAtraccion {
		TipoAtraccion tipoAtraccion;

		switch(tipo) {
		case "Paisaje":
			tipoAtraccion = TipoAtraccion.PAISAJE;
			break;
		case "Aventura":
			tipoAtraccion = TipoAtraccion.AVENTURA;
			break;
		case "Degustacion":
			tipoAtraccion = TipoAtraccion.DEGUSTACION;
			break;
		default:
			throw new ExceptionTipoAtraccion("Tipo de Atraccion inválido");
		}

		return tipoAtraccion;
	}
	

}

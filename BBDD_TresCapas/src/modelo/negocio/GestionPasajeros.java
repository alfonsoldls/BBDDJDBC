package modelo.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.intefaces.DaoPasajero;
import modelo.ppersistencia.DaoCocheMySql;
import modelo.ppersistencia.DaoPasajeroMySql;

public class GestionPasajeros {

	private DaoPasajero daoPasajero = new DaoPasajeroMySql();

	public int alta(Pasajero p) {
		boolean alta = daoPasajero.alta(p);
		if (alta) {
			return 0;
		} else {
			return 1;
		}
	}

	public boolean baja(int id) {
		boolean baja = daoPasajero.baja(id);
		return baja;
	}

	public Pasajero obtener(int id) {
		Pasajero pasajero = daoPasajero.obtener(id);
		return pasajero;
	}

	public List<Pasajero> listar() {
		List<Pasajero> listaPasajeros = daoPasajero.listar();
		return listaPasajeros;
	}

	public void añadirPasajeroACoche(int idPasajero, int idCoche) {
		// Verificar si el coche y el pasajero existen
			//añadirPasajero();
	}

	public boolean eliminarPasajeroDeCoche(int idPasajero, int idCoche) {
		// Verificar si el coche y el pasajero existen
		boolean baja = daoPasajero.baja(idPasajero);
		return baja;
	}

	public List<Pasajero> listarPasajerosDeCoche(int idCoche) {
		// Obtener la lista de pasajeros asociados al coche
		List<Pasajero> listaPasajeros = daoPasajero.listar();
		return listaPasajeros;
	}

	public void menuPasajeros() {
		Scanner sc = new Scanner(System.in);
		String nombre;
		int id, edad;
		float peso;
		Pasajero p = new Pasajero();
		GestionPasajeros gp = new GestionPasajeros();
		DaoCocheMySql daoCocheMySql = new DaoCocheMySql();
		DaoPasajeroMySql daoPasajeroMySql = new DaoPasajeroMySql();
		Coche c = new Coche();
		List<Coche> listaCoches = new ArrayList<>();
		
		
		int opcion = 0;

		while (opcion != 6) {
			System.out.println("Teclea una opción del 1 al 7, 8 para salir");
			System.out.println("1.- Crear nuevo pasajero");
			System.out.println("2.- Borrar pasajero por id");
			System.out.println("3.- Consulta pasajero por id");
			System.out.println("4.- Listar todos los pasajeros");
			System.out.println("5.- Añadir pasajero a coche");
			System.out.println("6.- Eliminar pasajero de un coche");
			System.out.println("7.- Listar todos los pasajeros de un coche");
			System.out.println("8.- Salir");

			opcion = sc.nextInt();

			while (opcion < 1 || opcion > 8) {
				System.out.println("del 1 al 8");
				opcion = sc.nextInt();
			}

			Scanner leer = new Scanner(System.in);
			switch (opcion) {
			
			case 1:
				System.out.println("Introduzca los datos del pasajero (id/nombre/edad/peso)");
				id = sc.nextInt();
				nombre = sc.next();
				edad = sc.nextInt();
				peso = sc.nextFloat();

				p = new Pasajero();
				p.setId(id);
				p.setNombre(nombre);
				p.setEdad(edad);
				p.setPeso(peso);

				int alta = gp.alta(p);

				if (alta == 0) {
					System.out.println("Pasajero dado de alta");
				} else if (alta == 1) {
					System.out.println("Error de conexión con la BBDD");
				}
				break;

			case 2:
				System.out.println("Introduce el ID del pasajero a borrar:");
				int idBaja = sc.nextInt();
				boolean baja = gp.baja(idBaja);
				if (baja == true) {
					System.out.println("El pasajero ha sido borrado");
				} else {
					System.out.println("El pasajero no ha podido ser borrado");
				}
				break;

			case 3:
				System.out.println("Introduce el ID del pasajero a consultar:");
				id = sc.nextInt();
				Pasajero obtenido = gp.obtener(id);
				if (obtenido != null) {
					System.out.println(p);
				} else {
					System.out.println("No se ha encontrado ningun pasajero con ese ID");
				}
				break;

			case 4:
				List<Pasajero> listaPasajeros = gp.listar();
				if (!listaPasajeros.isEmpty()) {
					System.out.println("Lista de pasajeros: ");
					for (Pasajero pasajero : listaPasajeros) {
						System.out.println(pasajero);
					}
				} else {
					System.out.println("No hay pasajeros en la base de datos");
				}

				break;
				
				
			case 5: 
				System.out.println("Imprimiendo la lista de coches: " + listaCoches);
				System.out.println("Introduce el ID del coche que quieres elegir");
				int idCoche = sc.nextInt();
				System.out.println("Introduce el ID del pasajero");
				int idPasajero = sc.nextInt();
				c = daoCocheMySql.obtener(idCoche);
				p = daoPasajero.obtener(idPasajero);

				if (c != null && p != null) {
					// Intentar agregar el pasajero al coche en la base de datos
					try {
						daoCocheMySql.añadirPasajero(idCoche, idPasajero);
						System.out.println("Pasajero añadido al coche");
					} catch (Exception e) {
						System.out.println("Error al agregar pasajero al coche: " + e.getMessage());
					}
				} else {
					System.out.println("El coche o el pasajero especificados no existen.");
				}
			
				break;
				
				
			case 6:
				System.out.println("Introduce el ID del pasajero que quieres eliminar");
				daoPasajeroMySql = new DaoPasajeroMySql();
				idCoche = sc.nextInt();
				c = daoCocheMySql.obtener(idCoche);
				idPasajero = sc.nextInt();
				p = daoPasajeroMySql.obtener(idPasajero);

				if (c != null && p != null) {
					// Intentar eliminar el pasajero del coche en la base de datos
					try {
						if (daoPasajeroMySql.baja(idPasajero)) {
							System.out.println("Pasajero eliminado del coche");
						} else {
							System.out.println("No se pudo eliminar el pasajero del coche");
						}
					} catch (Exception e) {
						System.out.println("Error al eliminar pasajero del coche: " + e.getMessage());
					}
				} else {
					System.out.println("El coche o el pasajero especificados no existen.");
				}
				break;
				
				
			case 7:
				System.out.println("Introduce el ID del coche que quieres consultar");
				daoCocheMySql = new DaoCocheMySql();
				idCoche = sc.nextInt();
				List<Pasajero> pasajeros = daoCocheMySql.getPasajeros(idCoche);

				if (!pasajeros.isEmpty()) {
					System.out.println("Pasajeros del coche:");
					for (Pasajero pasajero : pasajeros) {
						System.out.println(pasajero.toString());
					}
				} else {
					System.out.println("No hay pasajeros asociados a este coche");
				}
				break;
				
				
			case 8:
				System.out.println("Fin del programa");
				break;
			}
		}
		
	}

		
}

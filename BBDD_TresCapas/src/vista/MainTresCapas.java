package vista;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestionPasajeros;
import modelo.negocio.gestionCoches;

public class MainTresCapas {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		gestionCoches gc = new gestionCoches();
		String marca, modelo;
		int añoFab;
		float kms;
		int id;
		Coche c = new Coche();
		GestionPasajeros gp = new GestionPasajeros();

		int opcion = 0;

		while (opcion != 6) {
			System.out.println("Teclea una opción del 1 al 5, 6 para salir");
			System.out.println("1.- Añadir coche");
			System.out.println("2.- Borrar coche por id");
			System.out.println("3.- Consultar coche por id");
			System.out.println("4.- Modificar coche por id");
			System.out.println("5.- Listar coches");
			System.out.println("6.- Gestión de pasajeros");
			System.out.println("7.- Salir");

			opcion = sc.nextInt();

			while (opcion < 1 || opcion > 6) {
				System.out.println("del 1 al 6");
				opcion = sc.nextInt();
			}

			Scanner leer = new Scanner(System.in);
			switch (opcion) {

			case 1:
				System.out.println("Introduzca los datos del coche (marca/modelo/añoFabricacion/kms)");
				marca = sc.next();
				modelo = sc.next();
				añoFab = sc.nextInt();
				kms = sc.nextFloat();

				c = new Coche();
				c.setMarca(marca);
				c.setModelo(modelo);
				c.setAñoFab(añoFab);
				c.setKms(kms);

				int alta = gc.alta(c);

				if (alta == 0) {
					System.out.println("Coche dado de alta");
				} else if (alta == 1) {
					System.out.println("Error de conexión con la BBDD");
				} else if (alta == 2) {
					System.out.println("Los campos marca o modelo son igual a Null");
				}
				break;

			case 2:
				System.out.println("Introduce el ID del coche a borrar:");
				int idBaja = sc.nextInt();
				boolean baja = gc.baja(idBaja);
				if (baja == true) {
					System.out.println("El coche ha sido borrado");
				} else {
					System.out.println("El coche no ha podido ser borrado");
				}
				break;

			case 3:
				System.out.println("Introduce el ID del coche a consultar:");
				id = sc.nextInt();
				Coche obtenido = gc.obtener(id);
				if (obtenido != null) {
					System.out.println(c);
				} else {
					System.out.println("No se ha encontrado ningun coche con ese ID");
				}
				break;

			case 4:
				System.out.println("Introduce el ID del coche a modificar:");
				id = sc.nextInt();
				System.out.println("Introduzca los nuevos datos del coche (marca/modelo/añoFabricacion/kms)");
				marca = sc.next();
				modelo = sc.next();
				añoFab = sc.nextInt();
				kms = sc.nextFloat();

				c = new Coche();
				c.setMarca(marca);
				c.setModelo(modelo);
				c.setAñoFab(añoFab);
				c.setKms(kms);

				int modificar = gc.modificar(c);
				if (modificar == 0) {
					System.out.println("El coche ha sido modificado con éxito");
				} else if (modificar == 1) {
					System.out.println("Error de conexión con la BBDD");
				} else if (modificar == 2) {
					System.out.println("Los campos marca o modelo son igual a Null");
				}
				break;

			case 5:
				List<Coche> listaCoches = gc.listar();
				if (!listaCoches.isEmpty()) {
					System.out.println("Lista de coches: ");
					for (Coche coche : listaCoches) {
						System.out.println(coche);
					}
				} else {
					System.out.println("No hay coches en la base de datos");
				}

				break;

			case 6:
				gp.menuPasajeros();
				break;

			case 7:
				System.out.println("Fin del programa");

			}

		}
	}

	public void pintarMenu() {

	}
}

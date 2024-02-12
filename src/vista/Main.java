package vista;

import java.util.Scanner;


import conexion.ConexionMySql;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	public static GestorCoche gc;
	public static GestorPasajero gp;
	
	
	public static void main(String[] args) {
		gc = new GestorCoche();
		gp = new GestorPasajero();
		
		boolean salir = false;
		String opc;
		
		while(!salir) {
			mostrarMenuPrincipal();
			opc = sc.nextLine();
			switch(opc) {
				case "1": 
					añadirCoche();
					break;
				case "2":
					borrarCoche();
					break;
				case "3":
					consultarCoche();
					break;
				case "4": 
					modificarCoche();
					break;
				case "5":
					for(Coche c : gc.listarCoches())
						System.out.println(c);
					break;
				case "6":
					gestionPasajeros();
					break;
				case "7":
					salir = true;
					break;
				default: 
					System.out.println("Introduzca una opcion valida");
				
			} 	
					
		}
		System.out.println("cerrando...");
	}

	private static void mostrarMenuPrincipal() {
			System.out.println("-------MENÚ-------\n\n");
			System.out.println("1. Añadir nuevo coche");
			System.out.println("2. Borrar coche por Id");
			System.out.println("3. Consulta coche por id");
			System.out.println("4. Modificar coche por id");
			System.out.println("5. Listado de coches");
			System.out.println("6. Gestion de pasajeros");
			System.out.println("7. Terminar el programa\n");
	}
	
	private static void mostrarMenuGestionPasajeros() {
		System.out.println("-------GESTION DE PASAJEROS-------\n\n");
		System.out.println("1. Crear nuevo pasajero");
		System.out.println("2. Borrar pasajero por Id");
		System.out.println("3. Consulta pasajero por id");
		System.out.println("4. Listar todos los pasajeros");
		System.out.println("5. Añadir pasajero a coche");
		System.out.println("6. Eliminar pasajero de un coche");
		System.out.println("7. Listar pasajeros de un coche\n");
	}
	
	private static void añadirCoche() {
		Coche c = new Coche();
		System.out.println("Introduce la marca del coche");
		c.setMarca(sc.nextLine());
		System.out.println("Introduce el modelo del coche");
		c.setModelo(sc.nextLine());
		System.out.println("Introduce el año de fabricacion del coche");
		c.setAnioFabricacion(Integer.parseInt(sc.nextLine()));
		System.out.println("Introduce los km del coche");
		c.setKm(Double.parseDouble(sc.nextLine()));
		
		if(gc.altaCoche(c)== 1)
			System.out.println("Se ha dado de alta correctamente");
		else if (gc.altaCoche(c)== 0)
			System.out.println("No se ha podido dar de alta");
		else if (gc.altaCoche(c)== -1)
			System.out.println("No se ha podido dar de alta ya que los campos modelo o marca están vacíos");
	}
	
	private static void borrarCoche() {
		System.out.println("Introduce el ID del coche");
		if(gc.bajaCoche(Integer.parseInt(sc.nextLine())))
			System.out.println("El coche se ha dado de baja correctamente");
		else
			System.out.println("No se ha podido dar de baja el coche");
	}
	
	private static void consultarCoche() {
		System.out.println("Introduce el ID del coche");
		Coche c = gc.obtenerCoche(Integer.parseInt(sc.nextLine()));
		if(c != null)
			System.out.println(c);
		else 
			System.out.println("No se ha podido obtener el coche o no existe");
	}
	
	private static void modificarCoche() {
		Coche c = new Coche();
		System.out.println("Introduce el ID del coche a modificar");
		c.setId(Integer.parseInt(sc.nextLine()));
		System.out.println("Introduce la marca del coche");
		c.setMarca(sc.nextLine());
		System.out.println("Introduce el modelo del coche");
		c.setModelo(sc.nextLine());
		System.out.println("Introduce el año de fabricacion del coche");
		c.setAnioFabricacion(Integer.parseInt(sc.nextLine()));
		System.out.println("Introduce los km del coche");
		c.setKm(Double.parseDouble(sc.nextLine()));
		
		if(gc.modificarCoche(c)== 1)
			System.out.println("El coche se ha modificado correctamente");
		else if (gc.modificarCoche(c)== 0)
			System.out.println("No se ha podido modificar el coche o no existe");
		else if (gc.modificarCoche(c)== -1)
			System.out.println("No se ha podido modificar ya que los campos modelo o marca están vacíos");
		
	}
	
	
	
	private static void gestionPasajeros() {
		mostrarMenuGestionPasajeros();
		String opc = sc.nextLine();
		switch (opc) {
			case "1":
				añadirPasajero();
				break;
			case "2":
				borrarPasajero();
				break;
			case "3":
				consultarPasajero();
				break;
			case "4":
				for(Pasajero p : gp.listarPasajeros())
					System.out.println(p);;
				break;
			case "5":
				System.out.println("---Lista de Coches sin Pasajeros---");
				for (Coche c : gp.CochesSinPasajeros())
					System.out.println(c);
				añadirPasajeroACoche();
				break;
			case "6":
				borrarPasajeroACoche();
				break;
			case "7":
				listarPasajerosCoche();
				break;
		}
		
	}
	
	private static void añadirPasajero() {
		Pasajero p = new Pasajero();
		System.out.println("Introduce el nombre");
		p.setNombre(sc.nextLine());
		System.out.println("Introduce la edad");
		p.setEdad(Integer.parseInt(sc.nextLine()));
		System.out.println("Introduce el peso");
		p.setPeso(Float.parseFloat(sc.nextLine()));
		
		if(gp.altaPasajero(p))
			System.out.println("El pasajero se ha dado de baja correctamente");
		else
			System.out.println("No se ha podido dar de alta");
	}
	
	
	private static void borrarPasajero() {
		System.out.println("Introduce el ID del pasajero");
		if(gp.bajaPasajero(Integer.parseInt(sc.nextLine())))
			System.out.println("El pasajero se ha dado de baja correctamente");
		else
			System.out.println("No se ha podido dar de baja");
	}
	
	private static void consultarPasajero() {
		System.out.println("Introduce el ID del pasajero");
		Pasajero p = gp.consultarPasajero(Integer.parseInt(sc.nextLine()));
		if(p != null)
			System.out.println(p);
		else
			System.out.println("No se ha encontrado el pasajero o no existe");
	}
	
	private static void añadirPasajeroACoche() {
		
		System.out.println("Introduce el ID del pasajero");
		int idPasajero = Integer.parseInt(sc.nextLine());
		System.out.println("Introduce el ID del coche");
		int idCoche = Integer.parseInt(sc.nextLine());
		if(gp.altaPasajeroACoche(idCoche, idPasajero))
			System.out.println("Añadido pasajero a coche");
		else
			System.out.println("No se ha podido agregar el pasajero al coche");
	}
	
	private static void borrarPasajeroACoche() {
		
		System.out.println("Introduce el ID del pasajero");
		int idPasajero = Integer.parseInt(sc.nextLine());
		System.out.println("Introduce el ID del coche");
		int idCoche = Integer.parseInt(sc.nextLine());
		if(gp.bajaPasajeroACoche(idCoche, idPasajero))
			System.out.println("Se ha dado de baja al pasajero");
		else
			System.out.println("No se ha podido dar de baja");
	}
	
	private static void listarPasajerosCoche() {
		System.out.println("Introduce el ID del coche");
		int idCoche = Integer.parseInt(sc.nextLine());
		for(Pasajero p : gp.pasajerosCoche(idCoche))
			System.out.println(p);
	}
	
	
	
}

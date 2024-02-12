package test;
import java.util.*;
import modelo.entidad.*;
import persistencia.*;
import persistencia.interfaces.*;

public class TestGestionCoches {

	public static void main(String[] args) {
		 	int opcionMenu, opcionSubmenu;
	        Scanner sc = new Scanner(System.in);
	        
	        do {
	            menu();
	            opcionMenu = sc.nextInt();
	            
	            switch(opcionMenu) {
	            
	            case 1:
	            	System.out.println("1. Añadir nuevo coche");
	            	nuevoCoche();
	                break;
	            
	            case 2:
	            	System.out.println("2. Borrar coche por ID");
	            	bajaCoche();
	                break;
	            
	            case 3:
	            	System.out.println("3. Consulta coche por ID");
	            	obtenerCoche();
	                break;
	            
	            case 4:
	            	System.out.println("4. Modificar coche por ID");
	            	modificarCoche();
	                break;  
	                
	            case 5:
	            	System.out.println("5. Listado de coches");
	            	listarCoches();
	                break;
	                
	            case 6:
	            	System.out.println("6. Gestión de pasajeros");
	            	do {
	    	            submenu();
	    	            opcionSubmenu = sc.nextInt();
	    	            
	    	            switch(opcionSubmenu) {
	    	            
	    	            case 1:
	    	            	System.out.println("1. Crear nuevo pasajero");
	    	            	nuevoPasajero();
	    	                break;
	    	            
	    	            case 2:
	    	            	System.out.println("2. Borrar pasajero por ID");
	    	            	bajaPasajero();
	    	                break;
	    	            
	    	            case 3:
	    	            	System.out.println("3. Consulta pasajero por ID");
	    	            	obtenerPasajero();
	    	                break;
	    	            
	    	            case 4:
	    	            	System.out.println("4. Listar todos los pasajeros");
	    	            	listarPasajeros();
	    	                break;  
	    	                
	    	            case 5:
	    	            	System.out.println("5. Añadir pasajero a coche");
	    	            	pasajeroACoche();
	    	                break;
	    	                
	    	            case 6:
	    	            	System.out.println("6. Eliminar pasajero de un coche");
	    	            	bajaPasajeroDeCoche();
	    	                break;    
	    	            
	    	            case 7:
	    	                System.out.println("7. Listar todos los pasajeros de un coche");
	    	                listarPasajerosDeCoche();
	    	                break;
	    	            
	    	            case 8:
	    	                System.out.println("8. Volver al menú principal");
	    	                break;    
	    	                
	    	            default:
	    	                System.out.println("Opción no válida");
	    	                break;  
	    	            }
	    	            
	    	        } while(opcionSubmenu != 8);
	                break;    
	            
	            case 7:
	                System.out.println("Saliendo del programa...");
	                break;
	                
	            default:
	                System.out.println("Opción no válida");
	                break;  
	            }
	            
	        } while(opcionMenu != 7);
	        
	        System.out.println("Programa finalizado.");
	        System.exit(0);

	}
	
	public static void menu() {
		System.out.println("");
		System.out.println("-------MENÚ-------");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("");
		System.out.println("1. Añadir nuevo coche");
		System.out.println("2. Borrar coche por ID");
		System.out.println("3. Consulta coche por ID");
		System.out.println("4. Modificar coche por ID");
		System.out.println("5. Listado de coches");
		System.out.println("6. Gestión de pasajeros");
		System.out.println("7. Terminar el programa");
		System.out.println("");
	}
	
	public static void submenu() {
		System.out.println("");
		System.out.println("------GESTIÓN DE PASAJEROS------");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("");
		System.out.println("1. Crear nuevo pasajero");
		System.out.println("2. Borrar pasajero por ID");
		System.out.println("3. Consulta pasajero por ID");
		System.out.println("4. Listar todos los pasajeros");
		System.out.println("5. Añadir pasajero a coche");
		System.out.println("6. Eliminar pasajero de un coche");
		System.out.println("7. Listar todos los pasajeros de un coche");
		System.out.println("8. Volver al menú principal");
		System.out.println("");
	}
	
	public static void nuevoCoche() {
		Scanner sc = new Scanner(System.in);
		String marca = "";
		String modelo = "";
		int prod, km = 0;
		Coche coche = new Coche();
		
	    while(marca.equalsIgnoreCase("")) {
	        System.out.println("Indique la marca del nuevo coche:");
	        marca = sc.nextLine();
	        if(marca.isEmpty()) {
	            System.out.println("La marca no puede estar vacía. Por favor, inténtelo de nuevo.");
	        }
	    }

	    while(modelo.equalsIgnoreCase("")) {
	        System.out.println("Indique el modelo del nuevo coche:");
	        modelo = sc.nextLine();
	        if(modelo.isEmpty()) {
	            System.out.println("El modelo no puede estar vacío. Por favor, inténtelo de nuevo.");
	        }
	    }
	    
		System.out.println("Indique el año de producción del nuevo coche:");
		prod = Integer.parseInt(sc.nextLine());
		System.out.println("Indique los kilómetros del nuevo coche:");
		km = Integer.parseInt(sc.nextLine());
		
		coche.setMarca(marca);
		coche.setModelo(modelo);
		coche.setYearProduccion(prod);
		coche.setKm(km);
		DaoCoche dc = new DaoCocheMySql();
		boolean alta = dc.alta(coche);
		
		if(alta){
			System.out.println("El coche se ha dado de alta correctamente");
		}else{
			System.out.println("El coche no se ha podido dar de alta");
		}
		
	}
	
	public static void bajaCoche() {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		System.out.println("Indique el ID del coche que quiere dar de baja:");
		id = Integer.parseInt(sc.nextLine());
		DaoCoche dc = new DaoCocheMySql();
		boolean baja = dc.baja(id);
		
		if(baja){
			System.out.println("El coche se ha dado de baja");
		}else{
			System.out.println("El coche no se ha podido dar de baja");
		}
	}
	
	public static void modificarCoche() {
		Scanner sc = new Scanner(System.in);
		String marca = "";
		String modelo = "";
		int id, prod, km = 0;
		Coche coche = new Coche();
		
		System.out.println("Indique el ID del coche que desea modificar:");
		id = Integer.parseInt(sc.nextLine());
		
	    while(marca.equalsIgnoreCase("")) {
	        System.out.println("Indique la marca del nuevo coche:");
	        marca = sc.nextLine();
	        if(marca.isEmpty()) {
	            System.out.println("La marca no puede estar vacía. Por favor, inténtelo de nuevo.");
	        }
	    }

	    while(modelo.equalsIgnoreCase("")) {
	        System.out.println("Indique el modelo del nuevo coche:");
	        modelo = sc.nextLine();
	        if(modelo.isEmpty()) {
	            System.out.println("El modelo no puede estar vacío. Por favor, inténtelo de nuevo.");
	        }
	    }
	    
		System.out.println("Indique el año de producción del nuevo coche:");
		prod = Integer.parseInt(sc.nextLine());
		System.out.println("Indique los kilómetros del nuevo coche:");
		km = Integer.parseInt(sc.nextLine());
		
		coche.setId(id);
		coche.setMarca(marca);
		coche.setModelo(modelo);
		coche.setYearProduccion(prod);
		coche.setKm(km);
		DaoCoche dc = new DaoCocheMySql();
		boolean modificar = dc.modificar(coche);
		
		if(modificar){
			System.out.println("El coche se ha modificado");
		}else{
			System.out.println("El coche no se ha podido modificar");
		}
	}
	
	public static void obtenerCoche() {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		System.out.println("Indique el ID del coche que desea obtener:");
		id = Integer.parseInt(sc.nextLine());
		DaoCoche dc = new DaoCocheMySql();
		Coche coche = dc.obtener(id);
		System.out.println(coche);
	}
	
	public static void listarCoches() {
		DaoCoche dc = new DaoCocheMySql();
		List<Coche> listaCoches = dc.listar();
		for(Coche c : listaCoches){
			System.out.println(c);
		}
	}
	
	public static void nuevoPasajero() {
		Scanner sc = new Scanner(System.in);
		String nombre = "";
		int edad = 0;
		double peso = 0;
		Pasajero pasajero = new Pasajero();
		
		System.out.println("Indique el nombre del nuevo pasajero:");
		nombre = sc.nextLine();
		System.out.println("Indique la edad del nuevo pasajero:");
		edad = Integer.parseInt(sc.nextLine());
		System.out.println("Indique el peso del nuevo pasajero:");
		peso = Double.parseDouble(sc.nextLine());
		
		pasajero.setNombre(nombre);
		pasajero.setEdad(edad);
		pasajero.setPeso(peso);

		DaoPasajero dp = new DaoPasajeroMySql();
		boolean alta = dp.alta(pasajero);
		
		if(alta){
			System.out.println("El pasajero se ha dado de alta correctamente");
		}else{
			System.out.println("El pasajero no se ha podido dar de alta");
		}
		
	}
	
	public static void bajaPasajero() {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		System.out.println("Indique el ID del pasajero que quiere dar de baja:");
		id = Integer.parseInt(sc.nextLine());
		DaoPasajero dp = new DaoPasajeroMySql();
		boolean baja = dp.baja(id);
		
		if(baja){
			System.out.println("El pasajero se ha dado de baja");
		}else{
			System.out.println("El pasajero no se ha podido dar de baja");
		}
	}
	
	public static void obtenerPasajero() {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		System.out.println("Indique el ID del pasajero que desea obtener:");
		id = Integer.parseInt(sc.nextLine());
		DaoPasajero dp = new DaoPasajeroMySql();
		Pasajero pasajero = dp.obtener(id);
		System.out.println(pasajero);
	}
	
	public static void listarPasajeros() {
		DaoPasajero dp = new DaoPasajeroMySql();
		List<Pasajero> listaPasajeros = dp.listar();
		for(Pasajero p : listaPasajeros){
			System.out.println(p);
		}
	}
	
	public static void pasajeroACoche() {
		Scanner sc = new Scanner(System.in);
		int idPasajero, idCoche = 0;
		System.out.println("Indique el ID del pasajero:");
		idPasajero = Integer.parseInt(sc.nextLine());
		System.out.println("Indique el ID del coche:");
		idCoche = Integer.parseInt(sc.nextLine());
		
		DaoPasajero dp = new DaoPasajeroMySql();
		
		boolean agregar = dp.pasajeroACoche(idPasajero, idCoche);
		
		if(agregar){
			System.out.println("El pasajero se ha agregado al coche");
		}else{
			System.out.println("El pasajero no se ha podido agregar al coche");
		}
	}
	
	public static void bajaPasajeroDeCoche() {
		Scanner sc = new Scanner(System.in);
		int idPasajero = 0;
		System.out.println("Indique el ID del pasajero:");
		idPasajero = Integer.parseInt(sc.nextLine());
		
		DaoPasajero dp = new DaoPasajeroMySql();
		
		boolean eliminar = dp.bajaPasajeroCoche(idPasajero);
		
		if(eliminar){
			System.out.println("El pasajero se ha eliminado del coche");
		}else{
			System.out.println("El pasajero no se ha podido eliminar del coche");
		}
	}
	
	public static void listarPasajerosDeCoche() {
		Scanner sc = new Scanner(System.in);
		int idCoche = 0;
		System.out.println("Indique el ID del coche:");
		idCoche = Integer.parseInt(sc.nextLine());
		
		DaoPasajero dp = new DaoPasajeroMySql();
		List<Pasajero> listaPasajeros = dp.listarPasajerosCoche(idCoche);
		for(Pasajero p : listaPasajeros){
			System.out.println(p);
		}
	}

}

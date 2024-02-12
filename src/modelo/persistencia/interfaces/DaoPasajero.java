package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

public interface DaoPasajero {

	boolean altaPasajero(Pasajero p);
	boolean bajaPasajero (int id);
	Pasajero consultaPasajero (int id);
	List<Pasajero>listarPasajeros(); 
	boolean altaPasajeroACoche(int idPasajero, int idCoche);
	boolean bajaPasajeroACoche(int idPasajero, int idCoche);
	List<Pasajero> listarPasajerosCoche(int idCoche);
	List<Coche> CochesSinPasajeros();
}

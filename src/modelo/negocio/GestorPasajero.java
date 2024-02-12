package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoPasajero;

public class GestorPasajero {
	
	private DaoPasajero daoPasajero = new DaoPasajeroMySql();
	
	/**
	 * Metodo que da de alta a un pasajero
	 * @param p Pasajero a dar de alta
	 * @return true si se ha dado de alta , false si no se ha dado de alta
	 */
	public boolean altaPasajero(Pasajero p){
		return daoPasajero.altaPasajero(p);
	}
	/**
	 * Metodo que da de baja a un pasajero segun su id
	 * @param id Id del pasajero a dar de baja
	 * @return true si se ha dado de baja , false si no se ha dado de baja
	 */
	public boolean bajaPasajero(int id) {
		return daoPasajero.bajaPasajero(id);
	}
	
	/**
	 * Metodo que devuelve el pasajero segun su id
	 * @param id Id del pasajero a buscar
	 * @return Devuelve el pasajero, en caso de no existir devolvera null
	 */
	public Pasajero consultarPasajero(int id) {
		return daoPasajero.consultaPasajero(id);
	}
	/**
	 * Metodo que devuelve la lista de todos los pasajeros
	 * @return la lista de pasajeros.
	 */
	public List<Pasajero> listarPasajeros(){
		return daoPasajero.listarPasajeros();
	}
	
	public boolean bajaPasajeroACoche(int idCoche, int idPasajero) {
		return daoPasajero.bajaPasajeroACoche(idPasajero, idCoche);
	}
	
	public boolean altaPasajeroACoche(int idCoche, int idPasajero) {
		return daoPasajero.altaPasajeroACoche(idPasajero, idCoche);
	}
	
	public List<Pasajero> pasajerosCoche(int idCoche){
		return daoPasajero.listarPasajerosCoche(idCoche);
	}
	
	public List<Coche> CochesSinPasajeros(){
		return daoPasajero.CochesSinPasajeros();
	}
	
}

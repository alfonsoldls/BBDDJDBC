package modelo.persistencia.intefaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

//Esta interfaz define un CRUD para el objeto persona
//es decir, las operaciones b�sicas que podemos hacer con una entidad
//Create
//Read
//Update
//Delete
public interface DaoCoche {
	
	public boolean alta(Coche c);
	public boolean baja(int id);
	public boolean modificar(Coche c);
	public Coche obtener(int id);
	public List<Coche> listar();
	public List<Pasajero> getPasajeros(int cocheId);
	void añadirPasajero(int cocheId, int pasajeroId);
	void borrarPasajero(int cocheId, int pasajeroId);
	
}




package persistencia.interfaces;

import java.util.List;

import modelo.entidad.Pasajero;

public interface DaoPasajero {

	public boolean alta (Pasajero p);
	public boolean baja (int idPasajero);
	public Pasajero obtener(int idPasajero);
	public List<Pasajero> listar();
	public boolean pasajeroACoche(int idPasajero, int idCoche);
	public boolean bajaPasajeroCoche(int idPasajero);
	public List<Pasajero> listarPasajerosCoche(int idCoche); 
	
}

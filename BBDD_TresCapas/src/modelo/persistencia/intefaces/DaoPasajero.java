package modelo.persistencia.intefaces;

import java.util.List;

import modelo.entidad.Pasajero;


public interface DaoPasajero {

		public boolean alta(Pasajero p);
		public boolean baja(int id);
		public Pasajero obtener(int id);
		public List<Pasajero> listar();
}

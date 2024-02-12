package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoCoche {

	boolean altaCoche (Coche c);
	boolean bajaCoche (int id);
	Coche consultaCoche(int id);
	boolean modificarCoche(Coche c);
	List<Coche>listarCoches(); 
	
}

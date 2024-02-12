package modelo.negocio;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.persistencia.intefaces.DaoCoche;
import modelo.ppersistencia.DaoCocheMySql;

public class gestionCoches {

private DaoCoche daoCoche = new DaoCocheMySql();
	
	/**
	 * Método que da de alta un coche en la base de datos. 
	 * @param c el coche a dar de alta
	 * @return 0 en caso de que hayamos dado de alta el coche, 1 en caso
	 * de algun error de conexión con la bbdd y 2 en caso de que los campos
	 * marca o modelo sean NULL.
	 */
	public int alta(Coche c){
		if(c.getMarca()!=null || c.getModelo()!=null) {
		boolean alta = daoCoche.alta(c);
		
			if(alta) {
				return 0;
			}else {
				return 1;
			}
		}
		else {
			return 2;
			
	}
}
	/**
	 * Método que da de baja un coche en la base de datos.
	 */
	
	public boolean baja(int id){
		boolean baja = daoCoche.baja(id);
		return baja;
	}
	
	/**
	 * Método que da modifica un coche en la base de datos. La modificarcion sera a partir del 
	 * id del coche.
	 * @param c el coche a modificar. Dentro tiene que tener el id
	 * @return 0 en caso de que hayamos modificado el coche, 1 en caso
	 * de algun error de conexion con la bbdd y 2 2 en caso de que los campos
	 * marca o modelo sean NULL.
	 */
	public int modificar(Coche c){
		if(c.getMarca()!=null || c.getModelo()!=null) {
		boolean modificado = daoCoche.modificar(c);
			if(modificado) {
				return 0;
			}else {
				return 1;
			}
		}
		else {
			return 2;
		}
	}
	
	public Coche obtener(int id){
		Coche coche = daoCoche.obtener(id);
		return coche;
	}
	
	public List<Coche> listar(){
		List<Coche> listaCoches = daoCoche.listar();
		return listaCoches;
	}
	
}

	
	


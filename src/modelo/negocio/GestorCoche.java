package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {

	private DaoCoche dc = new DaoCocheMySql();
	
	/**
	 * Da alta un coche en la base de datos.
	 * El metodo se encarga de comprobar si los datos de Marca y Modelo del coche son validos
	 * @param c Coche a dar de alta
	 * @return Retorna -1 si los campos de marca o modelo estan vacios.
	 *  0 en caso de que no se haya podido dar de alta por algun tipo de error de la  base de datos.
	 *  1 si se ha dado de alta.
	 */
	public int altaCoche(Coche c) {
		
		if (!c.getMarca().isEmpty() && !c.getModelo().isEmpty()) {
			if(dc.altaCoche(c))
				return 1;
			else 
				return 0;
		} else
			return -1;
		
	}
	
	/**
	 * Da de baja un coche segun el id pasado por parametro
	 * @param id Id del coche que queremos borrar
	 * @return true en caso de que se haya dado de baja, false si no se ha podido dar de baja
	 */
	public boolean bajaCoche(int id) {
		boolean baja = dc.bajaCoche(id);
		return baja;
		
	}
	
	/**
	 * Modifica un coche de la base de datos.
	 * El metodo se encarga de comprobar primero si los datos de Marca y Modelo del coche no son vacios
	 * @param c Coche a modificar
	 * @return Retorna -1 si los campos de marca o modelo estan vacios.
	 *  0 en caso de que no se haya podido modificar por algun tipo de error de la  base de datos o el id sea inexistente.
	 *  1 se ha modificado correctamente.
	 */
	
	public int modificarCoche(Coche c) {
		if (!c.getMarca().isEmpty() || !c.getModelo().isEmpty()) {
			if(dc.modificarCoche(c))
				return 1;
			else 
				return 0;
		} else
			return -1;
		
	}
	
	
	/**
	 * Buscar un coche segun su id en la base de datos
	 * @param id Id del coche que queremos obtener
	 * @return Devuelve el coche, si no existe devolverá null
	 */
	public Coche obtenerCoche(int id) {
		Coche c = dc.consultaCoche(id);
		return c;
		
	}
	/**
	 * Lista los coches que hay en la base de datos
	 * @return Devuelve la lista de coches
	 */
	public List<Coche> listarCoches(){
		return dc.listarCoches();
	}
	
	
}

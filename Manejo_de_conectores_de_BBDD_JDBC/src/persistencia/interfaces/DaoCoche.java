package persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoCoche {
	
	public boolean alta (Coche c);
	public boolean baja (int idCoche);
	public Coche obtener(int idCoche);
	public boolean modificar(Coche c);
	public List<Coche> listar();

}

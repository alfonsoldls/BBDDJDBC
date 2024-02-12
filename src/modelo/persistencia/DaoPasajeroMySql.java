package modelo.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.AbstractDaoMySql;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql extends AbstractDaoMySql implements DaoPasajero {

	@Override
	public boolean altaPasajero(Pasajero p) {
		sql = "insert into pasajero (nombre,edad, peso) values (?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setFloat(3, p.getPeso());
			filas = ps.executeUpdate();
			
			if(filas == 0)
				return false;
			else 
				return true;
			
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean bajaPasajero(int id) {
		sql = "delete from pasajero where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			filas = ps.executeUpdate();
			
			if(filas == 0)
				return false;
			else 
				return true;
			
		}catch (SQLException e) {
			return false;
		}
	}

	@Override
	public Pasajero consultaPasajero(int id) {
		sql = "select * from pasajero where id = ?";
		DaoCoche dc = new DaoCocheMySql();
		Pasajero p = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				p = new Pasajero();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setEdad(rs.getInt("edad"));
				p.setPeso(rs.getFloat("peso"));
				p.setCoche(dc.consultaCoche(rs.getInt("coche_id")));
			}
		} catch (SQLException e) {
			return p;
		}
		return p;
	}

	@Override
	public List<Pasajero> listarPasajeros() {
		sql = "select * from pasajero";
		List<Pasajero> lista = new ArrayList<>();
		DaoCoche dc = new DaoCocheMySql();
		Pasajero p = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				p = new Pasajero();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setEdad(rs.getInt("edad"));
				p.setPeso(rs.getFloat("peso"));
				p.setCoche(dc.consultaCoche(rs.getInt("coche_id")));
				lista.add(p);
			}
			
		} catch (SQLException e) {
			return null;
		}
		return lista;
	}
	
	@Override
	public boolean altaPasajeroACoche(int idPasajero, int idCoche) {
		sql = "update pasajero set coche_id = ? where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			filas = ps.executeUpdate();
			if(filas == 0)
				return false;
			else 
				return true;
			
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean bajaPasajeroACoche(int idPasajero, int idCoche) {
		sql = "update pasajero set coche_id = NULL where id = ? and coche_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(2, idCoche);
			ps.setInt(1, idPasajero);
			filas = ps.executeUpdate();
			if(filas == 0)
				return false;
			else 
				return true;
			
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public List<Pasajero> listarPasajerosCoche(int idCoche) {
		sql = "select * from pasajero where coche_id = ?";
		List<Pasajero> lista = new ArrayList<>();
		Pasajero p;
		DaoCoche dc = new DaoCocheMySql();
	
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCoche);
			rs = ps.executeQuery();
			while(rs.next()) {
				p = new Pasajero();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setEdad(rs.getInt("edad"));
				p.setPeso(rs.getFloat("peso"));
				p.setCoche(dc.consultaCoche(rs.getInt("coche_id")));
				lista.add(p);
			}
		} catch (SQLException e) {
			return null;
		}
		return lista;
	}
	
	@Override
	public List<Coche> CochesSinPasajeros() {
		sql = 	"SELECT * FROM coche WHERE id NOT IN (SELECT DISTINCT coche_id FROM pasajero WHERE coche_id is not null)";
		List<Coche> lista = new ArrayList<>();
		Coche c;
		DaoCoche dc = new DaoCocheMySql();
	
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				c = new Coche();
				c = dc.consultaCoche(rs.getInt("id"));
				lista.add(c);
			}
		} catch (SQLException e) {
			return null;
		}
		return lista;
	}
	
	

	
	
}

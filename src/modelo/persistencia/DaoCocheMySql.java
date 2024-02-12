package modelo.persistencia;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.AbstractDaoMySql;
import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMySql extends AbstractDaoMySql implements DaoCoche {

		
	
	@Override
	public boolean altaCoche(Coche c) {
		sql = "insert into coche (marca, modelo, año_fabricacion, km) values (?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAnioFabricacion());
			ps.setDouble(4, c.getKm());
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
	public boolean bajaCoche(int id) {
		sql = "delete from coche where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			filas = ps.executeUpdate();
			
			if(filas == 0)
				return false;
			else 
				return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Error: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Coche consultaCoche(int id) {
		sql = "select * from coche where id = ?";
		Coche c = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				c = new Coche();
				c.setId(rs.getInt("id"));
				c.setMarca(rs.getString("marca"));
				c.setModelo(rs.getString("modelo"));
				c.setAnioFabricacion(rs.getInt("año_fabricacion"));
				c.setKm(rs.getDouble("km"));
			}
		} catch (SQLException e) {
			return c;
		}
		return c;
	}

	@Override
	public boolean modificarCoche(Coche c) {
		sql = "update coche set marca = ?, modelo = ?, año_fabricacion = ? , km = ? where id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAnioFabricacion());
			ps.setDouble(4, c.getKm());
			ps.setInt(5, c.getId());
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
	public List<Coche> listarCoches() {
		sql = "select * from coche";
		List<Coche> lista = new ArrayList<>();
		Coche c = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				c = new Coche();
				c.setId(rs.getInt("id"));
				c.setMarca(rs.getString("marca"));
				c.setModelo(rs.getString("modelo"));
				c.setAnioFabricacion(rs.getInt("año_fabricacion"));
				c.setKm(rs.getDouble("km"));
				lista.add(c);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
			return null;
		}
		return lista;
	}

}

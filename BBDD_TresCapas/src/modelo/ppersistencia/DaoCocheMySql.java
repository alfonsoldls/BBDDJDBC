package modelo.ppersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.intefaces.DaoCoche;

public class DaoCocheMySql implements DaoCoche {
	
	private static final Properties properties = ConexionDB.getProperties();
	
	 public Connection getConnection() throws SQLException {
	        String url = properties.getProperty("db.url");
	        String user = properties.getProperty("db.user");
	        String password = properties.getProperty("db.password");
	       	
		
			return DriverManager.getConnection(url,user,password);	
		
	}
	
	public boolean cerrarConexion(Connection conexion){
		if (conexion != null) {
		try {
			conexion.close();
			System.out.println("conexion cerrada");
			return true; 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		}else return false;
		
	}
	

	@Override
	public boolean alta(Coche c) {
		Connection conexion;
		try {
			conexion = getConnection();
		} catch (SQLException e1) {
			System.err.print(e1);
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coches (MARCA,MODELO,AÑOFAB,KMS) "
				+ " values(?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAñoFab());
			ps.setFloat(4, c.getKms());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion(conexion);
		}
		
		return alta;
	
	}

	@Override
	public boolean baja(int id) {
		Connection conexion;
		try {
			conexion = getConnection();
		} catch (SQLException e1) {
			System.err.print(e1);
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from coches where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja el coche con el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion(conexion);
		}
		return borrado; 
	}

	@Override
	public boolean modificar(Coche c) {
		Connection conexion;
		try {
			conexion = getConnection();
		} catch (SQLException e1) {
			System.err.print(e1);
			return false;
		}
		boolean modificado = true;
		String query = "update coches set MARCA=?, MODELO=?, AÑOFAB=?, KMS=? where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAñoFab());
			ps.setFloat(4, c.getKms());
			ps.setInt(5, c.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			System.out.println(c.getId());
			if(numeroFilasAfectadas == 0)
				modificado = false;
			else
				modificado = true;
		} catch (SQLException e) {
			System.out.println("modificar -> error al modificar el coche" + c);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion(conexion);
		}
		
		return modificado;
	}



	@Override
	public Coche obtener(int id) {
		Connection conexion;
		try {
			conexion = getConnection();
		} catch (SQLException e1) {
			System.err.print(e1);
			return null;
		}		
		Coche coche = null;
		
		String query = "select ID,MARCA,MODELO,AÑOFAB,KMS from coches where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAñoFab(rs.getInt(4));
				coche.setKms(rs.getFloat(5));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion(conexion);
		}
	
		return coche;
	}

	@Override
	public List<Coche> listar() {
		Connection conexion;
		try {
			conexion = getConnection();
		} catch (SQLException e1) {
			System.err.print(e1);
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MARCA,MODELO,AÑOFAB,KMS from coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAñoFab(rs.getInt(4));
				coche.setKms(rs.getFloat(5));
				
				listaCoches.add(coche);
			}
			for (Coche c: listaCoches) {
				System.out.println(c);
				
			}
			
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener la lista de coches");
			e.printStackTrace();
			
		} finally {
			cerrarConexion(conexion);
		}
		return listaCoches;
	}
	
	   

	@Override
	public List<Pasajero> getPasajeros(int cocheId) {
		Connection conexion = null;
	    List<Pasajero> pasajeros = new ArrayList<>();

	    try {
	        conexion = getConnection();
	        String query = "SELECT p.* FROM pasajero p INNER JOIN coche_pasajero cp ON p.id = cp.pasajero_id WHERE cp.coche_id = ?";
	        PreparedStatement ps = conexion.prepareStatement(query);
	        ps.setInt(1, cocheId);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Pasajero pasajero = new Pasajero();
	            pasajero.setId(rs.getInt("id"));
	            pasajero.setNombre(rs.getString("nombre"));
	            // Añade más campos según la estructura de tu tabla Pasajeros

	            pasajeros.add(pasajero);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cerrarConexion(conexion);
	    }

	    return pasajeros;
	}
	

	@Override
	public void añadirPasajero(int cocheId, int pasajeroId) {
		Connection conexion = null;

	    try {
	        conexion = getConnection();
	        String query = "INSERT INTO coche_pasajero (coche_id, pasajero_id) VALUES (?, ?)";
	        PreparedStatement ps = conexion.prepareStatement(query);
	        ps.setInt(1, cocheId);
	        ps.setInt(2, pasajeroId);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cerrarConexion(conexion);
	    }
	}


	@Override
	public void borrarPasajero(int cocheId, int pasajeroId) {
		Connection conexion = null;

	    try {
	        conexion = getConnection();
	        String query = "DELETE FROM coche_pasajero WHERE coche_id = ? AND pasajero_id = ?";
	        PreparedStatement ps = conexion.prepareStatement(query);
	        ps.setInt(1, cocheId);
	        ps.setInt(2, pasajeroId);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cerrarConexion(conexion);
	    }
		
	}

}

		
	


	


	
	
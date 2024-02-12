package modelo.ppersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import modelo.entidad.Pasajero;
import modelo.persistencia.intefaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero {

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
	public boolean alta(Pasajero p) {
		Connection conexion;
		try {
			conexion = getConnection();
		} catch (SQLException e1) {
			System.err.print(e1);
			return false;
		}
		boolean alta = true;
		
		String query = "insert into pasajero (ID,NOMBRE,EDAD,PESO) "
				+ " values(?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getEdad());
			ps.setFloat(4, p.getPeso());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
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
		String query = "delete from pasajero where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja el pasajero con el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion(conexion);
		}
		return borrado; 
	}
	

	@Override
	public Pasajero obtener(int id) {
		Connection conexion;
		try {
			conexion = getConnection();
		} catch (SQLException e1) {
			System.err.print(e1);
			return null;
		}		
		Pasajero pasajero = null;
		
		String query = "select ID,NOMBRE,EDAD,PESO, from pasajero where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el pasajero con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion(conexion);
		}
	
		return pasajero;
	}

	@Override
	public List<Pasajero> listar() {
		Connection conexion;
		try {
			conexion = getConnection();
		} catch (SQLException e1) {
			System.err.print(e1);
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "select ID,NOMBRE,EDAD,PESO, from pasajero";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			Pasajero pasajero = null;
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				
				
				listaPasajeros.add(pasajero);
			}
			for (Pasajero p: listaPasajeros) {
				System.out.println(p);
				
			}
			
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener la lista de pasajeros");
			e.printStackTrace();
			
		} finally {
			cerrarConexion(conexion);
		}
		return listaPasajeros;
	}
}
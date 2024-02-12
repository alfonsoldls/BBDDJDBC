package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySql {

	private static String url, user, pass;
	private static Connection conn;
		

	
	private ConexionMySql() {
		
		Configuracion conf = new Configuracion();
		conf.inicializar();
		url  = conf.getProperty("url");
		user = conf.getProperty("usuario");
		pass = conf.getProperty("password");
		
		try {
			//Se le asigna la conexion al atributo conn usando el getconnection pasandole la url, user y pass
			//Si va todo bien se escribe un mensaje: conexion establecida correctamente
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("|CONEXION ESTABLECIDA CON LA BASE DE DATOS|");
			System.out.println("===========================================\n\n");
			
		} catch (SQLException e) {
			//En caso de que ocurra un error al intentar conectar con la base de datos se muestra por pantalla que ha habido un error.
			System.out.println("HA HABIDO UN ERROR AL INTENTAR CONECTARSE A LA BASE DE DATOS\n\n");
			e.printStackTrace();
		}
	
	}
	
	public static Connection getConexion() {
		//Se crea el metodo estático getconexion que llamara al constructore en caso de que no haya conexion establecida.
		if(conn == null)
			new ConexionMySql();
		//Devuelve la conexion.
		return conn;
	}
}
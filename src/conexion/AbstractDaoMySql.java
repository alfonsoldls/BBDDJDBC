package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbstractDaoMySql {
		//Se crea una clase abstracta que contendra los atributos necesarios que se usaran para la acceder a la base de datos
		protected Connection conn;
		protected PreparedStatement ps;
		protected ResultSet rs;
		protected String sql;
		protected int filas;
		
		public AbstractDaoMySql(){
			//Se llama al metodo estatico getConexion y se le asigna que devolvera la conexion y se le asigna a la variable 
			conn = ConexionMySql.getConexion();
		}
}
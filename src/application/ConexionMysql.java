package application;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConexionMysql {
	private Connection connection;
	private String usuario = "prueba1";
	private String password = "Prueba1.";
	private String servidor = "localhost";
	private String puerto = "3306";
	private String nombreDB = "db_javaFxCRUD";
	
	private String url = "jdbc:mysql://"+servidor+":"+puerto+"/"+nombreDB+"?serverTimezone=UTC";
	
	
	//private String driver = "com.mysql.jdbc.Driver";
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	public ConexionMysql() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, usuario, password);
			if(connection != null) {
				System.out.print("Conexion con exito");
			}
		} catch (Exception e){
			System.err.println("ocurrio un erroren la conexion");
			System.err.println("Mensaje" + e.getMessage());
			System.err.println("Detalle ErRor");
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return connection;
	}
}

package dao;

import model.Tarea;
import application.ConexionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

public class RegistrarTarea {
	
	private ConexionMysql fabricaConexion;
	public RegistrarTarea() {
		
		this.fabricaConexion = new ConexionMysql();  
	}
	
	public boolean registrar(Tarea tarea) {
		
		try {
			String SQL = "insert into tarea(titulo, descripcion, prioridad,"
					+ "fecha_culminacion, correo, portal_web, fisico, estado ) "
					+ " values(?,?,?,?,?,?,?,?)";
			Connection connection = this.fabricaConexion.getConnection();
			
			PreparedStatement sentencia = connection.prepareStatement(SQL);
			sentencia.setString(1, tarea.getTitulo());
			sentencia.setString(2, tarea.getDescripcion());
			sentencia.setString(3, tarea.getPrioridad());
			sentencia.setString(4, tarea.getFechaCulminacion());
			sentencia.setString(5, tarea.getCorreo());
			sentencia.setString(6, tarea.getPortalWeb());
			sentencia.setString(7, tarea.getFisico());
			sentencia.setString(8, tarea.getEstado());
			
			sentencia.executeUpdate();
			sentencia.close();
			
			return true;
			
		} catch (Exception e) {
			System.err.println("error al registrar");
			System.err.println("error al registrar " + e.getMessage());
			System.err.println("Detalle de error ---");
			
			e.printStackTrace();
			return false;
		}	
	}
	
	public List<Tarea> listar(){
		List <Tarea> listarTareas = new ArrayList<>();
		try {
			
			String SQL = "SELECT * FROM tarea;";
			Connection connection = this.fabricaConexion.getConnection();
			PreparedStatement sentencia = connection.prepareStatement(SQL);
			ResultSet data = sentencia.executeQuery();
			// data.next pregunta si hay una siguiente fila
			while(data.next()) {
				Tarea tarea = new Tarea();
				tarea.setId(data.getInt(1));
				tarea.setTitulo(data.getString(2));
				tarea.setDescripcion(data.getString(3));
				tarea.setPrioridad(data.getString(4));
				tarea.setFechaCulminacion(data.getString(5));
				tarea.setCorreo(data.getString(6));
				tarea.setPortalWeb(data.getString(7));
				tarea.setFisico(data.getString(8));
				tarea.setEstado(data.getString(9));
				
				listarTareas.add(tarea);
			}
		} catch (Exception e) {
			
			System.err.println("error al listar");
			System.err.println("error al listar " + e.getMessage());
			System.err.println("Detalle de error ---");
			
			e.printStackTrace();
		}
		return listarTareas;
	}
	
	// EDITAR TAREA
	public boolean editar (Tarea tarea) {
		try {
			String SQL = "update tarea set titulo=?, descripcion=?, prioridad=?, fecha_culminacion=?,"
					+ "correo=?, portal_web=?, fisico=?, estado=? WHERE id=?";
			Connection connection = this.fabricaConexion.getConnection();
			
			PreparedStatement sentencia = connection.prepareStatement(SQL);
			
			sentencia.setString(1, tarea.getTitulo());
			sentencia.setString(2, tarea.getDescripcion());
			sentencia.setString(3, tarea.getPrioridad());
			sentencia.setString(4, tarea.getFechaCulminacion());
			sentencia.setString(5, tarea.getCorreo());
			sentencia.setString(6, tarea.getPortalWeb());
			sentencia.setString(7, tarea.getFisico());
			sentencia.setString(8, tarea.getEstado());
			
			sentencia.setInt(9, tarea.getId());
			
			sentencia.executeUpdate();
			sentencia.close();
			
			return true;
		} catch (Exception e) {
			System.err.println("error al Edita");
			System.err.println("error al Editar " + e.getMessage());
			System.err.println("Detalle de errorEditar ---");
			
			return false;
		}
		
		
	}
	
}

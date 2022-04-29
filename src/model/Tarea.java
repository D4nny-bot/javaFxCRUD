package model;

public class Tarea {
	
	private int id;
	private String titulo;
	private String descripcion;
	private String prioridad;
	private String fechaCulminacion;
	private String correo;
	private String portalWeb;
	private String fisico;
	private String estado;
	
	public Tarea() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getFechaCulminacion() {
		return fechaCulminacion;
	}
	public void setFechaCulminacion(String fecha) {
		this.fechaCulminacion = fecha;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPortalWeb() {
		return portalWeb;
	}
	public void setPortalWeb(String portal) {
		this.portalWeb = portal;
	}
	public String getFisico() {
		return fisico;
	}
	public void setFisico(String fisico) {
		this.fisico = fisico;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Tarea{" + "id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion;
	}

}

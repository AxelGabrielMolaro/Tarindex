package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class Tarea implements Serializable {
	
	private static final long serialVersionUID=7L;
	
	private Integer id;
	private String nombre;
	private Integer creador;

	
	

	private String descripcion;
	private String acceso;
	private String estado;
	
	public Tarea() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getCreador() {
		return creador;
	}

	public void setCreador(Integer idUsuarioActual) {
		this.creador = idUsuarioActual;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAcceso() {
		return acceso;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
}

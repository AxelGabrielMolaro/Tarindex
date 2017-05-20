package ar.edu.unlam.diit.scaw.services.impl;

import java.io.Serializable;

public class AccedeTarea implements Serializable {
	
	private static final long serialVersionUID=8L;
	
	private Integer id;
	private String modo;
	private Integer idTarea;
	private Integer idUsuario;
	
	
	public AccedeTarea() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getModo() {
		return modo;
	}


	public void setModo(String modo) {
		this.modo = modo;
	}


	public Integer getIdTarea() {
		return idTarea;
	}


	public void setIdTarea(Integer idTarea) {
		this.idTarea = idTarea;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

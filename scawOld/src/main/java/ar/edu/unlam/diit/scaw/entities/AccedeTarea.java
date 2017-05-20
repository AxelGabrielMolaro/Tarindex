package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class AccedeTarea implements Serializable{
	
	private static final long serialVersionUID=9L;
	private Integer id;
	private Integer idUsuario;
	private Integer idTarea;
	private String modo;
	private Integer esAsignada;
	
	public AccedeTarea() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Integer idTarea) {
		this.idTarea = idTarea;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getEsAsignada() {
		return esAsignada;
	}

	public void setEsAsignada(Integer esAsignada) {
		this.esAsignada = esAsignada;
	}
	
}

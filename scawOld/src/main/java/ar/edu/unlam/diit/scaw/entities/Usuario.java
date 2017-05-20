package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class Usuario implements Serializable, Comparable<Usuario>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String nickName;
	private String apellido;
	private String contrasena;
	private String tipo;
	private Integer estaAprobado;

	
	public Usuario()
	{
		
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getEstaAprobado() {
		return estaAprobado;
	}

	public void setEstaAprobado(Integer estaAprobado) {
		this.estaAprobado = estaAprobado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		return true;
	}

	@Override
	public int compareTo(Usuario user) {
		// TODO Auto-generated method stub
		return user.nombre.compareTo(nombre);
	}

	
	
	
}

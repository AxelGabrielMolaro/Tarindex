package ar.edu.unlam.diit.scaw.beans.heramientas;

import java.io.Serializable;

import ar.edu.unlam.diit.scaw.entities.Usuario;

/**Es para el caso particulas que quiero que en ver de que diga 1 0 diga si o no en estaAprobado
 * @author molaro
 *
 */
public class HerramientasUsuarioEspecial {
	
		
		
		private Integer id;
		private String nombre;
		private String nickName;
		private String apellido;
		private String contrasena;
		private String tipo;
		private String estaAprobado;

		
		public HerramientasUsuarioEspecial( Integer id, String nombre, String nickName,String apellido,String contraseña,String tipo, String estaAprobado)
		{
			this.id = id;
			this.nombre = nombre;
			this.apellido = apellido;
			this.nickName = nickName;
			this.contrasena= contraseña;
			this.tipo = tipo;
			this.estaAprobado = estaAprobado;
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

		
		

		public String getEstaAprobado() {
			return estaAprobado;
		}




		public void setEstaAprobado(String estaAprobado) {
			this.estaAprobado = estaAprobado;
		}




		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
			return result;
		}

		

		
		
		
	

	
}

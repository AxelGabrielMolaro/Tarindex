package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.beans.heramientas.HerramientasUsuarioEspecial;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioService {
	
	
	public Usuario login(String nickname, String contraseña) throws Exception;
	
	public List<Usuario> getListaDeUsuarios();

	
	//aciones del usuario admin 
		public Usuario getUsuarioPorId(Integer id);
		
		public List<HerramientasUsuarioEspecial> getListaDeUsuariosMenosElUsuarioActual(Integer idUsuarioActual);
		
		public List<Usuario> getListaDeUsuariosQueNoEstanAprobados();
		
		public void seterElValorDeAprobadoDeUnUsuario(Integer id,Integer valor0o1);
		
		
		public void modificarUnUsuarioPorId(Integer id,String nickname,String nombre,String apellido,String contraseña,String tipo,Integer estaAprobado) throws Exception;
		
		public void eliminarUnUsuarioPorId(Integer id);
		
		//usuario cualquiera
		public void guardarUnUsuarioEnLaBDD(Usuario usuarioAGuardar) throws Exception;
		
		//tareas
		
		public List<Usuario> getListaDeUsuariosQueNoParticipenEnUnaTareaYNickName(Integer idTarea,String nickName) throws Exception;
		

		public void agregarUnParticipanteAUnTareaPorIdDeUsuarioYModo(Integer idUsuarioNuevo,String modo ,Integer idTarea);
		
		public List<Usuario> getListaDeUsuariosQuePertenecenAUnaTarea(Integer idTarea);
		
		public void eliminarUnParticipanteAUnTareaPorIdDeUsuario(Integer idUsuarioEliminaro ,Integer idTarea);
		
		//asignar tarea
		public List<Usuario> getListaDeUsuariosMenosElUsuarioActualYPorTipo(Integer idUsuarioActual,String tipo);
		
		//asignar tarea
				public List<Usuario> getListaDeUsuariosNormales();
}

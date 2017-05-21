package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {
	
	public Usuario login(String nickname, String contraseña);
	
	public List<Usuario> getListaDeUsuarios();
	
	public Usuario getUsuarioPorNickName(String nickName) ;
	
	//aciones del usuario admin 
	public Usuario getUsuarioPorId(Integer id);
	
	
	public List<Usuario> getListaDeUsuariosMenosElUsuarioActual(Integer idUsuarioActual);
	
	public List<Usuario> getListaDeUsuariosQueNoEstanAprobados();
	
	public void seterElValorDeAprobadoDeUnUsuario(Integer id,Integer valor0o1);
	
	public void guardarUnUsuarioEnLaBDD(Usuario usuarioAGuardar);
	
	public void modificarUnUsuarioPorId(Integer id,String nickname,String nombre,String apellido,String contraseña,String tipo,Integer estaAprobado);
	
	public void eliminarUnUsuarioPorId(Integer id);
	
	//tareas
	
	public List<Usuario> getListaDeUsuariosQueNoParticipenEnUnaTareaYNickName(Integer idTarea,String nickName);
	
	public List<Usuario> getListaDeUsuariosQuePertenecenAUnaTarea(Integer idTarea);

	public void agregarUnParticipanteAUnTareaPorIdDeUsuarioYModo(Integer idUsuarioNuevo,String modo ,Integer idTarea);
	
	
	public void eliminarUnParticipanteAUnTareaPorIdDeUsuario(Integer idUsuarioEliminaro ,Integer idTarea);
	
	public List<Usuario> getListaDeUsuariosNormales();
	
}

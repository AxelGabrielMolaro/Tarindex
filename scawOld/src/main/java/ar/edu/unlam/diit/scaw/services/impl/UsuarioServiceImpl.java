package ar.edu.unlam.diit.scaw.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.daos.PersonDao;
import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import junit.framework.Assert;

public class UsuarioServiceImpl implements UsuarioService {

	
	@Autowired
	UsuarioDao usuarioDao;
	
	//UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
	
	@Override
	public Usuario login(String nickname, String contraseña) {
		return usuarioDao.login(nickname, contraseña);
	}
	
	@Override
	public List<Usuario> getListaDeUsuarios() {
		// TODO Auto-generated method stub
		return usuarioDao.getListaDeUsuarios();
	}
	//admin
	@Override
	public Usuario getUsuarioPorId(Integer id) {
		// TODO Auto-generated method stub
		return usuarioDao.getUsuarioPorId(id);
	}

	@Override
	public List<Usuario> getListaDeUsuariosMenosElUsuarioActual(Integer idUsuarioActual) {
		// TODO Auto-generated method stub
		return usuarioDao.getListaDeUsuariosMenosElUsuarioActual(idUsuarioActual);
	}

	@Override
	public List<Usuario> getListaDeUsuariosQueNoEstanAprobados() {
		// TODO Auto-generated method stub
		return usuarioDao.getListaDeUsuariosQueNoEstanAprobados();
	}

	@Override
	public void seterElValorDeAprobadoDeUnUsuario(Integer id, Integer valor0o1) {
		// TODO Auto-generated method stub
		usuarioDao.seterElValorDeAprobadoDeUnUsuario(id, valor0o1);
	}

	/* lo guarda si no existe si no tira exepciion
	 * (non-Javadoc)
	 * @see ar.edu.unlam.diit.scaw.services.UsuarioService#guardarUnUsuarioEnLaBDD(ar.edu.unlam.diit.scaw.entities.Usuario)
	 */
	@Override
	public void guardarUnUsuarioEnLaBDD(Usuario usuarioAGuardar) throws Exception {
		
		System.out.println(usuarioAGuardar.getNickName() + "nick");
		System.out.println(usuarioDao.getUsuarioPorNickName(usuarioAGuardar.getNickName() )+ "oa");
		if(!(usuarioDao.getUsuarioPorNickName(usuarioAGuardar.getNickName() )==null))
		{
		
			System.out.println("entro3");
			throw new Exception("Error de registro");
		}
		else
		{
			System.out.println("entro4");
				usuarioDao.guardarUnUsuarioEnLaBDD(usuarioAGuardar);
		}
		
	}

		
	@Override
	public void modificarUnUsuarioPorId(Integer id, String nickname, String nombre, String apellido, String contraseña,
			String tipo, Integer estaAprobado) {
		// TODO Auto-generated method stub
		usuarioDao.modificarUnUsuarioPorId(id, nickname, nombre, apellido, contraseña, tipo, estaAprobado);
		
	}
	@Override
	public void eliminarUnUsuarioPorId(Integer id) {
		// TODO Auto-generated method stub
		usuarioDao.eliminarUnUsuarioPorId(id);
	}
	
	


	@Override
	public void agregarUnParticipanteAUnTareaPorIdDeUsuarioYModo(Integer idUsuarioNuevo, String modo, Integer idTarea) {
		usuarioDao.agregarUnParticipanteAUnTareaPorIdDeUsuarioYModo(idUsuarioNuevo, modo, idTarea);
		
	}
	
	@Override
	public List<Usuario> getListaDeUsuariosQuePertenecenAUnaTarea(Integer idTarea) {
		// TODO Auto-generated method stub
		return usuarioDao.getListaDeUsuariosQuePertenecenAUnaTarea(idTarea);
	}
	
	@Override
	public List<Usuario> getListaDeUsuariosQueNoParticipenEnUnaTareaYNickName(Integer idTarea,String nickName) {
		// TODO Auto-generated method stub
		List<Usuario> lista = usuarioDao.getListaDeUsuariosQueNoParticipenEnUnaTareaYNickName(idTarea, nickName);
		List<Usuario> lista2 = usuarioDao.getListaDeUsuariosQuePertenecenAUnaTarea(idTarea);
		List<Usuario> lista3 = new ArrayList<>();
		for(Usuario u : lista)
			{
				if(!lista2.contains(u))
				{
					lista3.add(u);
				}
			}
		return lista3;
	}
	
	@Override
	public void eliminarUnParticipanteAUnTareaPorIdDeUsuario(Integer idUsuarioEliminaro, Integer idTarea) {
		usuarioDao.eliminarUnParticipanteAUnTareaPorIdDeUsuario(idUsuarioEliminaro, idTarea);
		
	}
	
	@Override
	public List<Usuario> getListaDeUsuariosMenosElUsuarioActualYPorTipo(Integer idUsuarioActual, String tipo) {
		List<Usuario> lista = usuarioDao.getListaDeUsuariosMenosElUsuarioActual(idUsuarioActual);
		List<Usuario> lista2 = new ArrayList<>();
		for(Usuario u : lista)
		{
			if(u.getTipo().equals(tipo))
			{
				lista2.add(u);
			}
		}
			
		return lista2;
	}
	
}

package ar.edu.unlam.diit.scaw.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.beans.heramientas.HerramientaValidaciones;
import ar.edu.unlam.diit.scaw.beans.heramientas.HerramientasEncriptar;
import ar.edu.unlam.diit.scaw.beans.heramientas.HerramientasExprecionesRegulares;
import ar.edu.unlam.diit.scaw.beans.heramientas.HerramientasUsuarioEspecial;
import ar.edu.unlam.diit.scaw.daos.PersonDao;
import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import junit.framework.Assert;

public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;

	// UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

	@Override
	public Usuario login(String nickname, String contraseña) throws Exception {
		if (HerramientasExprecionesRegulares.tieneComillasElString(nickname)==false||nickname.length()==0) {
			throw new Exception("Error de ingreso");
		} else if (HerramientasExprecionesRegulares.tieneComillasElString(contraseña)==false||contraseña.length()==0) {
			throw new Exception("Error de ingreso");
		} 
		
		return usuarioDao.login(nickname, contraseña);
	}

	@Override
	public List<Usuario> getListaDeUsuarios() {
		// TODO Auto-generated method stub
		return usuarioDao.getListaDeUsuarios();
	}

	// admin
	@Override
	public Usuario getUsuarioPorId(Integer id) {
		// TODO Auto-generated method stub
		return usuarioDao.getUsuarioPorId(id);
	}

	@Override
	public List<HerramientasUsuarioEspecial> getListaDeUsuariosMenosElUsuarioActual(Integer idUsuarioActual) {
		List<Usuario> listaSinEncriptar = usuarioDao.getListaDeUsuariosMenosElUsuarioActual(idUsuarioActual);
		List<HerramientasUsuarioEspecial> listaFinal = new ArrayList<>();
		
		for(Usuario u: listaSinEncriptar)
		{
			if(u.getEstaAprobado().equals(0))
			{
				HerramientasUsuarioEspecial nuevoUser = new HerramientasUsuarioEspecial(u.getId(), u.getNombre(), u.getNickName(), u.getApellido(), u.getContrasena(), u.getTipo(), "No");
				listaFinal.add(nuevoUser);
			}
			if(u.getEstaAprobado().equals(1))
			{
				HerramientasUsuarioEspecial nuevoUser = new HerramientasUsuarioEspecial(u.getId(), u.getNombre(), u.getNickName(), u.getApellido(), u.getContrasena(), u.getTipo(), "Si");
				listaFinal.add(nuevoUser);
			}
		}
		
		return HerramientasEncriptar.encriptarContraseñaMD5TodaUnListaDeUsuarios(listaFinal);
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

	/*
	 * lo guarda si no existe si no tira exepciion (non-Javadoc)
	 * 
	 * @see
	 * ar.edu.unlam.diit.scaw.services.UsuarioService#guardarUnUsuarioEnLaBDD(ar
	 * .edu.unlam.diit.scaw.entities.Usuario)
	 */
	
	@Override
	public void guardarUnUsuarioEnLaBDD(Usuario usuarioAGuardar) throws Exception {

		if (HerramientasExprecionesRegulares.tieneComillasElString(usuarioAGuardar.getNombre())==false||usuarioAGuardar.getNombre().length()==0) {
			throw new Exception("Error de registro : No use carácteres especiales como espacios , '',_ , etc");
		} else if (HerramientasExprecionesRegulares.tieneComillasElString(usuarioAGuardar.getApellido())==false||usuarioAGuardar.getApellido().length()==0) {
			throw new Exception("Error de registro : No use carácteres especiales como espacios , '',_ , etc");
		} else if (HerramientasExprecionesRegulares.tieneComillasElString(usuarioAGuardar.getContrasena())==false||usuarioAGuardar.getContrasena().length()==0) {
			throw new Exception("Error de registro : No use carácteres especiales como espacios , '',_ , etc");
		} else if (HerramientasExprecionesRegulares.tieneComillasElString(usuarioAGuardar.getNickName())==false||usuarioAGuardar.getNickName().length()==0) {
			throw new Exception("Error de registro : No use carácteres especiales como espacios , '',_ , etc");
		}else if (HerramientaValidaciones.elLargoStringPasadoEsMayorALaCantidadPasada(usuarioAGuardar.getContrasena(), 7)==false) {
			throw new Exception("Error de registro : Ingrese una contraseña de por lo menos 8 carácteres");
		}

		if (!(usuarioDao.getUsuarioPorNickName(usuarioAGuardar.getNickName()) == null)) {

			throw new Exception("Error de registro : NickName no válido");
		} else {
			
			usuarioDao.guardarUnUsuarioEnLaBDD(usuarioAGuardar);
		}

	}

	@Override
	public void modificarUnUsuarioPorId(Integer id, String nickname, String nombre, String apellido, String contraseña,
			String tipo, Integer estaAprobado) throws Exception{
		System.out.println(tipo);

		if (HerramientasExprecionesRegulares.tieneComillasElString(nombre)==false|nombre.length()==0) {
			throw new Exception("Error de edicion : No use carácteres especiales como espacios '',_ , etc  ");
		} else if (HerramientasExprecionesRegulares.tieneComillasElString(apellido)==false||apellido.length()==0) {
			throw new Exception("Error de edicion : No use carácteres especiales como espacios '',_ , etc  ");
		} else if (HerramientasExprecionesRegulares.tieneComillasElString(contraseña)==false||contraseña.length()==0) {
			throw new Exception("Error de edicion : No use carácteres especiales como espacios '',_ , etc  ");
		}else if (HerramientasExprecionesRegulares.tieneComillasElString(contraseña)==false||contraseña.length()==0) {
			throw new Exception("Error de edicion : No use carácteres especiales como espacios '',_ , etc  ");
		
		}else if (HerramientasExprecionesRegulares.tieneComillasElString(estaAprobado.toString())==false||(estaAprobado!= 1 && estaAprobado !=0)) {
			throw new Exception("Error de edicion : No use carácteres especiales como espacios '',_ , etc  ");
		}else if (HerramientasExprecionesRegulares.tieneComillasElString(nickname)==false||nickname.length()==0) {
			throw new Exception("Error de edicion : No use carácteres especiales como espacios '',_ , etc ");
		}else if (HerramientaValidaciones.elLargoStringPasadoEsMayorALaCantidadPasada(contraseña, 7)==false) {
			throw new Exception("Error de edición : Use una contraseña de por lo menos 8 carácteres");
		}

		
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
	public List<Usuario> getListaDeUsuariosQueNoParticipenEnUnaTareaYNickName(Integer idTarea, String nickName) {
		// TODO Auto-generated method stub
		List<Usuario> lista = usuarioDao.getListaDeUsuariosQueNoParticipenEnUnaTareaYNickName(idTarea, nickName);
		List<Usuario> lista2 = usuarioDao.getListaDeUsuariosQuePertenecenAUnaTarea(idTarea);
		List<Usuario> lista3 = new ArrayList<>();
		for (Usuario u : lista) {
			if (!lista2.contains(u)) {
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
		for (Usuario u : lista) {
			if (u.getTipo().equals(tipo)) {
				lista2.add(u);
			}
		}

		return lista2;
	}

}

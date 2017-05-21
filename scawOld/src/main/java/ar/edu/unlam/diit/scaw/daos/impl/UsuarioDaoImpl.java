package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.UpdatableSqlQuery;

import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.TareaDaoImpl.TareaMapper;
import ar.edu.unlam.diit.scaw.entities.Person;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.Usuario;

/**
 * @author molaro
 *
 */
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public UsuarioDaoImpl() {
		super();
	}

	// //obtengo los participantes de una tarea
	// public List<Usuario> getListaDeParticipantesDeUnaTareaPorIdTarea(Integer
	// idTarea) {
	// Map<String, Object> params = new HashMap<String, Object>();
	// String sql = "SELECT USUARIO.ID, USUARIO.NOMBRE
	// ,USUARIO.APELLIDO,USUARIO.CONTRASENA ,USUARIO.TIPO,USUARIO.ESTAAPROBADO
	// ,USUARIO.NICKNAME FROM (TAREA JOIN ACCEDETAREA ON TAREA.ID = ACCEDETAREA
	// .IDTAREA) JOIN USUARIO ON ACCEDETAREA.IDUSUARIO = USUARIO.ID WHERE
	// TAREA.ID = ' " + " " + idTarea.toString() + " '";
	//
	// List<Usuario> lista = jdbcTemplate.query(sql, params, new
	// UsuarioMapper());
	// return lista;
	//
	//
	//
	//
	// };

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.edu.unlam.diit.scaw.daos.UsuarioDao#login(java.lang.String,
	 * java.lang.String) logea y devuelve yn user
	 */
	public Usuario login(String nickname,
			String contraseña) {/* si sale bien devuelve un user si no null */

		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USUARIO WHERE NICKNAME =  :a";
		params.put("a", nickname);
		List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
		Usuario usuarioBuscado = new Usuario();

		usuarioBuscado.setNickName(null);
		if (lista.size() == 0) {

		} else {
			for (Usuario u : lista) {
				if (u.getNickName().equals(nickname)) {
					usuarioBuscado.setNickName(u.getNickName());
					usuarioBuscado.setContrasena(u.getContrasena());
					usuarioBuscado.setApellido(u.getApellido());
					usuarioBuscado.setNombre(u.getNombre());
					usuarioBuscado.setId(u.getId());
					usuarioBuscado.setTipo(u.getTipo());
					usuarioBuscado.setEstaAprobado(u.getEstaAprobado());
				}
			}

			if (usuarioBuscado.getNickName().equals(null)) {
				return null;
			} else {
				if (usuarioBuscado.getContrasena().equals(contraseña)) {
					return usuarioBuscado;
				}
			}
			return null;
		}
		return null;

	};

	/*
	 * devuelve los usuarios no aprobados (non-Javadoc)
	 * 
	 * @see ar.edu.unlam.diit.scaw.daos.UsuarioDao#
	 * getListaDeUsuariosQueNoEstanAprobados()
	 */
	@Override
	public List<Usuario> getListaDeUsuariosQueNoEstanAprobados() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USUARIO WHERE ESTAAPROBADO = 0";
		List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
		return lista;
	}

	/*
	 * Devuelve un usuario para id, lo uso para edicion (non-Javadoc)
	 * 
	 * @see
	 * ar.edu.unlam.diit.scaw.daos.UsuarioDao#getUsuarioPorId(java.lang.Integer)
	 */
	@Override
	public Usuario getUsuarioPorId(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USUARIO WHERE ID = :id";
		params.put("id", id.toString());
		List<Usuario> lista =  jdbcTemplate.query(sql, params, new UsuarioMapper());
		for(Usuario u:lista )
		{
			if(u.getId().equals(id))
			{
				return u;
			}
		}
		return null;
	}
	
	@Override
	public Usuario getUsuarioPorNickName(String nickName)  {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USUARIO WHERE NICKNAME =  :n";
		params.put("n",nickName);
		
		List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
		for (Usuario usuario : lista) {
			if(usuario.getNickName().equals(nickName))
			{
				
				return usuario;
			}
		}
		return null;
			
		

	}
	
	/*
	 * Traigo todos los usuarios menos el actual , lo hago para mostrar la lista
	 * sin que se vea el admin (non-Javadoc)
	 * 
	 * @see ar.edu.unlam.diit.scaw.daos.UsuarioDao#
	 * getListaDeUsuariosMenosElUsuarioActual(java.lang.Integer)
	 */
	@Override
	public List<Usuario> getListaDeUsuariosMenosElUsuarioActual(Integer idUsuarioActual) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USUARIO WHERE ID <> '" + idUsuarioActual + "'";
		List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
		return lista;
	}

	/*
	 * para aprobar y desaprobar un usuario, 1= aprobado 2= pendiente de
	 * aprobacion (non-Javadoc)
	 * 
	 * @see
	 * ar.edu.unlam.diit.scaw.daos.UsuarioDao#seterElValorDeAprobadoDeUnUsuario(
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void seterElValorDeAprobadoDeUnUsuario(Integer id, Integer valor0o1) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "UPDATE  USUARIO SET ESTAAPROBADO = '" + valor0o1.toString() + "' where id = '" + id.toString()
				+ "'";
		
		jdbcTemplate.update(sql, params);

	}

	public void save(Person person) {

		String sql = "INSERT INTO PERSON (FIRSTNAME, LASTNAME, EMAIL) VALUES (:firstName, :lastName, :email)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstName", person.getFirstName());
		params.put("lastName", person.getLastName());
		params.put("email", person.getEmail());
		jdbcTemplate.update(sql, params);

	}

	/*
	 * guarda un usuario en la bdd pendiente de aprobacion (non-Javadoc)
	 * 
	 * @see
	 * ar.edu.unlam.diit.scaw.daos.UsuarioDao#guardarUnUsuarioEnLaBDD(ar.edu.
	 * unlam.diit.scaw.entities.Usuario)
	 */
	@Override
	public void guardarUnUsuarioEnLaBDD(Usuario usuarioAGuardar) {
		// INSERT INTO
		// usuario(NOMBRE,APELLIDO,CONTRASENA,TIPO,ESTAAPROBADO,NICKNAME) VALUES
		// ('admin','molaro','123','administrador',1,'admin');
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "INSERT INTO USUARIO(NOMBRE,APELLIDO,NICKNAME,CONTRASENA,TIPO,ESTAAPROBADO) VALUES (:nombre,:apellido,:nickname,:contraseña,:tipo,:estaAprobado)";
		params.put("nombre", usuarioAGuardar.getNombre());
		params.put("apellido", usuarioAGuardar.getApellido());
		params.put("nickname", usuarioAGuardar.getNickName());
		params.put("contraseña", usuarioAGuardar.getContrasena());
		params.put("tipo", "normal");
		params.put("estaAprobado", "0"); // es cero porque cuando crea un
											// usuario hay que aprobarlo
		System.out.println(sql);
		jdbcTemplate.update(sql, params);
	}

	/*
	 * Modifica los campos de un usuario con un id determinado en la bdd
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.edu.unlam.diit.scaw.daos.UsuarioDao#modificarUnUsuarioPorId(java.lang.
	 * Integer, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void modificarUnUsuarioPorId(Integer id, String nickname, String nombre, String apellido, String contraseña,
			String tipo, Integer estaAprobado) {

		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "UPDATE  USUARIO SET nickName = :nickname ,nombre= :nombre,apellido = :apellido,contrasena = :contraseña, tipo = :tipo,estaaprobado=:estaAprobado where id = :id";
		params.put("id", id.toString());
		params.put("nombre", nombre);
		params.put("apellido", apellido);
		params.put("nickname", nickname);
		params.put("contraseña", contraseña);
		params.put("tipo", tipo);
		params.put("estaAprobado", estaAprobado);
		//String sql = "UPDATE  USUARIO SET nickName = '"+nickname+ "',nombre= '"+nombre+"',apellido = '"+apellido+"',contrasena = '"+contraseña+"', tipo = '"+tipo +"',estaaprobado = '"+estaAprobado.toString()+"' where id = '"+id.toString()+"'";
		jdbcTemplate.update(sql, params);

	}
	
	
	
	/* Eliminar un usuario de la bdd
	 * (non-Javadoc)
	 * @see ar.edu.unlam.diit.scaw.daos.UsuarioDao#eliminarUnUsuarioPorId(java.lang.Integer)
	 */
	@Override
	public void eliminarUnUsuarioPorId(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "DELETE FROM USUARIO WHERE ID = :id";
		params.put("id", id);
		
		jdbcTemplate.update(sql, params);
		
	}

	/*
	 * devuelve la lista completa de usuarios (non-Javadoc)
	 * 
	 * @see ar.edu.unlam.diit.scaw.daos.UsuarioDao#getListaDeUsuarios()
	 */
	@Override
	public List<Usuario> getListaDeUsuarios() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USUARIO";
		List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
		return lista;
	}

	
	
	
	/*funcona con en conjunto con su servicio
	 *  (non-Javadoc)
	 * @see ar.edu.unlam.diit.scaw.daos.UsuarioDao#getListaDeUsuariosQueNoParticipenEnUnaTarea(java.lang.Integer)
	 */
	@Override
	public List<Usuario> getListaDeUsuariosQueNoParticipenEnUnaTareaYNickName(Integer idTarea,String nickName) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT USUARIO.ID, USUARIO.NOMBRE ,USUARIO.APELLIDO,USUARIO.CONTRASENA ,USUARIO.TIPO,USUARIO.ESTAAPROBADO ,USUARIO.NICKNAME FROM USUARIO where tipo <> 'administrador' AND NICKNAME LIKE  '"+nickName+"%'";
		System.out.println(sql);
		params.put("idTarea", idTarea.toString());
		params.put("nickName", nickName);
		List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
		return lista;
	}
	
	@Override
	public List<Usuario> getListaDeUsuariosQuePertenecenAUnaTarea(Integer idTarea) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT USUARIO.ID, USUARIO.NOMBRE ,USUARIO.APELLIDO,USUARIO.CONTRASENA ,USUARIO.TIPO,USUARIO.ESTAAPROBADO ,USUARIO.NICKNAME FROM (TAREA JOIN ACCEDETAREA  ON TAREA.ID = ACCEDETAREA .IDTAREA) JOIN USUARIO ON ACCEDETAREA.IDUSUARIO = USUARIO.ID  WHERE TAREA.ID = :idTarea";
		
		params.put("idTarea", idTarea.toString());
		List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
		return lista;	}
	
	
	@Override
	public List<Usuario> getListaDeUsuariosNormales() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USUARIO WHERE TIPO = 'normal'";
		
		
		List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
		return lista;	
	}
	
	@Override
	public void agregarUnParticipanteAUnTareaPorIdDeUsuarioYModo(Integer idUsuarioNuevo, String modo,Integer idTarea) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		String sql2 = "INSERT INTO ACCEDETAREA(IDUSUARIO,IDTAREA,MODO) VALUES (:idUsuario,:idTarea,:modo)";
		params.put("idUsuario", idUsuarioNuevo.toString());
		params.put("modo", modo);
		params.put("idTarea", idTarea.toString());
		jdbcTemplate.update(sql2, params);
		
	}
	
	@Override
	public void eliminarUnParticipanteAUnTareaPorIdDeUsuario(Integer idUsuarioEliminaro, Integer idTarea) {
	
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "DELETE FROM ACCEDETAREA WHERE IDUSUARIO = :idUsuario AND IDTAREA = :idTarea";
			params.put("idUsuario", idUsuarioEliminaro);
			params.put("idTarea", idTarea);
			jdbcTemplate.update(sql, params);
					
	}
	// geters
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final class UsuarioMapper implements RowMapper<Usuario> {

		// @Override
		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setApellido(rs.getString("apellido"));
			usuario.setContrasena(rs.getString("contrasena"));
			usuario.setTipo(rs.getString("tipo"));
			usuario.setEstaAprobado(rs.getInt("estaAprobado"));
			usuario.setNickName(rs.getString("nickName"));
			return usuario;
		}

	}

	

}

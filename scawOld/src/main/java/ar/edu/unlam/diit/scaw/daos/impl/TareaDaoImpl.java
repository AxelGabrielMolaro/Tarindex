package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ar.edu.unlam.diit.scaw.daos.TareaDao;
import ar.edu.unlam.diit.scaw.entities.AccedeTarea;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.TareaYAccede;
import ar.edu.unlam.diit.scaw.entities.Usuario;


public class TareaDaoImpl  implements TareaDao{
	
	@Autowired
	 NamedParameterJdbcTemplate jdbcTemplate;
	
	public TareaDaoImpl() {
		super();
	}
	
	/*obtiene todas las tareas por estado publica o privada
	 *  (non-Javadoc)
	 * @see ar.edu.unlam.diit.scaw.daos.TareaDao#getListaDeTareasPorEstado(java.lang.String)
	 */
	@Override
	public List<Tarea> getListaDeTareasPorEstado(String estadoPublicoOPrivado) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM TAREA WHERE ESTADO = :estado";
		params.put("estado", estadoPublicoOPrivado);
		List<Tarea> lista = jdbcTemplate.query(sql, params, new TareaMapper());
		return lista;
	}
	
	
	/**devuelve una tarea pro id si no la encuentra devuelve null
	 * @param id
	 * @return
	 */
	@Override
	public Tarea getTareaPorId(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM TAREA WHERE ID = :id";
		params.put("id", id);
		List<Tarea> lista = jdbcTemplate.query(sql, params, new TareaMapper());
		for(Tarea t:lista)
		{
			if(t.getId().equals(id))
			{
				
				return t;
			}
		}
		
		return null;	
		
		}
	
	/**trea una lista por id usuario estado de tarea y modo de accede
	 * @param idUsuario
	 * @param estadoTarea
	 * @param modoAccede
	 * @return
	 */
	@Override
	public List<Tarea> getListaDeTareasPorIdDeUsuarioEstadoYModo(Integer idUsuario, String estadoTarea,
			String modoAccede) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM TAREA INNER JOIN ACCEDETAREA ON TAREA.ID = ACCEDETAREA.IDTAREA WHERE ( ACCEDETAREA.IDUSUARIO = :idUsuario AND TAREA.ESTADO = :estado) AND ACCEDETAREA.MODO = :modo";
		params.put("idUsuario", idUsuario);
		params.put("estado", estadoTarea);
		params.put("modo", modoAccede);
		List<Tarea> lista = jdbcTemplate.query(sql, params, new TareaMapper());
		return lista;
	}
	
	/* trae una tare por idusuario y estado pendiente o completo
	 * (non-Javadoc)
	 * @see ar.edu.unlam.diit.scaw.daos.TareaDao#getListaDeTareasPorEstadoYIdUsuario(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Tarea> getListaDeTareasPorEstadoYIdUsuario(String estado, Integer idUsuario) {
		
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "SELECT * FROM TAREA INNER JOIN ACCEDETAREA ON TAREA.ID = ACCEDETAREA.IDTAREA WHERE ACCEDETAREA.IDUSUARIO = :idUsuario AND TAREA.ESTADO = :estado";
			params.put("idUsuario", idUsuario);
			params.put("estado", estado);
		
			List<Tarea> lista = jdbcTemplate.query(sql, params, new TareaMapper());
			return lista;
	}
	
	/**Trae todas las tareas
	 * @return
	 */
	@Override
	public List<Tarea> getTareas() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM TAREA ";

		List<Tarea> lista = jdbcTemplate.query(sql, params, new TareaMapper());
		
		return lista;
	}
	
	/**crea una tarea y un accede modo escritura con el usuarioActual
	 * @param tarea
	 * @param idUsuario
	 */
	@Override
	public void guardarUnaTareaEnLaBdd(Tarea tarea, Integer idUsuario) {
		
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "INSERT INTO TAREA(NOMBRE,DESCRIPCION,ESTADO,ACCESO,CREADOR) VALUES (:nombre,:descripcion,:estado,:acceso,:creador)";
			params.put("nombre", tarea.getNombre());
			params.put("descripcion", tarea.getDescripcion());
			params.put("estado", "pendiente");
			params.put("acceso", tarea.getAcceso());
			params.put("creador",tarea.getCreador());
			jdbcTemplate.update(sql, params);
			
			Map<String, Object> params2 = new HashMap<String, Object>();
			Integer cantidadTotalDeTareas = getTareas().size();
			Integer idTarea = cantidadTotalDeTareas ; 
			
			String sql2 = "INSERT INTO  ACCEDETAREA(IDUSUARIO,IDTAREA,MODO) VALUES (:idUsuario,:idTarea,:modo)";
			params2.put("idUsuario", idUsuario);
			params2.put("idTarea", idTarea);
			params2.put("modo", "escritura");
			jdbcTemplate.update(sql2, params2);
		
			
		}

		/* ASIGNA UNA TAREA
		 * (non-Javadoc)
		 * @see ar.edu.unlam.diit.scaw.daos.TareaDao#asignarUnaTarea(ar.edu.unlam.diit.scaw.entities.Tarea, java.lang.Integer)
		 */
		@Override
		public void asignarUnaTarea(Tarea tarea, Integer idUsuario) {
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "INSERT INTO TAREA(NOMBRE,DESCRIPCION,ESTADO,ACCESO,CREADOR) VALUES (:nombre,:descripcion,:estado,:acceso,:creador)";
			params.put("nombre", tarea.getNombre());
			params.put("descripcion", tarea.getDescripcion());
			params.put("estado", "pendiente");
			params.put("acceso", tarea.getAcceso());
			params.put("creador",tarea.getCreador());
			jdbcTemplate.update(sql, params);
			
			Map<String, Object> params2 = new HashMap<String, Object>();
			Integer cantidadTotalDeTareas = getTareas().size();
			Integer idTarea = cantidadTotalDeTareas ; 
			
			String sql2 = "INSERT INTO  ACCEDETAREA(IDUSUARIO,IDTAREA,MODO,ESASIGNADA) VALUES (:idUsuario,:idTarea,:modo,:esAsignada)";
			params2.put("idUsuario", idUsuario);
			params2.put("idTarea", idTarea);
			params2.put("modo", "escritura");
			params2.put("esAsignada", 1);
			jdbcTemplate.update(sql2, params2);
			
		}
		
		/**Modifica una tarea menos el id y el creador
		 * @param id
		 * @param nombre
		 * @param descripcion
		 * @param acceso
		 * @param estado
		 */
		@Override
		public void modificarUnaTareaPorId(Integer id, String nombre, String descripcion, String acceso, String estado) {
			
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "UPDATE  TAREA SET NOMBRE = :nombre ,DESCRIPCION= :descripcion,ESTADO = :estado,ACCESO = :acceso where id = :id";
			params.put("id", id.toString());
			params.put("nombre", nombre);
			params.put("descripcion", descripcion);
			params.put("acceso", acceso);
			params.put("estado", estado);
		
			jdbcTemplate.update(sql, params);
		}
		
		@Override
		public void eliminarUnaTareaDeLaBddPorIdDeTarea(Integer idTarea) {
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "DELETE FROM TAREA WHERE ID = :idTarea";
			
			params.put("idTarea", idTarea.toString());
			
			jdbcTemplate.update(sql, params);
			
			
		}
		
		@Override
		public void setearElEstadoDeUnaTareaPorIdTarea(Integer idTarea, String estado) {
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "UPDATE  TAREA SET ESTADO = :estado where id = :idTarea";
			params.put("estado", estado);
			params.put("idTarea", idTarea.toString());
			jdbcTemplate.update(sql, params);
		}
	
		@Override
		public List<Tarea> getListaDeTareasAsignadas(Integer id) {

			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "SELECT * FROM TAREA INNER JOIN ACCEDETAREA ON TAREA.ID = ACCEDETAREA.IDTAREA WHERE ACCEDETAREA.IDUSUARIO = :idUsuario and ACCEDETAREA.ESASIGNADA = 1";
			params.put("idUsuario", id);
			
		
			List<Tarea> lista = jdbcTemplate.query(sql, params, new TareaMapper());
			return lista;
			
		}
		
	@Override
	public List<Tarea> getListaDeTareasAnonimas() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM TAREA WHERE ACCESO = 'publica'";
		System.out.println(sql);
		List<Tarea> lista = jdbcTemplate.query(sql, params, new TareaMapper());
		return lista;
	}
	
	
	//
	
	public static final class TareaYAccedeMapper implements RowMapper<TareaYAccede>
	{

		@Override
		public TareaYAccede mapRow(ResultSet rs, int rowNum) throws SQLException {
			TareaYAccede x = new TareaYAccede();
			x.getAccedeTareaUsuaroEn().setId(rs.getInt("id"));
			x.getAccedeTareaUsuaroEn().setIdTarea(rs.getInt("idTarea"));
			x.getAccedeTareaUsuaroEn().setIdUsuario(rs.getInt("idUsuario"));
			x.getAccedeTareaUsuaroEn().setModo(rs.getString("modo"));
			x.setId(rs.getInt("id"));
			x.setAcceso(rs.getString("acceso"));
			x.setDescripcion(rs.getString("descripcion"));
			x.setNombre(rs.getString("nombre"));
			x.setEstado(rs.getString("estado"));
			return x;
		}
		
		
	}
	
	

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	//TAREA ROW
	public static final class TareaMapper implements RowMapper<Tarea>
	{

		@Override
		public Tarea mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tarea tarea= new Tarea();
			tarea.setId(rs.getInt("id"));
			tarea.setAcceso(rs.getString("acceso"));
			tarea.setDescripcion(rs.getString("descripcion"));
			tarea.setNombre(rs.getString("nombre"));
			tarea.setEstado(rs.getString("estado"));
			tarea.setCreador(rs.getInt("creador"));
			
			return tarea;
		}
		
	}
	
	//acceso tarea row
	public static final class AccesoMapper implements RowMapper<AccedeTarea>
	{

		@Override
		public AccedeTarea mapRow(ResultSet rs, int rowNum) throws SQLException {
			AccedeTarea accedeTarea = new AccedeTarea();
			accedeTarea.setId(rs.getInt("id"));
			accedeTarea.setIdTarea(rs.getInt("idTarea"));
			accedeTarea.setIdUsuario(rs.getInt("idUsuario"));
			accedeTarea.setModo(rs.getString("modo"));
			accedeTarea.setEsAsignada(rs.getInt("esAsignada"));
			
			return accedeTarea;
		}
		
		
	}
}

package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.daos.TareaDao;
import ar.edu.unlam.diit.scaw.daos.impl.TareaDaoImpl;
import ar.edu.unlam.diit.scaw.entities.AccedeTarea;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.TareaService;

public class TareaServiceImpl implements TareaService{

	@Autowired
	TareaDaoImpl tareaDao = new TareaDaoImpl();
	
	@Override
	public void eliminarUnaTareaDeLaBddPorIdDeTarea(Integer idTarea) {
		tareaDao.eliminarUnaTareaDeLaBddPorIdDeTarea(idTarea);
	}
	
	@Override
	public List<Tarea> getListaDeTareasPorEstado(String estadoPublicoOPrivado) {
		// TODO Auto-generated method stub
		return tareaDao.getListaDeTareasPorEstado(estadoPublicoOPrivado);
	}
	
	@Override
	public List<Tarea> getListaDeTareasPorIdDeUsuarioEstadoYModo(Integer idUsuario, String estadoTarea,
			String modoAccede) {
		// TODO Auto-generated method stub
		return tareaDao.getListaDeTareasPorIdDeUsuarioEstadoYModo(idUsuario, estadoTarea, modoAccede);
	}
	
	@Override
	public List<Tarea> getListaDeTareasPorEstadoYIdUsuario(String estado, Integer idUsuario) {
		// TODO Auto-generated method stub
		return tareaDao.getListaDeTareasPorEstadoYIdUsuario(estado, idUsuario);
	}
	
	@Override
	public void guardarUnaTareaEnLaBdd(Tarea tarea, Integer idUsuario) {
		tareaDao.guardarUnaTareaEnLaBdd(tarea, idUsuario);
		
	}
	
	@Override
	public void modificarUnaTareaPorId(Integer id, String nombre, String descripcion, String acceso, String estado) {
		tareaDao.modificarUnaTareaPorId(id, nombre, descripcion, acceso, estado);
		
	}
	
	@Override
	public void setearElEstadoDeUnaTareaPorIdTarea(Integer idTarea, String estado) {
		tareaDao.setearElEstadoDeUnaTareaPorIdTarea(idTarea, estado);
		
	}
	
	@Override
	public Tarea getTareaPorId(Integer id) {
		// TODO Auto-generated method stub
		return tareaDao.getTareaPorId(id);
	}
	
	@Override
	public List<Tarea> getTareas() {
		// TODO Auto-generated method stub
		return tareaDao.getTareas();
	}
	
	@Override
	public void asignarUnaTarea(Tarea tarea, Integer idUsuario) {
		tareaDao.asignarUnaTarea(tarea, idUsuario);
	}
	
	@Override
	public List<Tarea> getListaDeTareasAsignadas(Integer id) {
		// TODO Auto-generated method stub
		return tareaDao.getListaDeTareasAsignadas(id);
	}
	
	@Override
	public List<Tarea> getListaDeTareasAnonimas() {
		// TODO Auto-generated method stub
		return tareaDao.getListaDeTareasAnonimas();
	}
	 
}

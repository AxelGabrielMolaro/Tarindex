package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.AccedeTarea;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface TareaDao {

	public List<Tarea> getTareas();
	
	public Tarea getTareaPorId(Integer id);
	
	public List<Tarea> getListaDeTareasPorEstado(String estadoPublicoOPrivado);//no la uso creo
	
	public List<Tarea> getListaDeTareasPorEstadoYIdUsuario(String estado,Integer idUsuario);
	
	//public List<Tarea> getListaDeTareasPorIdDeUsuarioYEstado(Integer idUsuario,String estadoTarea);//me devuelve las pendientes y las realizadas por id de usuario
	
	
	public List<Tarea> getListaDeTareasPorIdDeUsuarioEstadoYModo(Integer idUsuario,String estadoTarea,String modoAccede);
	
	public void setearElEstadoDeUnaTareaPorIdTarea(Integer idTarea,String estado);
	
	public void eliminarUnaTareaDeLaBddPorIdDeTarea(Integer idTarea);
	
	public void guardarUnaTareaEnLaBdd(Tarea tarea , Integer idUsuario);
	
	
	public void modificarUnaTareaPorId(Integer id,String nombre,String descripcion,String acceso,String estado);
	
	//asignar tarea
		public void asignarUnaTarea(Tarea tarea , Integer idUsuario);
		
	//asignar tarea
		public  List<Tarea>  getListaDeTareasAsignadas(Integer id);
		
		// tareas anonimas
				public  List<Tarea>  getListaDeTareasAnonimas();
	
}

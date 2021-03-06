package ar.edu.unlam.diit.scaw.beans;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hsqldb.lib.HashSet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.AccedeTarea;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.TareaYAccede;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.TareaService;
import ar.edu.unlam.diit.scaw.services.UsuarioService;


@ManagedBean(name="tareaBean",eager=true)
@ApplicationScoped
public class TareaBean extends UsuarioBean implements Serializable  {
	
	private static final long serialVersionUID = 10L;
	
	
	//manejo de errores
	private String tareaErrorMensaje1;

	//participante 
	private Integer idTareaABM;
	private Integer	participanteId;//es para asignar tareas
	private String participanteNombre;
	private String participanteNickName;
	private List<Usuario> listaDeParticipantes;
		
	java.util.HashSet<Usuario> listaDeParticipantesBuscados;
	private String modo;

	
	//usurio actual
	Integer idUsuarioActual ;
	

	
	
	private Integer idTarea;
	private String nombreTarea;
	private String descripcionTarea;
	private String accesoTarea;
	private String estadoTarea;
	private String creadorTarea;
	
	private Map listaAcceso ;// no lo use sacar si no sirve
	//Spring Inject
	
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
		TareaService tareaService = (TareaService) context.getBean("tareaService");
		UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioService");
	
	
		
	public TareaBean() {
		super();
		
		this.nombreTarea = null;
		this.descripcionTarea = null;
		this.accesoTarea =  null;
		this.estadoTarea = null;
		this.creadorTarea = null;
	}
	
	public void ResetearTareaBean() {
		
		
		this.nombreTarea = null;
		this.descripcionTarea = null;
		this.accesoTarea =  null;
		this.estadoTarea = null;
		this.creadorTarea = null;
	}
	
	public void resetearMensajesdeErrorTarea()
	{
		this.tareaErrorMensaje1 = "";
	}
	
	public String agregarTarea()
	{
		ResetearTareaBean();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idUsuarioActual");
		Integer idUsuario = Integer.parseInt(id2);
		this.idUsuarioActual = idUsuario;
		resetearMensajesdeErrorTarea();
		
		return "normal_agregar_tarea";
		
		
	}
	
	public String agregarTareaPost()
	{
		
		Tarea nuevaTarea = new Tarea();
		nuevaTarea.setCreador(this.idUsuarioActual);
		nuevaTarea.setNombre(this.nombreTarea);
		nuevaTarea.setDescripcion(this.descripcionTarea);
		nuevaTarea.setAcceso(this.accesoTarea);
		nuevaTarea.setEstado("pendiente");
		try {
			resetearMensajesdeErrorTarea();
			tareaService.guardarUnaTareaEnLaBdd(nuevaTarea, this.idUsuarioActual);
			ResetearTareaBean();
			return "normal_tareas_listado";
		
		} catch (Exception e) {

			this.tareaErrorMensaje1 = e.getMessage();
			return "normal_agregar_tarea";
		}
		
		
		
		
	}
	
	public String verTareas()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idUsuarioActual");
		Integer idUsuario = Integer.parseInt(id2);
		this.idUsuarioActual = idUsuario;
		return "normal_tareas_listado";
		
	}
	
	public String hacerPendienteUnaTrea()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idTareaABM");
		Integer idTarea = Integer.parseInt(id2);
		
		tareaService.setearElEstadoDeUnaTareaPorIdTarea(idTarea, "pendiente");
		return "normal_tareas_listado";
	}
	
	public String hacerCompletaUnaTrea()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idTareaABM");
		Integer idTarea = Integer.parseInt(id2);
		
		tareaService.setearElEstadoDeUnaTareaPorIdTarea(idTarea, "completa");
		return "normal_tareas_listado";
	}
	
	public String eliminarUnaTrea()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idTareaABM");
		Integer idTarea = Integer.parseInt(id2);
		tareaService.eliminarUnaTareaDeLaBddPorIdDeTarea(idTarea);
		
		return "normal_tareas_listado";
	}
	
	
	public String verTarea()
	{
		resetearMensajesdeErrorTarea();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idTareaABM");
		Integer idTareaABM = Integer.parseInt(id2);
		this.idTareaABM = idTareaABM;
		Tarea tarea = tareaService.getTareaPorId(idTareaABM);
		this.idTarea = tarea.getId();
		this.nombreTarea = tarea.getNombre();
		this.descripcionTarea = tarea.getDescripcion();
		this.accesoTarea = tarea.getAcceso();
	
		return "normal_tarea_ver";
	}
	public String verTareaLectura()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idTareaABM");
		Integer idTareaABM = Integer.parseInt(id2);
		this.idTareaABM = idTareaABM;
		Tarea tarea = tareaService.getTareaPorId(idTareaABM);
		this.idTarea = tarea.getId();
		this.nombreTarea = tarea.getNombre();
		this.descripcionTarea = tarea.getDescripcion();
		this.accesoTarea = tarea.getAcceso();
	
		return "normal_tarea_ver_lectura";
	}
	
	public String verTareaAnonima()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idTareaABM");
		Integer idTareaABM = Integer.parseInt(id2);
		this.idTareaABM = idTareaABM;
		Tarea tarea = tareaService.getTareaPorId(idTareaABM);
		this.idTarea = tarea.getId();
		this.nombreTarea = tarea.getNombre();
		this.descripcionTarea = tarea.getDescripcion();
		this.accesoTarea = tarea.getAcceso();
	
		return "normal_tarea_ver_anonima";
	}
	
	public String asignarTarea()
	{
		resetearElBean();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idUsuarioActual");
		
		Integer idUsuario = Integer.parseInt(id2);
		this.idUsuarioActual = idUsuario;
		resetearMensajesdeErrorTarea();
		return "normal_asignar_tarea";
	}
	public String asignarTareaPost()
	{
		Tarea nuevaTarea = new Tarea();
		nuevaTarea.setCreador(this.idUsuarioActual);
		nuevaTarea.setNombre(this.nombreTarea);
		nuevaTarea.setDescripcion(this.descripcionTarea);
		nuevaTarea.setAcceso(this.accesoTarea);
		nuevaTarea.setEstado("pendiente");
		
		try {
			ResetearTareaBean();
			resetearMensajesdeErrorTarea();
			tareaService.asignarUnaTarea(nuevaTarea, this.participanteId);
			return "normal_tareas_listado";
		} catch (Exception e) {
			this.tareaErrorMensaje1 = e.getMessage();
			return "normal_asignar_tarea";
		}
		
		
		
	}
	
	public List<Tarea> getListaDeTareasAsignadas() {
		return tareaService.getListaDeTareasAsignadas(this.idUsuarioActual);
	}
	
	public List<Tarea> getListaDeTareasAnonimas() {
		return tareaService.getListaDeTareasAnonimas();
	}
	
	public List<Usuario> getListaDeParicipantes() {
		return usuarioService.getListaDeUsuariosQuePertenecenAUnaTarea(this.idTareaABM);
	}
	
	
	
	public List<Tarea> getListaDeTareasCompletas()
	{
				return tareaService.getListaDeTareasPorEstadoYIdUsuario("completa", this.idUsuarioActual);
	}
	
	
	
	public List<Tarea> getListaDeTareasPendientesEscritura()
	{
	
		return tareaService.getListaDeTareasPorIdDeUsuarioEstadoYModo(this.idUsuarioActual, "pendiente", "escritura");
		
	}
	
	public List<Tarea> getListaDeTareasPendientesLectura()
	{
	
		return tareaService.getListaDeTareasPorIdDeUsuarioEstadoYModo(this.idUsuarioActual, "pendiente", "lectura");
		
	}
	
	public String buscarParticipantePost()
	{
		
		return "normal_buscar_participante";
	}
	
	public List<Usuario> getlistaDeParicipantesBuscados() {
		
		try {
			resetearMensajesdeErrorTarea();
			return usuarioService.getListaDeUsuariosQueNoParticipenEnUnaTareaYNickName(this.idTareaABM, this.participanteNickName);
		} catch (Exception e) {
			this.tareaErrorMensaje1 = e.getMessage();
			 List<Usuario> listaVacia = new ArrayList<>();
			return listaVacia;
		}
		
	}
	
	
	public String agregarParicipanteAUnaTarea()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("participanteABM");
		Integer participanteABM = Integer.parseInt(id2);
		usuarioService.agregarUnParticipanteAUnTareaPorIdDeUsuarioYModo(participanteABM, this.modo, this.idTarea);
		return "normal_tarea_ver";
	}
	
	public String eliminarParicipanteAUnaTarea()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("participanteABM");
		Integer participanteABM = Integer.parseInt(id2);
		usuarioService.eliminarUnParticipanteAUnTareaPorIdDeUsuario(participanteABM, this.idTarea);
		if(participanteABM.equals(this.idUsuarioActual))
		{
			return "normal_tareas_listado";
		}
		
		return "normal_tarea_ver";
	
	}
	
	public String editarTareaPost()
	{
		try {
			
			resetearMensajesdeErrorTarea();
			tareaService.modificarUnaTareaPorId(this.idTarea, this.nombreTarea, this.descripcionTarea, this.accesoTarea, this.estadoTarea);
			return "normal_tareas_listado";
		} catch (Exception e) {
			this.tareaErrorMensaje1 = e.getMessage();
			return  "normal_editar_tarea";
		}
		
		
	}
	

	public String getParticipanteNombre() {
		return participanteNombre;
	}

	public void setParticipanteNombre(String participanteNombre) {
		this.participanteNombre = participanteNombre;
	}

	public List<Usuario> getListaDeParticipantes() {
		return listaDeParticipantes;
	}

	public void setListaDeParticipantes(List<Usuario> listaDeParticipantes) {
		this.listaDeParticipantes = listaDeParticipantes;
	}

	public java.util.HashSet<Usuario> getListaDeParticipantesBuscados() {
		return listaDeParticipantesBuscados;
	}

	public void setListaDeParticipantesBuscados(java.util.HashSet<Usuario> listaDeParticipantesBuscados) {
		this.listaDeParticipantesBuscados = listaDeParticipantesBuscados;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public Integer getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Integer idTarea) {
		this.idTarea = idTarea;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	public String getDescripcionTarea() {
		return descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}

	public String getAccesoTarea() {
		return accesoTarea;
	}

	public void setAccesoTarea(String accesoTarea) {
		this.accesoTarea = accesoTarea;
	}

	public String getEstadoTarea() {
		return estadoTarea;
	}

	public void setEstadoTarea(String estadoTarea) {
		this.estadoTarea = estadoTarea;
	}


	public String getCreadorTarea() {
		return creadorTarea;
	}

	public void setCreadorTarea(String creadorTarea) {
		this.creadorTarea = creadorTarea;
	}

	public Map getListaAcceso() {
		return listaAcceso;
	}

	public void setListaAcceso(Map listaAcceso) {
		this.listaAcceso = listaAcceso;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public TareaService getTareaService() {
		return tareaService;
	}

	public void setTareaService(TareaService tareaService) {
		this.tareaService = tareaService;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdUsuarioActual() {
		return idUsuarioActual;
	}

	public void setIdUsuarioActual(Integer idUsuarioActual) {
		this.idUsuarioActual = idUsuarioActual;
	}

	public Integer getIdTareaABM() {
		return idTareaABM;
	}

	public void setIdTareaABM(Integer idTareaABM) {
		this.idTareaABM = idTareaABM;
	}

	public String getParticipanteNickName() {
		return participanteNickName;
	}

	public void setParticipanteNickName(String participanteNickName) {
		this.participanteNickName = participanteNickName;
	}

	public Integer getParticipanteId() {
		return participanteId;
	}

	public void setParticipanteId(Integer participanteId) {
		this.participanteId = participanteId;
	}

	public String getTareaErrorMensaje1() {
		return tareaErrorMensaje1;
	}

	public void setTareaErrorMensaje1(String tareaErrorMensaje1) {
		this.tareaErrorMensaje1 = tareaErrorMensaje1;
	}


	
	
	
	
	
	
}

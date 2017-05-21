package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.jsf.FacesContextUtils;

import ar.edu.unlam.diit.scaw.entities.TareaYAccede;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.PersonService;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import ar.edu.unlam.diit.scaw.services.impl.UsuarioServiceImpl;

@ManagedBean(name = "usuarioBean", eager = true)
@SessionScoped
public class UsuarioBean implements Serializable {

	
	
	
	// BAJA Y MODI
	private Integer idUsuarioABM;
	private String nombreABM ;
	private String apellidoABM;
	private String contrasenaABM ;
	private String tipoABM ;
	private Integer estaAprobadoABM;
	private String nickNameABM ;
	private String mensajeDeError ;
	private String mensajeError2;
	
	private static final long serialVersionUID = 1L;
	private Integer id  ;
	private String nombre ;
	private String apellido;
	//private String contraseña ;//con ñ nome anda
	private String contrasena ;
	private String tipo ;
	private Integer estaAprobado;
	private String nickName ;

	// Spring Inject

	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
	UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioService");

	// UsuarioServiceImpl usuarioService= new UsuarioServiceImpl();

	public UsuarioBean() {
		super();
		this.nombre = null;
		this.apellido = null;
		this.contrasena = null;
		this.tipo = null;
		this.estaAprobado = null;
		this.nickName = null;

	}

	/**************************/
	public void resetearElBean()
	{
		this.nombre = null;
		this.apellido = null;
		this.contrasena = null;
		this.tipo = null;
		this.estaAprobado = null;
		this.nickName = null;
		this.nombreABM = null;
		this.apellidoABM = null;
		this.contrasenaABM = null;
		this.tipoABM = null;
		this.estaAprobadoABM = null;
		this.nickNameABM = null;
		this.mensajeDeError ="";
		this.mensajeError2 = "";
	}
	/**
	 * Resetea los msj de error para que no queden todos en pantalla 
	 */
	public void resetearMensajesDeError ()
	{
		this.mensajeDeError ="";
		this.mensajeError2 = "";
	}
	
	/**es un metodo que si te da ok devuelve un usuario y seteea los valores de un usuario
	 * @author molaro
	 * 
	 * @return
	 */
	public String login() {
		

		Usuario usuarioBuscado;
		try {
			usuarioBuscado = usuarioService.login(this.nickName, this.contrasena);
			if (usuarioBuscado == null) {
				resetearElBean();
				return "index";

				
			} else if(usuarioBuscado.getEstaAprobado()==0) {
				resetearElBean();
				return "index";
			}
			else if (usuarioBuscado.getTipo().equals("normal"))
			{
				
				this.id = usuarioBuscado.getId();
				this.nickName = usuarioBuscado.getNickName();
				this.nombre = usuarioBuscado.getNombre();
				this.apellido = usuarioBuscado.getApellido();
				this.tipo = usuarioBuscado.getTipo();
				this.estaAprobado = usuarioBuscado.getEstaAprobado();
				
				return "normalHome";
			}
			else
			{
				this.id = usuarioBuscado.getId();
				this.nickName = usuarioBuscado.getNickName();
				this.nombre = usuarioBuscado.getNombre();
				this.apellido = usuarioBuscado.getApellido();
				this.tipo = usuarioBuscado.getTipo();
				this.estaAprobado = usuarioBuscado.getEstaAprobado();
				return "administradorHome";
				
			}
			
		} catch (Exception e) {
			resetearMensajesDeError();
			this.mensajeError2 = e.getMessage();
			
		}
		
		
		return "index";
		
		
		
			

	}
	
	public String cerrarSesion()
	{
		resetearElBean();
		return "index";
	}
	
	/**traer la lista de los usuairos para ser aprobados
	 * @author molaro
	 * @return
	 */
	public List<Usuario> getListaDeUsuariosPendientesDeAprobacion()
	{
		return usuarioService.getListaDeUsuariosQueNoEstanAprobados();
		
	}
	
	/**Es para no editarse a si mismo
	 * @return
	 */
	public List<Usuario> getListaDeUsuariosMenosElUsuarioActual()
	{
		return usuarioService.getListaDeUsuariosMenosElUsuarioActual(this.id);
		
	}
	
	/**Para llenar el combo de asignar tarea
	 * @return
	 */
	public List<Usuario> getListaDeUsuariosMenosElUsuarioActualYEsTipoNormal()
	{
		resetearMensajesDeError();

		return usuarioService.getListaDeUsuariosMenosElUsuarioActualYPorTipo(this.id,"normal");
		
	}
	
	/**aprueba un usuario y redirige a la lista de los usuarios pendientes
	 * @return
	 */
	public String aprobarUnUsuario()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idUsuarioAAparobar");
		Integer idUsuarioAAparobar = Integer.parseInt(id2);
		
		
		usuarioService.seterElValorDeAprobadoDeUnUsuario(idUsuarioAAparobar, 1);
		return "administrador_usuariosNoAprobados_listado";
	}
	
	/**rECHAZA UN USUARIO Y LO BORRA
	 * @return
	 */
	public String rechazarUnUsuario()
	{
		 Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id2 = params.get("idUsuarioARechazar");
		Integer idUsuarioAEliminar = Integer.parseInt(id2);
		usuarioService.eliminarUnUsuarioPorId(idUsuarioAEliminar);
		return " administrador_usuariosNoAprobados_listado ";
	}
	
	/**Esto recibe el id del usuario a editar y te redirige al fromulario de edicion
	 * @return
	 */
	public String editarUsuario()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id2 = params.get("idUsuarioAEditar");
		Integer idUsuarioAEditar = Integer.parseInt(id2);
		Usuario usuarioAEditar = usuarioService.getUsuarioPorId(idUsuarioAEditar);
		this.idUsuarioABM = usuarioAEditar.getId();
		this.nombreABM = usuarioAEditar.getNombre();
		this.apellidoABM = usuarioAEditar.getApellido();
		this.contrasenaABM = usuarioAEditar.getContrasena();
		this.tipoABM = usuarioAEditar.getTipo();
		this.estaAprobadoABM = usuarioAEditar.getEstaAprobado();
		this.nickNameABM = usuarioAEditar.getNickName();

		

		return "administrador_editar_usuario";
	}
	
	/**
	 * @return
	 */
	public String editarUsuario2()
	{
		try {
			usuarioService.modificarUnUsuarioPorId(this.idUsuarioABM, this.nickNameABM, this.nombreABM, this.apellidoABM, this.contrasenaABM, this.tipoABM, this.estaAprobadoABM);
	
		} catch (Exception e) {
			resetearMensajesDeError();
			this.mensajeDeError = e.getMessage();
			
			return "administrador_editar_usuario";
		}
		return "administrador_usuarios_listado";
	}
	
	/**Crea un usuario si el nick no existe ya
	 * @return
	 */
	public String registrase()
	{
		
		try {
	
			
			Usuario usuarioNuevo = new Usuario();
			usuarioNuevo.setNombre(this.nombreABM);
			usuarioNuevo.setApellido(this.apellidoABM);
			usuarioNuevo.setNickName(this.nickNameABM);
			usuarioNuevo.setContrasena(this.contrasenaABM);
			
			
			this.mensajeDeError="";
			
			usuarioService.guardarUnUsuarioEnLaBDD(usuarioNuevo);
			resetearElBean();
			return "index";
			
		
		} catch (Exception e) {
			resetearMensajesDeError();
			this.mensajeDeError = e.getMessage();
			return "index";
		}
	}
	
	
	/***********************/

	public Integer getIdUsuarioABM() {
		return idUsuarioABM;
	}



	public void setIdUsuarioABM(Integer idUsuarioABM) {
		this.idUsuarioABM = idUsuarioABM;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	



	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getEstaAprobado() {
		return estaAprobado;
	}

	public void setEstaAprobado(Integer estaAprobado) {
		this.estaAprobado = estaAprobado;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public String getMensajeError2() {
		return mensajeError2;
	}

	public void setMensajeError2(String mensajeError2) {
		this.mensajeError2 = mensajeError2;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombreABM() {
		return nombreABM;
	}

	public void setNombreABM(String nombreABM) {
		this.nombreABM = nombreABM;
	}

	public String getApellidoABM() {
		return apellidoABM;
	}

	public void setApellidoABM(String apellidoABM) {
		this.apellidoABM = apellidoABM;
	}

	public String getContrasenaABM() {
		return contrasenaABM;
	}

	public void setContrasenaABM(String contrasenaABM) {
		this.contrasenaABM = contrasenaABM;
	}

	public String getTipoABM() {
		return tipoABM;
	}

	public void setTipoABM(String tipoABM) {
		this.tipoABM = tipoABM;
	}

	public Integer getEstaAprobadoABM() {
		return estaAprobadoABM;
	}

	public void setEstaAprobadoABM(Integer estaAprobadoABM) {
		this.estaAprobadoABM = estaAprobadoABM;
	}

	public String getNickNameABM() {
		return nickNameABM;
	}

	public void setNickNameABM(String nickNameABM) {
		this.nickNameABM = nickNameABM;
	}

	public String getMensajeDeError() {
		return mensajeDeError;
	}

	public void setMensajeDeError(String mensajeDeError) {
		this.mensajeDeError = mensajeDeError;
	}

}

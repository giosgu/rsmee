package action;

import java.io.Serializable;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.EstadoUsuario;
import model.Genero;
import model.Paciente;
import model.Rol;
import model.TipoDocumento;
import model.TipoPaciente;
import model.Usuario;
import security.PasswordManager;
import validador.ValidacionesIngresoPaciente;
import dao.PacienteDao;
import dao.ParametrosDao;
import dao.RolDao;
import dao.UsuarioDao;
import excepcion.ValidationServiceException;

@ConversationScoped
@Stateful
@Named
public class AltaPacienteAction extends AbstractActionBean implements Serializable{
	
    //@Logger private Log log;

    /**
	 * 
	 */
	private static final long serialVersionUID = 8712364822892370919L;
	private Boolean isPrimerRender =Boolean.TRUE;
    private Paciente paciente; 
    @Inject
    private Usuario usuario;
    private String generoId;
    private String tipoDocumentoId;
    private String fechaNacimiento;
    private String confirmarContrasenia;
    private boolean aceptoTerminos;
	@Inject
    ParametrosDao parametroDao;
	@Inject
	InvitacionPacienteAction invitacionPaciente;
	@Inject
	PacienteDao pacienteDao;
	@Inject
	UsuarioDao usuarioDao;
	@Inject
	ValidacionesIngresoPaciente validacionesUsuario;
	@Inject
	RolDao rolList;
	
	public String getGeneroId() {
		return generoId;
	}
	public void setGeneroId(String generoId) {
		this.generoId = generoId;
	}

	@Inject
    private transient Conversation conversation;
    
    public void preRenderview(){   	
    	if(isPrimerRender){
	    	final FacesContext fc = FacesContext.getCurrentInstance();
	    	fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  ResourceBundle.getBundle("messages").getString("usuario.amb.mensaje.registracion.bienvenida"), "Sarazasasa"));
	    	fc.getPartialViewContext().getRenderIds().add("globalMessage");
	    	isPrimerRender = Boolean.FALSE;
    	}

    }
	@PostConstruct
    public void init(){   	
    	//TODO Agregar captcha
//    	CaptchaCustomBean captchaCustomBean = (CaptchaCustomBean) Component.getInstance(CaptchaCustomBean.class);
//    	captchaCustomBean.init();
        usuario = new model.Usuario();
        paciente = new Paciente();
    }
    
    public String altaPacienteAction() throws IllegalStateException, SecurityException {
    	
    	usuario.setPaciente(paciente);
		usuario.setCorreoAlternativo(usuario.getIdUsuario());

		Usuario administrador = usuarioDao.getObjectByID(new Long(1));

		//modificaciones task id 30 - simplificar alta pacientes
		usuario.setPrestador(administrador.getPrestador());

    	//Imagen Defalult de Perfil
    	String pathImagenPerfilDefault;
    	Genero genero = new Genero(generoId);
    	usuario.setGenero(genero);
    	if(usuario.getGenero().getCodigo().compareToIgnoreCase(model.Genero.STR_MASCULINO)==0)
    		pathImagenPerfilDefault = parametroDao.descripcionParametroPorId(model.Parametros.IMAGEN_PERFIL_DEFAULT_HOMBRE);
    	else
    		pathImagenPerfilDefault = parametroDao.descripcionParametroPorId(model.Parametros.IMAGEN_PERFIL_DEFAULT_MUJER);
    	
    	usuario.setPathImagenPerfil(pathImagenPerfilDefault);
    	usuario.setTipoDocumento(new TipoDocumento(Long.parseLong(tipoDocumentoId)));

		validacionesUsuario.setUsuario(usuario);
		validacionesUsuario.setConfirmarContrasenia(confirmarContrasenia);	
		validacionesUsuario.setFechaNacimiento(fechaNacimiento);
		validacionesUsuario.setAceptoTerminos(aceptoTerminos);
		try {
			validacionesUsuario.validate();
		} catch (excepcion.ValidationServiceException e) {
			final FacesContext fc = FacesContext.getCurrentInstance();
	        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return "";
		}
		
    	usuario.setActivo(true);
    	usuario.setNumeroAfiliado("0"+usuario.getNumeroAfiliado());

		//Genero invitacion
		try{
			usuario.setEstadoUsuario(invitacionPaciente.crearInvitacion(usuario));			
		}catch (ValidationServiceException e) {
			usuario.setNumeroAfiliado(
					usuario.getNumeroAfiliado().substring(1, usuario.getNumeroAfiliado().length())
			);//saco el 0 para mostrar
			final FacesContext fc = FacesContext.getCurrentInstance();
	        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return "no-persist";
		}
		
		//Pasó la validación = Aceptó Términos y Condiciones
		usuario.setAceptoTerminosCondiciones(true);
		
    	
    	Set<Rol> roles = new HashSet<Rol>();
    	roles.add(rolList.getObjectByID(Rol.PACIENTE));
    	usuario.setRoles(roles);
  		PasswordManager.encryptPassword(usuario);
  		paciente.setTipoPaciente(TipoPaciente.ADULTO);

		pacienteDao.persist(paciente);
		usuarioDao.persist(usuario);
//		TODO agregar la notificacion
		//notificarAltaPaciente(usuario);//Notifico
		
		//Mensaje para el paciente
	
		String codEstadoUsuarioResultado = usuario.getEstadoUsuario().getCodigo();
		if(codEstadoUsuarioResultado.equals(EstadoUsuario.ESTADO_PRE_ACEPTADO.getCodigo())){
			//FIXME statusMessages.clear();
		}else if(codEstadoUsuarioResultado.equals(EstadoUsuario.ESTADO_PENDIENTE.getCodigo())){
	    	final FacesContext fc = FacesContext.getCurrentInstance();
	    	fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  
	    			usuario.getNombre()+" "+usuario.getApellido()+", tu usuario ha sido creado con éxito.",
	    			""));
	    	fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  
	    			"En las próximas horas estaremos validando tu suscripción y recibirás un e-mail confirmando la misma.",
	    			""));

			return "estadoPendiente";
		}
//		log.info("altaPacienteAction.altaPacienteAction() = estadoPreAceptado");

    	return "estadoPreAceptado";
	}    

	
/*	
	//Notifica al administrador el alta
	private void notificarAltaPaciente(Usuario nuevoUsuario){
		TipoNotificacionList tipoNotificacionList = (TipoNotificacionList) Component.getInstance(TipoNotificacionList.class);
		UsuarioList usuarioList = (UsuarioList) Component.getInstance(UsuarioList.class);
		
		//Tipo de notif p/admin según estado del paciente
		TipoNotificacion tipoNotificacion = new TipoNotificacion();		
		if(nuevoUsuario.getEstadoUsuario().getCodigo().equals(EstadoUsuario.ESTADO_PRE_ACEPTADO.getCodigo()))
			tipoNotificacion = tipoNotificacionList.getObjectByID(TipoNotificacion.TN_PACIENTE_NUEVO_ACEPTADO);		
		else if(nuevoUsuario.getEstadoUsuario().getCodigo().equals(EstadoUsuario.ESTADO_PENDIENTE.getCodigo()))
			tipoNotificacion = tipoNotificacionList.getObjectByID(TipoNotificacion.TN_PACIENTE_PENDIENTE);
		
		Notificacion notificacion = NotificacionBuilder.build(nuevoUsuario, usuarioList.getObjectByID(new Long(1)), tipoNotificacion, null);
		NotificacionHome notificacionHome = (NotificacionHome) Component.getInstance(NotificacionHome.class);
		notificacionHome.setInstance(notificacion);
		notificacionHome.persist();
		
		//envío mail
		EmailService emailService = (EmailService) Component.getInstance(EmailService.class);
		emailService.enviarNotificacion(500, tipoNotificacion.getMailPathTemplate(), notificacion, ((ParametrosList)
				Component.getInstance(ParametrosList.class)).descripcionParametroPorId(Parametros.URL_RSM));		
		
	}
  */
    
    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    public String getTipoDocumentoId() {
		return tipoDocumentoId;
	}
	public void setTipoDocumentoId(String tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getConfirmarContrasenia() {
		return confirmarContrasenia;
	}
	public void setConfirmarContrasenia(String confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}
	public boolean isAceptoTerminos() {
		return aceptoTerminos;
	}
	public void setAceptoTerminos(boolean aceptoTerminos) {
		this.aceptoTerminos = aceptoTerminos;
	}

}

package action;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.EstadoUsuario;
import model.Paciente;
import model.Rol;
import model.TipoPaciente;
import model.Usuario;
import security.PasswordManager;
import dao.PacienteDao;
import dao.ParametrosDao;
import dao.RolDao;

@Stateful
@Named
public class AltaPacienteAction extends AbstractActionBean implements Serializable{
	
    //@Logger private Log log;

    
    private Paciente paciente; 
    private Usuario usuario;
    
    @Inject
    public void init(){   	

    	//TODO Agregar captcha
//    	CaptchaCustomBean captchaCustomBean = (CaptchaCustomBean) Component.getInstance(CaptchaCustomBean.class);
//    	captchaCustomBean.init();
    	final FacesContext fc = FacesContext.getCurrentInstance();
//    	statusMessages.clear();
//    	statusMessages.addFromResourceBundle("usuario.amb.mensaje.registracion.bienvenida");
//		FIXME
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "usuario.amb.mensaje.registracion.bienvenida", ""));
        paciente = new model.Paciente();
        usuario = new model.Usuario();
    }
    
    public String altaPacienteAction() throws IllegalStateException, SecurityException {
    	
    	usuario.setPaciente(paciente);

//		Usuario administrador = ((UsuarioList) Component.getInstance(UsuarioList.class)).getObjectByID(new Long(1));
//		usuario.setPrestador(administrador.getPrestador());
		usuario.setCorreoAlternativo(usuario.getIdUsuario());

    	//Imagen Defalult de Perfil
//    	ParametrosList parametrosList = (ParametrosList) Component.getInstance(ParametrosList.class);
		ParametrosDao dao = new ParametrosDao();
    	String pathImagenPerfilDefault;
    	if(usuario.getGenero().getCodigo().compareToIgnoreCase(model.Genero.STR_MASCULINO)==0)
    		pathImagenPerfilDefault = dao.descripcionParametroPorId(model.Parametros.IMAGEN_PERFIL_DEFAULT_HOMBRE);
    	else
    		pathImagenPerfilDefault = dao.descripcionParametroPorId(model.Parametros.IMAGEN_PERFIL_DEFAULT_MUJER);
    	
    	usuario.setPathImagenPerfil(pathImagenPerfilDefault);
    	
		validador.ValidacionesIngresoUsuario validacionesUsuario = new validador.ValidacionesIngresoPaciente();
//		validacionesUsuario.setEntityManager(getEntityManager());
//		validacionesUsuario.setUsuario(usuarioHome.getInstance());
//		validacionesUsuario.setUsuarioList((UsuarioList) Component.getInstance(UsuarioList.class));
//		validacionesUsuario.setConfirmarContrasenia(altaPacienteForm.getConfirmarContrasenia());	
//		validacionesUsuario.setDiaNacimiento(altaPacienteForm.getDiaNacimiento());
//		validacionesUsuario.setMesNacimiento(altaPacienteForm.getMesNacimiento());
//		validacionesUsuario.setAnioNacimiento(altaPacienteForm.getAnioNacimiento());
//		validacionesUsuario.setAceptoTerminos(altaPacienteForm.isAceptoTerminos());
		try {
			validacionesUsuario.validate();
		} catch (excepcion.ValidationServiceException e) {
//			FIXME
			final FacesContext fc = FacesContext.getCurrentInstance();
	        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "usuario.amb.mensaje.registracion.bienvenida", ""));
			//fc.clear();
			//statusMessages.addFromResourceBundle(Severity.ERROR, e.getMessage());
	        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return "no-persist";
		}
		
    	usuario.setActivo(true);
    	usuario.setNumeroAfiliado("0"+usuario.getNumeroAfiliado());

		//Genero invitacion
    	//TODO generar invitacion
//		InvitacionPacienteAction invitacionPaciente = (InvitacionPacienteAction) Component.getInstance(InvitacionPacienteActionBean.class);
//		try{
//			usuarioHome.getInstance().setEstadoUsuario(invitacionPaciente.crearInvitacion(usuarioHome.getInstance()));			
//		}catch (ValidationServiceException e) {
//			usuarioHome.getInstance().setNumeroAfiliado(
//					usuarioHome.getInstance().getNumeroAfiliado().substring(1, usuarioHome.getInstance().getNumeroAfiliado().length())
//			);//saco el 0 para mostrar
//			statusMessages.clear();
//			statusMessages.addFromResourceBundle(Severity.ERROR, e.getMessage());
//			return "no-persist";
//		}
		
		//Pasó la validación = Aceptó Términos y Condiciones
		usuario.setAceptoTerminosCondiciones(true);
		
    	RolDao rolList = new RolDao();
    	Set<Rol> roles = new HashSet<Rol>();
    	roles.add(rolList.getObjectByID(Rol.PACIENTE));
    	usuario.setRoles(roles);
  		PasswordManager.encryptPassword(usuario);
  		paciente.setTipoPaciente(TipoPaciente.ADULTO);

  		PacienteDao pacienteDao = new PacienteDao();
		pacienteDao.save(paciente);
	
		//FIXME deberia ser usuario dao
		pacienteDao.save(usuario);
//		agregar la notificacion
		//notificarAltaPaciente(usuario);//Notifico
		
		//Mensaje para el paciente
	
		String codEstadoUsuarioResultado = usuario.getEstadoUsuario().getCodigo();
		if(codEstadoUsuarioResultado.equals(EstadoUsuario.ESTADO_PRE_ACEPTADO.getCodigo())){
			//FIXME statusMessages.clear();
		}else if(codEstadoUsuarioResultado.equals(EstadoUsuario.ESTADO_PENDIENTE.getCodigo())){
			//TODO  usuarioHome.registracionUsuario();
//	        log.info("altaPacienteAction.altaPacienteAction() = estadoPendiente");
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
}

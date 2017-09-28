package action;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;

import model.Especialidad;
import model.EstadoRelacion;
import model.Relacion;
import model.Usuario;
import validador.ValidacionesEnvioSolicitud;
import validador.Validador;
import dao.EspecialidadDao;
import dao.RelacionDao;
import dao.UsuarioDao;
import excepcion.ValidationServiceException;

@Named
@Stateful
@SessionScoped
public class BusquedaMedicoAction extends AbstractActionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1791916928669712570L;
	private String apellidoMedico;
	private Long especialidadSeleccionada;
	@Inject EspecialidadDao especialidadDao;
	private Usuario medicoSeleccionado;
	@Inject UsuarioDao usuarioDao;
	@Inject RelacionDao relacionList;
	@Resource
    private EJBContext context;
	
	public void init(){		
	}
	
	
	public String enviarSolicitud(Long codigoUsuarioDestino) throws IllegalStateException, SecurityException, SystemException{		
		

		Usuario usuarioDestino = usuarioDao.getObjectByID(codigoUsuarioDestino);
		Usuario usuarioLogueado = getUsuarioLogueado();
		
		//Validaciones
		List<Validador> validadores = new ArrayList<Validador>();		
		ValidacionesEnvioSolicitud validacionesEnvioSolicitud = new ValidacionesEnvioSolicitud();
		validacionesEnvioSolicitud.setUsuarioOrigen(usuarioLogueado);
		validacionesEnvioSolicitud.setUsuarioDestino(usuarioDestino);
		validadores.add(validacionesEnvioSolicitud);		
		try {
			for (Validador validador : validadores) {
				validador.validate();
			}
		} catch (ValidationServiceException e) {
			context.getUserTransaction().rollback();
			this.addFacesMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  
					e.getMessage(), "") );
		}
				
		//Debo ver si ya se envió una solicitud en sentido inverso
		StringBuffer msj = new StringBuffer("");
		Relacion relacionOrigenDestino = relacionList.obtenerRelacion(usuarioLogueado.getCodigo(),codigoUsuarioDestino);
		if(relacionOrigenDestino != null && relacionOrigenDestino.getEstadoRelacion().getCodigo().equals(EstadoRelacion.INICIAL)){
			//Notifico
/*			TipoNotificacionList tipoNotificacionList = (TipoNotificacionList) Component.getInstance(TipoNotificacionList.class);
			TipoNotificacion tipoNotificacion =  tipoNotificacionList.getObjectByID(TipoNotificacion.TN_RESPUESTA_SOLICITUD);
			
			Notificacion notificacion = NotificacionBuilder.build(getUsuarioSeleccionado(), relacionOrigenDestino.getOrigenUsuario(), tipoNotificacion, null);
			NotificacionHome notificacionHome = (NotificacionHome) Component.getInstance(NotificacionHome.class);
			notificacionHome.setInstance(notificacion);
			notificacionHome.persist();
			
			EmailService emailService = (EmailService) Component.getInstance(EmailService.class);
			emailService.enviarNotificacion(500, tipoNotificacion.getMailPathTemplate(), notificacion, ((ParametrosList)
					Component.getInstance(ParametrosList.class)).descripcionParametroPorId(Parametros.URL_RSM));			
*/			
			//Creo contactos y acepto sol
/*			SolicitudesForm solicitudesForm = (SolicitudesForm) Component.getInstance(SolicitudesFormBean.class);
			solicitudesForm.establecerContactos(relacionOrigenDestino);			
			relacionOrigenDestino.setEstadoRelacion(EstadoRelacion.ESTADO_ACEPTADA);
			relacionOrigenDestino.setNotificacion(notificacion);
			relacionHome.setInstance(relacionOrigenDestino);
			relacionHome.update();
			
			//Mensaje
			msj.append("Usted ya tenía una solicitud de ");
			msj.append(StringUtils.blankIfNull(usuarioDestino.getPrefijo())).append(" ").append(usuarioDestino.getNombre()).append(" ").append(usuarioDestino.getApellido()).append(", ");
			msj.append("por lo tanto ya se encuentran en contacto.");
*/		}else{
			if(relacionList.envieSolicitudPendiente(codigoUsuarioDestino, usuarioLogueado.getCodigo())){
				this.addFacesMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  
						"Usted ya envió una solicitud a este profesional.", "") );
				
				return "no-updated";
			}
			//Notifico
/*			TipoNotificacionList tipoNotificacionList = (TipoNotificacionList) Component.getInstance(TipoNotificacionList.class);
			TipoNotificacion tipoNotificacion =  tipoNotificacionList.getObjectByID(TipoNotificacion.TN_SOLICITUD);
			
			Notificacion notificacion = NotificacionBuilder.build(getUsuarioSeleccionado(), usuarioDestino, tipoNotificacion, null);
			NotificacionHome notificacionHome = (NotificacionHome) Component.getInstance(NotificacionHome.class);
			notificacionHome.setInstance(notificacion);
			notificacionHome.persist();
			
			EmailService emailService = (EmailService) Component.getInstance(EmailService.class);
			emailService.enviarNotificacion(500, tipoNotificacion.getMailPathTemplate(), notificacion, ((ParametrosList)
					Component.getInstance(ParametrosList.class)).descripcionParametroPorId(Parametros.URL_RSM));			
			
			Relacion relacionDestinoOrigen = relacionList.obtenerRelacion(codigoUsuarioDestino,usuarioLogueado.getCodigo());
			if(relacionDestinoOrigen == null)
				relacionDestinoOrigen = new Relacion();
			relacionDestinoOrigen.setEstadoRelacion(EstadoRelacion.ESTADO_INICIAL);
			relacionDestinoOrigen.setFechaCreacion(new Date());
			relacionDestinoOrigen.setNotificacion(notificacion);
			relacionDestinoOrigen.setOrigenUsuario(usuarioLogueado);
			relacionDestinoOrigen.setDestinoUsuario(usuarioDestino);
			relacionHome.setInstance(relacionDestinoOrigen);
			relacionHome.saveOrUpdate();
			//Mensaje
			msj.append("Se ha enviado su solicitud de contacto a ");
			msj.append(StringUtils.blankIfNull(usuarioDestino.getPrefijo())).append(" ").append(usuarioDestino.getNombre()).append(" ").append(usuarioDestino.getApellido()).append(". ");
			msj.append("Recibirá una notificación cuando la misma sea aceptada");
*/		}
/*		statusMessages.clear();
		statusMessages.add(msj.toString());
		limpiarFormBusqueda();
*/		return "updated";
	}

	
	public String getApellidoMedico() {
		return apellidoMedico;
	}

	public void setApellidoMedico(String apellidoMedico) {
		this.apellidoMedico = apellidoMedico;
	}

	public Long getEspecialidadSeleccionada() {
		return especialidadSeleccionada;
	}

	public void setEspecialidadSeleccionada(Long especialidadSeleccionada) {
		this.especialidadSeleccionada = especialidadSeleccionada;
	}

	public EspecialidadDao getEspecialidadDao() {
		return especialidadDao;
	}

	public void setEspecialidadDao(EspecialidadDao especialidadDao) {
		this.especialidadDao = especialidadDao;
	}


	public List<Especialidad> getEspecialidades() {
		return especialidadDao.findAll();
	}

	public Usuario getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	public void setMedicoSeleccionado(Usuario medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

	public List<Usuario> getMedicos() {
		return usuarioDao.obtenerMedicos();
	}
	
}

/*
 *******************************************************************************
 **  Archivo    : UserDataLogin.java
 **  Paquete    : com.octomind.rsm.security
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versi�n)
 **  Autor      : mazzca
 **  Fecha      : 18/01/2012 14:48:36
 *******************************************************************************
 **  Modificaciones/Correcciones
 **               Fecha       Responsable     Comentario
 **
 *******************************************************************************
 */
package security;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Startup;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Scope;

import model.Usuario;

import org.apache.commons.lang3.StringUtils;
//import org.jboss.seam.Component;
//import org.jboss.seam.annotations.Install;
//import org.jboss.seam.annotations.Scope;
//import org.jboss.seam.annotations.Startup;
//import org.jboss.seam.annotations.intercept.BypassInterceptors;
//import org.jboss.seam.faces.FacesMessages;
//import org.jboss.seam.log.LogProvider;
//import org.jboss.seam.log.Logging;

/**
 * @author mazzca
 *
 */
@Named
//@Scope(SESSION)
//@Install(precedence = APPLICATION)
//@BypassInterceptors
@Startup
//FIXME rehacer clase
public class UserDataLogin /*extends Identity */{
	
	private static final long serialVersionUID = 1L;
    //private static final LogProvider log = Logging.getLogProvider(UserDataLogin.class);
	private Usuario logUser;
	//No implementado
	private List<String> permisos = new ArrayList<String>();
	private boolean forzarCambioContrasenia;
	private boolean primerAcceso = true;	
	private Long codAccesoUsuario;
	private Usuario usuarioLogueadoPre;//Uss estado PRE
	
	public String login(){
		//usuarioLogueadoPre=null;
//		log.info("Login usuario " + this.getCredentials().getUsername());
//		log.info("Principal " + this.getPrincipal());
		
		boolean newLogUser=false;
/*		if(this.logUser != null && this.getCredentials().getUsername().equals(logUser.getIdUsuario()) ){
			log.info("LogUser not Null");
		}else{
			log.info("LogUser Null");
			newLogUser=true;
		}
*/		
		String resultadoLogin = ""; // super.login();
		if(StringUtils.isNotEmpty(resultadoLogin) && resultadoLogin.compareToIgnoreCase("loggedIn")==0){
			if(newLogUser){
				String userAgentString = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("User-Agent");
			//	UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
			//	AccesoUsuarioHome accesoUsuarioHome = (AccesoUsuarioHome) Component.getInstance(AccesoUsuarioHome.class);
				
			//	AccesoUsuario accesoUsuario = new AccesoUsuario();
			//	accesoUsuario.setFechaIngreso(new Date());				
				//accesoUsuario.setUsuario(logUser);
			//	accesoUsuario.setSistemaOperativo(userAgent.getOperatingSystem().getName());
//				accesoUsuario.setDispositivo(userAgent.getOperatingSystem().getDeviceType().toString());
//				accesoUsuario.setNavegador(userAgent.getBrowser().getGroup().toString());
//				accesoUsuario.setNavegadorDetalle(userAgent.getBrowser().getVersion(userAgentString).toString());
//				
//				accesoUsuarioHome.setInstance(accesoUsuario);
//				accesoUsuarioHome.persist();
//				setCodAccesoUsuario(accesoUsuarioHome.getInstance().getCodigo());
			}
//			FacesMessages.instance().clear();
//			FacesMessages.instance().add(this.mensajeBienvenida());
		}
		return resultadoLogin;
	}
	
	private String mensajeBienvenida() {
//		String mensaje = "Bienvenido, ";
//		if(this.getLogUser().getPrefijo()!=null){
//			mensaje = mensaje.concat(this.getLogUser().getPrefijo()+" ");
//		}
//		return mensaje.concat(this.getLogUser().getNombre()+" "+this.getLogUser().getApellido());
		return null;
	}
	
	public void cerrarAccesoUsuario() {
//		AccesoUsuarioHome accesoUsuarioHome = (AccesoUsuarioHome) Component.getInstance(AccesoUsuarioHome.class);
//		accesoUsuarioHome.setInstance(
//			((AccesoUsuarioList) Component.getInstance(AccesoUsuarioList.class)).getObjectByID(codAccesoUsuario)
//		);
//		accesoUsuarioHome.getInstance().setFechaEgreso(new Date());
//		accesoUsuarioHome.update();
	}
	
	public boolean hasRole(String role){
/*		if (!securityEnabled) return true;  
		Set<Rol> roles = logUser.getRoles();
		return CollectionUtils.exists(roles, RolUtils.tienePermiso(role));
*/		return true;		

	}
	
	public boolean isPrimerAcceso() {
		return primerAcceso;
	}

	public void setPrimerAcceso(boolean primerAcceso) {
		this.primerAcceso = primerAcceso;
	}


	public boolean checkPermiso(String idFuncionalidad){
		if (this.permisos == null || this.permisos.isEmpty())
			return false;
		else
			if (this.permisos.contains(idFuncionalidad))
				return true;
			else return false;
	}
	
	
	public void addPermiso(String idFuncionalidad){
		this.permisos.add(idFuncionalidad);
	}
	
	public Usuario getLogUser() {
		return logUser;
	}

	public void setLogUser(Usuario logUser) {
		this.logUser = logUser;
	}

	public List<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
	}

	public boolean isForzarCambioContrasenia() {
		return forzarCambioContrasenia;
	}

	public void setForzarCambioContrasenia(boolean forzarCambioContrasenia) {
		this.forzarCambioContrasenia = forzarCambioContrasenia;
	}

	public Long getCodAccesoUsuario() {
		return codAccesoUsuario;
	}
	public void setCodAccesoUsuario(Long codAccesoUsuario) {
		this.codAccesoUsuario = codAccesoUsuario;
	}

	public Usuario getUsuarioLogueadoPre() {
		return usuarioLogueadoPre;
	}
	public void setUsuarioLogueadoPre(Usuario usuarioLogueadoPre) {
		this.usuarioLogueadoPre = usuarioLogueadoPre;
	}
	
}

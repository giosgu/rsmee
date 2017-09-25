package action;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import model.EstadoUsuario;
import model.Usuario;
import security.PasswordManager;
import dao.UsuarioDao;

@ConversationScoped
@Stateful
@Named
public class LoginAction extends  AbstractActionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6360890729192483024L;
	private String nombreUsuario;
	private String contrasenia;
	@Inject
	private UsuarioDao usuarioDao;
	private static String LOGIN_INVALIDO = "loginInvalido";
	private static String LOGIN_OK = "loginOk";
	
	public String login(){
		Usuario u = null;
		try{
		  u = usuarioDao.getUsuario(nombreUsuario);
		}catch(NoResultException e){
			this.addFacesMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  
					ResourceBundle.getBundle("messages").getString("login.usuario.o.contrasenia.invalido"), "") );
			return LOGIN_INVALIDO;
			
		}
		try {
			if (!PasswordManager.isValid(contrasenia, u)){
				this.addFacesMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  
						ResourceBundle.getBundle("messages").getString("login.usuario.o.contrasenia.invalido"), "") );
				return LOGIN_INVALIDO;
			}
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			this.addFacesMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "") );
			return LOGIN_INVALIDO;
		}
		
		 if (!u.isActivo()){
			 this.addFacesMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  
						ResourceBundle.getBundle("messages").getString("login.usuario.inactivo"), "") );
				return LOGIN_INVALIDO;
		 }
		 
		 if(u.getEstadoUsuario().getCodigo().compareTo(EstadoUsuario.ESTADO_BAJA.getCodigo())==0){
			 this.addFacesMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  
						ResourceBundle.getBundle("messages").getString("login.usuario.estado.baja"), "") );
				return LOGIN_INVALIDO;

		 }

		 AbstractActionBean.getSession().setAttribute(USUARIO_LOGUEADO, u);
		
		return LOGIN_OK;
	}

	public String logout(){
		Usuario usuario = getUsuarioLogueado();
		usuario.setFechaModificacion(new Date());
		usuarioDao.merge(usuario);
    	HttpSession session = getSession();
    	session.invalidate();
    	return "logout";
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}

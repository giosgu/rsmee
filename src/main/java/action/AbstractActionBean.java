package action;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Usuario;

public abstract class AbstractActionBean {

	public static final String USUARIO = "USUARIO";
	public static final String USUARIO_PATH_IMAGEN_PERFIL = "USUARIO_PATH_IMAGEN_PERFIL";
	public static final String USUARIO_LOGUEADO = "USUARIO_LOGUEADO";
//	@In UserDataLogin identity;
	

    protected final static Usuario getUsuarioLogueado() {
      HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      if (session == null)
    	  return null;
      return (Usuario)session.getAttribute(USUARIO_LOGUEADO);
    }
	
	protected void addFacesMessage(String clientId, FacesMessage fm){
		final FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, fm);
	}
	
	public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
	
}

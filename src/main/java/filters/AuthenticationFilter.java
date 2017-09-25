package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

import org.apache.commons.lang3.StringUtils;

import decorator.UsuarioDecorator;
import action.AbstractActionBean;


@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthenticationFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain chain) throws IOException, ServletException {
	 try {
            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
            String reqURI = req.getRequestURI();
            
            Usuario usuario = this.getUsuarioLogueado(ses); 
            UsuarioDecorator usuarioDecorator = new UsuarioDecorator(usuario);
            
            //si está logueado y va al login, redirección al dashboard
            if(usuario != null && reqURI.contains("/login.xhtml")){
            	if(usuarioDecorator.isMedico()){
            		res.sendRedirect(req.getContextPath() + "/m/medico.xhtml");
            	}
            	if(usuarioDecorator.isMedico()){
            		res.sendRedirect(req.getContextPath() + "/p/paciente.xhtml");
            	}

            }
            //si la página es pública, pasa
            if(!isNavegacionPrivada(reqURI)){
            	chain.doFilter(request, response);
            	return;
            }
            
            //si no está logueado y la página no es pública, redirije al login TODO guardar la url original para después
            if(usuario == null){
            	res.sendRedirect(req.getContextPath() + "/login.xhtml");
            	return;
            }
            	
            
            //si es médico y accede a contenido de médico
            if(usuarioDecorator.isMedico() && this.isNavegacionMedica(reqURI) ){
            	chain.doFilter(request, response);
            	return;
            }
            
            //si es paciente y accede a contenido de paciente
            if(usuarioDecorator.isPaciente() && this.isNavegacionPaciente(reqURI) ){
            	chain.doFilter(request, response);
            	return;
            }

            
//            if ( reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && getUsuarioLogueado() != null)
//                                       || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource") )
//                   chain.doFilter(request, response);
//            else   // user didn't log in but asking for a page that is not allowed so take user to login page
//                   res.sendRedirect(req.getContextPath() + "/login.xhtml");  // Anonymous user. Redirect to login page

            //si llegó hasta acá, qué carajo querés hacer nene!!
            res.sendRedirect(req.getContextPath() + "/access.xhtml");
	 	}
	     catch(Throwable t) {
	         System.out.println( t.getMessage());
	     }
	

		
	}



	private boolean isNavegacionPrivada(String requestURI){
		return StringUtils.isNotEmpty(requestURI) 
				&& (requestURI.contains("/p/") || requestURI.contains("/m/") || requestURI.contains("/a/") || requestURI.contains("/f/"));
	}
	
	private boolean isNavegacionMedica(String requestURI){
		return requestURI.contains("/m/");
	}
	
	private boolean isNavegacionPaciente(String requestURI){
		return requestURI.contains("/p/");
	}
		
	private Usuario getUsuarioLogueado(HttpSession session) {
		if(session == null)
			return null;
		return (Usuario) session.getAttribute(AbstractActionBean.USUARIO_LOGUEADO);
	}

		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}

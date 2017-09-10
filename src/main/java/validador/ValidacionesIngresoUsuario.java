/*
 * Validador para la Registración de Usuarios tanto médicos como pacientes 
 * */
package validador;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Usuario;
import excepcion.ValidationServiceException;

//import org.jboss.seam.Component;
//
//import com.octomind.rsm.Excepciones.ValidationServiceException;
//import com.octomind.rsm.action.profesional.Validador;
//import com.octomind.rsm.home.usuario.UsuarioHome;
//import com.octomind.rsm.home.usuario.UsuarioList;
//import com.octomind.rsm.model.Usuario;
//
public class ValidacionesIngresoUsuario implements Validador, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4997162544619287773L;
	@Inject
	private EntityManager entityManager;
	protected model.Usuario usuario;
//	protected UsuarioDa usuarioList;
	
    //Fecha Nacimiento
    private String diaNacimiento;
    private String mesNacimiento;
    private String anioNacimiento;
	
    private String fechaNacimiento;
    
	private String confirmarContrasenia;
	
	//Aceptó términos y condiciones
    private boolean aceptoTerminos;
	
	public void validate() throws ValidationServiceException {
		
		//Términos y condiciones
		if(!isAceptoTerminos())
			throw new ValidationServiceException("Debe aceptar los términos y condiciones para poder registrarse, Muchas Gracias");
		
		//verifico que el usuario no exista en la base
		//FIXME
//		if(usuarioList.getUsuario(usuario.getIdUsuario()) != null)
//			throw new ValidationServiceException("El Nombre de Usuario selecionado ya se encuentra registrado");
		
		//verifico que las contrasenias sean iguales
		if(usuario.getContrasenia().compareTo(confirmarContrasenia)!=0)
			throw new ValidationServiceException("usuario.amb.error.coincidencia.contrasenias");
		
		//Validacion fecha nacimiento
		ValidacionesFormatoFechaNacimiento validacionesFormatoFechaNacimiento = new ValidacionesFormatoFechaNacimiento();
		validacionesFormatoFechaNacimiento.setFechaNacimientoS(fechaNacimiento);
		
		try {
			validacionesFormatoFechaNacimiento.validate();
		} catch (ValidationServiceException e) {
			throw new ValidationServiceException(e.getMessage());
		}
		
		usuario.setFechaNacimiento(validacionesFormatoFechaNacimiento.getFechaNacimientoValidada());
		usuario.setFechaNacimiento(validacionesFormatoFechaNacimiento.getFechaNacimientoValidada());

		
		ValidacionesUsuario validacionesUsuario = new ValidacionesUsuario();
		validacionesUsuario.setUsuario(getUsuario());
		try {
			validacionesUsuario.validate();
		} catch (ValidationServiceException e) {
			throw new ValidationServiceException(e.getMessage());
		}


	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
	//FIXME agregar el usuario
//	public UsuarioList getUsuarioList() {
//		return usuarioList;
//	}
//	public void setUsuarioList(UsuarioList usuarioList) {
//		this.usuarioList = usuarioList;
//	}
	public void setConfirmarContrasenia(String confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}
	public String getConfirmarContrasenia() {
		return confirmarContrasenia;
	}

	public String getDiaNacimiento() {
		return diaNacimiento;
	}
	public void setDiaNacimiento(String diaNacimiento) {
		this.diaNacimiento = diaNacimiento;
	}
	public String getMesNacimiento() {
		return mesNacimiento;
	}
	public void setMesNacimiento(String mesNacimiento) {
		this.mesNacimiento = mesNacimiento;
	}
	public String getAnioNacimiento() {
		return anioNacimiento;
	}
	public void setAnioNacimiento(String anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}
	public boolean isAceptoTerminos() {
		return aceptoTerminos;
	}
	public void setAceptoTerminos(boolean aceptoTerminos) {
		this.aceptoTerminos = aceptoTerminos;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}

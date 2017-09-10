package validador;

import javax.inject.Named;

import model.Usuario;

import org.apache.commons.lang3.StringUtils;

import utils.DateUtils;
import excepcion.ValidationServiceException;

@Named
public class ValidacionesUsuario implements Validador {

	private Usuario usuario;
	
	public void validate() throws ValidationServiceException {
		
		//verifico que si ingresa un Tipo de Documento debe ingresar un Numero de Documento y viceversa
		if(usuario.getTipoDocumento() != null && StringUtils.isBlank(usuario.getNumDocumento()))
			throw new ValidationServiceException("Si ingresa un Tipo de Documento, se debe completar el Número de Documento correspondiente.");
		
		if(usuario.getTipoDocumento() == null && StringUtils.isNotBlank(usuario.getNumDocumento()))
			throw new ValidationServiceException("Si ingresa un Número de Documento, se debe completar el Tipo de Documento correspondiente.");
		
		//Edad Mayor
		int edad = DateUtils.edadSegunFechaNac(usuario.getFechaNacimiento());
		if(edad < 18){
			throw new ValidationServiceException("La Fecha de Nacimiento no puede indicar una edad inferior a 18 años");
		}
		else if(edad > 120){
			throw new ValidationServiceException("La Fecha de Nacimiento no puede superar los 120 años");
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

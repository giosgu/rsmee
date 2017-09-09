/*
 *******************************************************************************
 **  Archivo    : IdUsuarioValidator.java
 **  Paquete    : com.octomind.rsm.validation.login
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versión)
 **  Autor      : mazzca
 **  Fecha      : 25/01/2012 10:42:30
 *******************************************************************************
 **  Modificaciones/Correcciones
 **               Fecha       Responsable     Comentario
 **
 *******************************************************************************
 */
package validador;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import org.primefaces.validate.ClientValidator;

import utils.StringUtils;

/**
 * @author alessiof
 *
 */
@Named
@FacesValidator("custom.formatoMailValidator")
public class FormatoMailValidator implements Validator, ClientValidator {

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		 		
		String email = String.valueOf(value);
		
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		((UIInput) component).setValid(false);
		
		if (!email.equals("admin")){
			if (!StringUtils.email(email)){
				message.setDetail("No es un Formato Mail válido");	
				throw new ValidatorException(message);
			}
		}
		//else lo doy como valido
		((UIInput) component).setValid(true);
		
	}

	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValidatorId() {
		return "custom.formatoMailValidator";
	}


}

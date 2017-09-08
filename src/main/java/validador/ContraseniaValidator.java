/*
 *******************************************************************************
 **  Archivo    : ContraseniaValidator.java
 **  Paquete    : com.octomind.rsm.validation.login
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versión)
 **  Autor      : mazzca
 **  Fecha      : 25/01/2012 11:47:09
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

/**
 * @author mazzca
 *
 */
@Named
@FacesValidator("custom.contraseniaValidator")
public class ContraseniaValidator implements Validator, ClientValidator{
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		 		
		String contrasenia = String.valueOf(value);
		
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		((UIInput) component).setValid(false);
		
		if (contrasenia.length()<8){
			message.setDetail("Debe contener al menos 8 caracteres");	
			throw new ValidatorException(message);
		}
		//Si tiene al menos una letra y al menos un numero
		if (!utils.StringUtils.tieneNumerosYLetras(contrasenia)){
			message.setDetail("Debe contener letras y números");
			throw new ValidatorException(message);
		}
		//Si es solamente num�rico
		if (org.apache.commons.lang3.StringUtils.isNumeric(contrasenia)){
			message.setDetail("Debe contener letras y números");
			throw new ValidatorException(message);
		}
		//Si es salamente alfab�tico
		if (org.apache.commons.lang3.StringUtils.isAlpha(contrasenia)){
			message.setDetail("Debe contener letras y números");
			throw new ValidatorException(message);
		}
		
		//else lo doy como valido
		((UIInput) component).setValid(true);
		
	}

	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getValidatorId() {
		return "custom.contraseniaValidator";
	}

}

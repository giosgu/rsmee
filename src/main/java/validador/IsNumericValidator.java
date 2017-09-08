/*
 *******************************************************************************
 **  Archivo    : isNumericValidator.java
 **  Paquete    : com.octomind.rsm.validation
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versión)
 **  Autor      : mazzca
 **  Fecha      : 23/03/2012 16:42:30
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

import org.apache.commons.lang3.StringUtils;
import org.primefaces.validate.ClientValidator;

/**
 * @author alessiof
 *
 */
@Named
@FacesValidator("custom.isNumericValidator")
public class IsNumericValidator  implements Validator, ClientValidator {

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String numeroIngresado = String.valueOf(value);
		
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		((UIInput) component).setValid(false);		
		
		if(numeroIngresado!=null && numeroIngresado.compareTo("")!=0)
	    	if(!StringUtils.isNumeric(numeroIngresado)){
	    		message.setDetail("El campo debe ser Numérico");
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

	@Override
	public String getValidatorId() {
		return "custom.isNumericValidator";
	}

}

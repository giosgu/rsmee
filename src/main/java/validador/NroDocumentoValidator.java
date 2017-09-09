/*
 *******************************************************************************
 **  Archivo    : NroDocumentoValidator.java
 **  Paquete    : com.octomind.rsm.validation.usuario
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versión)
 **  Autor      : alessiof
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

import utils.StringUtils;

@Named
@FacesValidator("custom.nroDocumentoValidator")
public class NroDocumentoValidator implements Validator, ClientValidator {
	
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		 		
		String nroDocumento = String.valueOf(value);
		
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		((UIInput) component).setValid(false);
		
		if (nroDocumento.length()<5 || nroDocumento.length()>15 ){
			message.setDetail("Debe contener entre 5 y 15 caracteres");
			throw new ValidatorException(message);
		}
		//Si es solamente alfabético
		if (org.apache.commons.lang3.StringUtils.isAlpha(nroDocumento)){
			message.setDetail("El valor debe ser numérico o alfanumérico");
			throw new ValidatorException(message);
		}		
		if (!StringUtils.alfanumerico(nroDocumento)){
			message.setDetail("El valor debe ser numérico o alfanumérico");	
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
		return "custom.nroDocumentoValidator";
	}

}

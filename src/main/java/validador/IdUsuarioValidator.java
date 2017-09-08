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
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import utils.StringUtils;

/**
 * @author mazzca
 *
 */
@FacesValidator("custom.idUsuarioValidator")
public class IdUsuarioValidator implements Validator, ClientValidator{

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		 		
		String idUsuario = String.valueOf(value);
		if (!idUsuario.equals("admin")){
			if (!StringUtils.email(idUsuario)){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
						"Nombre de usuario no es un mail válido"));
			}
		}
	}

	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValidatorId() {
		return "custom.idUsuarioValidator";
	}


}

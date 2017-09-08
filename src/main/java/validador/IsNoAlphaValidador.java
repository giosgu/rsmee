/**
 * Valida si el valor NO no está compuesto únicamente por letras 
 *  
 */
package validador;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.validate.ClientValidator;
 
@Named
@FacesValidator("custom.isNoAlphaValidador")
public class IsNoAlphaValidador implements Validator, ClientValidator{
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);

		String valor = String.valueOf(value); 
		
		if(StringUtils.isNotBlank(valor)){
			valor = valor.replaceAll(" ", "");
			
			//Si es solamente alfabético
			if (StringUtils.isAlpha(valor)){
				message.setDetail("No puede contener únicamente letras");
				throw new ValidatorException(message);
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
		return "custom.isNoAlphaValidador";
	}
	
}



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
@FacesValidator("custon.isDoublePositivoValidator")
public class IsDoublePositivoValidator implements Validator, ClientValidator {

	
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		((UIInput) component).setValid(false);
				
		String stringLong = String.valueOf(value);
		
		if(StringUtils.isNotEmpty(stringLong)){
			Double valueDouble;
			try{ 
				valueDouble = Double.parseDouble(stringLong);
			} catch(Exception e){
				message.setDetail("El valor debe ser numérico #.#");
				throw new ValidatorException(message);
			}
			
			if(valueDouble<0){
				message.setDetail("El valor debe ser positivo");
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
		return "custon.isDoublePositivoValidator";
	}


}

/**
 * Valida si el nombre es válido contra una expresión regular 
 *  
 */
package validador;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
@FacesValidator("custom.nombreValidador")
public class NombreValidador implements Validator, ClientValidator {
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		
		String valor = String.valueOf(value); 
		
		if(StringUtils.isNotBlank(valor)){
			valor = valor.replaceAll(" ", "");
			
			String expression="["+
				"a-z|ñ|Ñ|"+
				"á|é|í|ó|ú|Á|É|Í|Ó|Ú|"+
				"'"+
				"Ä|Ë|Ï|Ö|Ü|ä|ë|ï|ö|ü|"+
				"â|Â|ê|Ê|î|Î|Ô|ç|Ç|"+
				"]*";
			
	        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
		    Matcher matcher = pattern.matcher(valor);
		    
		    if(!matcher.matches()){
		    	message.setDetail("El valor es inválido");
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
		return "custom.nombreValidador";
	}
	
}



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
@FacesValidator("custom.pesoUsuarioValidator") 
public class PesoUsuarioValidator implements Validator, ClientValidator {

	private static final long serialVersionUID = 1L;
	
	private static final Double Minino_Valor_Peso = 0.1;
	private static final Double Maximo_Valor_Peso = 500.0;
	
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		((UIInput) component).setValid(false);
				
		String stringDouble = String.valueOf(value);
		
		if(StringUtils.isNotEmpty(stringDouble)){
			Double valueDouble;
			try{
				valueDouble = Double.parseDouble(stringDouble);
			} catch(Exception e){
				message.setDetail("El valor debe ser numérico");
				throw new ValidatorException(message);
			}
			
			if(valueDouble<Minino_Valor_Peso || valueDouble>Maximo_Valor_Peso){
				message.setDetail("El valor debe estar entre: "+ Minino_Valor_Peso +" y "+ Maximo_Valor_Peso);
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
		return "custom.pesoUsuarioValidator";
	}


}

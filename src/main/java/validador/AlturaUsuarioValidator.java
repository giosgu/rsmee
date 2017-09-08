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
@FacesValidator("custom.alturaUsuarioValidator")
public class AlturaUsuarioValidator implements Validator, ClientValidator {

	private static final long serialVersionUID = 1L;
	
	private static final int Minino_Valor_Altura = 10;
	private static final int Maximo_Valor_Altura = 300;	
	
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		

		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		((UIInput) component).setValid(false);
				
		String stringIngeger = String.valueOf(value);
		
		if(StringUtils.isNotEmpty(stringIngeger)){
			Integer valueInteger;
			try{
				valueInteger = Integer.parseInt(stringIngeger);
			} catch(Exception e){
				message.setDetail("El valor debe ser numérico");
				throw new ValidatorException(message);
			}
			
			if(valueInteger<Minino_Valor_Altura || valueInteger>Maximo_Valor_Altura){
				message.setDetail("El valor debe estar entre: "+ Minino_Valor_Altura +" y "+ Maximo_Valor_Altura);
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
		return "custom.alturaUsuarioValidator";
	}


}

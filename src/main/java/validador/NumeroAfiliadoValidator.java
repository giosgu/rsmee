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

@Named
@FacesValidator("custom.numeroAfiliadoValidator")
public class NumeroAfiliadoValidator implements Validator, ClientValidator  {

	@Override
	public void validate(FacesContext arg0, UIComponent component, Object arg2)
			throws ValidatorException {
		
		String numeroAfiliado = String.valueOf(arg2);
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		((UIInput) component).setValid(false);
		
		try{
			new Long(numeroAfiliado);
		}catch(NumberFormatException e){
			message.setDetail("El número de afiliado debe ser numérico de 7 dígitos");
			throw new ValidatorException(message);
		}
		if(numeroAfiliado.length() != 7){
			message.setDetail("El número de afiliado debe ser numérico de 7 dígitos");
			throw new ValidatorException(message);
		}
		
		((UIInput) component).setValid(true);
	}

	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValidatorId() {
		return "custom.numeroAfiliadoValidator";
	}

}
package validador;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import model.TipoDocumento;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.validate.ClientValidator;
 
@Named
@FacesValidator("custom.formularioUsuarioValidador")
public class FormularioUsuarioValidador implements Validator, ClientValidator {
	
	private TipoDocumento tipoDocumento;
	private String numDocumento;
	
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		//verifico que si ingresa un Tipo de Documento debe ingresar un Numero de Documento y viceversa
		if(tipoDocumento != null && StringUtils.isBlank(numDocumento))
			throw new ValidatorException(new FacesMessage("Si ingresa un Tipo de Documento, se debe completar el Número de Documento correspondiente."));
		
		if(tipoDocumento == null && StringUtils.isNotBlank(numDocumento))
			throw new ValidatorException(new FacesMessage("Si ingresa un Número de Documento, se debe completar el Tipo de Documento correspondiente."));

	}

	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}


	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getValidatorId() {
		return "custom.formularioUsuarioValidador";
	}
	
}



package validador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import org.primefaces.validate.ClientValidator;

@Named
@FacesValidator("custom.fechaNacimientoValidator")
public class FechaNacimientoValidator implements Validator, ClientValidator {

	
	public void validate(FacesContext arg0, UIComponent arg1, Object value)throws ValidatorException {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);

		int edad = edadSegunFechaNac((Date) value);
		if(edad < 18){
			message.setDetail("La Fecha de Nacimiento no puede ser menos a 18 años");
			throw new ValidatorException(message);
		}
		else if(edad > 120){
			message.setDetail("La Fecha de Nacimiento no puede superar los 120 años");
			throw new ValidatorException(message);
		}
	
	}

	//Saber mi edad
	public static int edadSegunFechaNac(Date fecha_nac) {
	    Date fechaActual = new Date();
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    String hoy = formato.format(fechaActual);
	    String[] dat1 = formato.format(fecha_nac).split("/");
	    String[] dat2 = hoy.split("/");
	    int anios = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
	    int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
	    if (mes < 0) {
	      anios = anios - 1;
	    } else if (mes == 0) {
	      int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
	      if (dia < 0) {
	        anios = anios - 1;
	      }
	    }
	    return anios;
	}

	
	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return "custom.FechaNacimientoValidator";
	}

}

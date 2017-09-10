package validador;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import excepcion.ValidationServiceException;

public class ValidacionesFormatoFechaNacimiento implements Validador {
	
    //Fecha Nacimiento
    private String fechaNacimientoS;
    
    private Date fechaNacimientoValidada;    
    
	@Override
	public void validate() throws ValidationServiceException {
		if(!StringUtils.isEmpty(fechaNacimientoS) ){
	    	Date fechaNacimiento;
	    	//Que sea una Fecha (Parseo)
	    	try {
	    		fechaNacimiento = DateUtils.parseDate(fechaNacimientoS, "dd/MM/yyyy");			
			} catch (ParseException e1) {
				throw new ValidationServiceException("La Fecha de Nacimiento ingresada No es Válida");
			}
			
			//Si es fecha válida
			setFechaNacimientoValidada(fechaNacimiento);
			
		}else
			throw new ValidationServiceException("Por favor, complete la Fecha de Nacimiento");
	}
	
	public Date getFechaNacimientoValidada() {
		return fechaNacimientoValidada;
	}
	public void setFechaNacimientoValidada(Date fechaNacimientoValidada) {
		this.fechaNacimientoValidada = fechaNacimientoValidada;
	}

	public String getFechaNacimientoS() {
		return fechaNacimientoS;
	}

	public void setFechaNacimientoS(String fechaNacimientoS) {
		this.fechaNacimientoS = fechaNacimientoS;
	}

    
    

}

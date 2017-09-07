package validador;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import excepcion.ValidationServiceException;

public class ValidacionesFormatoFechaNacimiento implements Validador {
	
    //Fecha Nacimiento
    private String diaNacimiento;
	private String mesNacimiento;
    private String anioNacimiento;
    
    private Date fechaNacimientoValidada;    
    
	@Override
	public void validate() throws ValidationServiceException {
		if(!StringUtils.isEmpty(diaNacimiento) && !StringUtils.isEmpty(mesNacimiento) && !StringUtils.isEmpty(anioNacimiento) ){
			//Fecha de Nacimiento
	    	String fechaNacimientoValidar = diaNacimiento+"/"+mesNacimiento+"/"+anioNacimiento;
	    	Date fechaNacimiento;
	    	//Que sea una Fecha (Parseo)
	    	try {
	    		fechaNacimiento = DateUtils.parseDate(fechaNacimientoValidar, "dd/MM/yyyy");			
			} catch (ParseException e1) {
				throw new ValidationServiceException("La Fecha de Nacimiento ingresada No es Válida");
			}
			//Que sea válida
			if(!utils.DateUtils.esFechaValida(diaNacimiento, mesNacimiento, anioNacimiento))
				throw new ValidationServiceException("La Fecha de Nacimiento ingresada No es Válida");
			
			//Si es fecha válida
			setFechaNacimientoValidada(fechaNacimiento);
			
		}else
			throw new ValidationServiceException("Por favor, complete la Fecha de Nacimiento");
	}
	
	
    public String getDiaNacimiento() {
		return diaNacimiento;
	}
	public void setDiaNacimiento(String diaNacimiento) {
		this.diaNacimiento = diaNacimiento;
	}
	public String getMesNacimiento() {
		return mesNacimiento;
	}
	public void setMesNacimiento(String mesNacimiento) {
		this.mesNacimiento = mesNacimiento;
	}
	public String getAnioNacimiento() {
		return anioNacimiento;
	}
	public void setAnioNacimiento(String anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}
	public Date getFechaNacimientoValidada() {
		return fechaNacimientoValidada;
	}
	public void setFechaNacimientoValidada(Date fechaNacimientoValidada) {
		this.fechaNacimientoValidada = fechaNacimientoValidada;
	}

    
    

}

package dao;

import java.math.BigDecimal;
import java.math.RoundingMode;

import model.Paciente;

public class PacienteDao extends BaseDao<Paciente, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5899269054916974891L;

	public PacienteDao(){
		super();
		this.entityType = Paciente.class;
		this.idType = Long.class;
	}
	
    //Calcular mi IMC
    public static String cualcularIMC(String pesoParam, String alturaParam){
    	//IMC = Peso (kg) / (Altura (m) x Altura (m))
    	Double altura = Double.valueOf(alturaParam)/100;
    	Double peso = Double.valueOf(pesoParam);
    	
    	Double imc = peso / (altura * altura);
    	BigDecimal imcRedondeado = (new BigDecimal(imc)).setScale(0, RoundingMode.HALF_UP);
    	String imcString = String.valueOf(imcRedondeado);
    	
    	return imcString;
    }

}


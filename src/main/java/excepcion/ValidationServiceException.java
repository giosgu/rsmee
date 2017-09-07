package excepcion;

import model.ErrorArchivo;

public class ValidationServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4038534113368857590L;

	public ValidationServiceException(String message){
		super(message);
	}
	
	public ValidationServiceException(ErrorArchivo error){
		super(error.getCodigo().toString());
	}
	
}

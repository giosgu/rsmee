package validador;

import excepcion.ValidationServiceException;

public interface Validador {

	public void validate() throws ValidationServiceException;
}

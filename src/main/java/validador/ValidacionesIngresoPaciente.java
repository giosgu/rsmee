package validador;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.Usuario;
import dao.UsuarioDao;
import excepcion.ValidationServiceException;

@Named
public class ValidacionesIngresoPaciente extends ValidacionesIngresoUsuario {

/**
	 * 
	 */
	private static final long serialVersionUID = 2989050491735967462L;
@Inject
private UsuarioDao usuarioList;

public void validate() throws ValidationServiceException{
		super.validate();
		//FIXME descomentar las validaciones
		
		if(usuarioList.existeUsuarioByDocumentoONroAfiliado(usuario.getNumDocumento(), "0"+usuario.getNumeroAfiliado()))
			throw new ValidationServiceException("El número de documento o número de afiliado ya se encuentra registrado");
			
		List<Usuario> usuarios = usuarioList.getByNumeroAfiliadoYDocumento(usuario.getNumeroAfiliado(), usuario.getNumDocumento());
		if(usuarios.size() > 0){
			throw new ValidationServiceException("Ya se encuentra registrado un usuario para ese número de afiliado, puede recuperar su contraseña desde la opción \"Olvidó su contraseña\".");
		}
		
	}
	
}

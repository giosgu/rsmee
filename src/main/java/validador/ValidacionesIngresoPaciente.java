package validador;

import excepcion.ValidationServiceException;

public class ValidacionesIngresoPaciente extends ValidacionesIngresoUsuario {

	public void validate() throws ValidationServiceException{
		super.validate();
		//FIXME descomentar las validaciones
//		
//		if(usuarioList.existeUsuarioByDocumentoONroAfiliado(usuario.getNumDocumento(), "0"+usuario.getNumeroAfiliado()))
//			throw new ValidationServiceException("El número de documento o número de afiliado ya se encuentra registrado");
//			
//
//		//valido que no exista un usuario para ese numero de afiliado y numero de documento
//		UsuarioList usuarioList = (UsuarioList) Component.getInstance(UsuarioList.class);
//		List<Usuario> usuarios = usuarioList.getByNumeroAfiliadoYDocumento(usuario.getNumeroAfiliado(), usuario.getNumDocumento());
//		if(usuarios.size() > 0){
//			throw new ValidationServiceException("Ya se encuentra registrado un usuario para ese número de afiliado, puede recuperar su contraseña desde la opción \"Olvidó su contraseña\".");
//		}
//		
	}
	
}

package security;

import java.security.GeneralSecurityException;

import model.Usuario;

public class PasswordManager {

	private static String SALT = "qazwsx34-";
	
	public static String encryptPassword(Usuario u){
    	new PasswordHash();
		PasswordHash hash = PasswordHash.instance();
		String passwordHash = null;
		try {
			byte[] salt = SALT.getBytes();
			passwordHash = hash.createPasswordKey(u.getContrasenia().toCharArray(), salt , 1200 );
			u.setContrasenia(passwordHash);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return passwordHash;
	}
	
	public static boolean isValid(String contraseña, Usuario usuario) throws GeneralSecurityException {
    	new PasswordHash();
		PasswordHash hash = PasswordHash.instance();
		byte[] salt = SALT.getBytes();
		return hash.createPasswordKey(contraseña.toCharArray(), salt, 1200).equals(usuario.getContrasenia());
		
	}
	
}

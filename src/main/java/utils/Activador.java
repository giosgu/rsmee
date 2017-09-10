package utils;

import java.util.Calendar;

import security.DigestUtils;

public class Activador {
	
	public static String generarCodigo(String idUsuario){
		String fecha = Calendar.getInstance().getTime().toString();
		String raw = "rsmqazwsx34-".concat(idUsuario).concat(fecha);
		return DigestUtils.md5Hex(raw);
	}

}

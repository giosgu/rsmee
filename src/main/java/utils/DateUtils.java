/*
 *******************************************************************************
 **  Archivo    : StringUtils.java
 **  Paquete    : com.octomind.rsm.util
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versión)
 **  Autor      : mazzca
 **  Fecha      : 25/01/2012 10:25:35
 *******************************************************************************
 **  Modificaciones/Correcciones
 **               Fecha       Responsable     Comentario
 **
 *******************************************************************************
 */

package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Named;

/**
 * @author alessiof
 *
 */
@Named
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	   
	   /**
	    * @param Date
	    * @return devuelve si un Fecha es válida o no
	    * 
	    */
	public static boolean esFechaValida(String pDia, String pMes, String pAnio){
		int dia, mes, anio;
		try{
			dia = Integer.parseInt(pDia);
			mes = Integer.parseInt(pMes);
			anio = Integer.parseInt(pAnio);
		}catch(NumberFormatException e){
			return true;
		}
		if (mes > 12)
			return false;
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.set(Calendar.YEAR , anio);
		calendario.set(Calendar.MONTH , mes-1);
		calendario.set(Calendar.DAY_OF_MONTH , 1);
		while(calendario.get(Calendar.MONTH) == (mes-1)){
			if (calendario.get(Calendar.DAY_OF_MONTH) == dia){
				return true;
			}
			calendario.add(Calendar.DAY_OF_MONTH,1);
		}
		return false;
	}
	
	   /**
	    * @param Date
	    * @return devuelve la fecha como String en formato dd/MM/yyyy
	    * 
	    */
	public static String formatearFecha(Date fechaParam){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaFormat = formato.format(fechaParam);
	    
		return fechaFormat;
	}
	
		/**
		 * @param Date
		 * @return devuelve el horario de la fecha como String en formato HH:mm:ss
		 * 
		 */
	public static String formatearFechaAHorario(Date fechaParam){
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");	    
		return formato.format(fechaParam);
	}
	
	   /**
	    * @param Integer
	    * @return devuelve el año actual restándole la cuantidad de años que se le pasa por parametro
	    * 
	    */
	public static Date fechaNacimientoPara(Integer edad){
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int anio = c.get(Calendar.YEAR)-edad;
		c.set(Calendar.YEAR , anio);
		return c.getTime();
	}
	
		/**
		 * @param Date
		 * @return devuelve si la fecha del parámetro es una fecha vencida
		 * 
		 */
	public static boolean esFechaVencida(Date fecha){
		//current
		Date current = new Date();
		String stCurrent = formatearFecha(current);
		//param
		String stFecha = formatearFecha(fecha);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			current = format.parse(stCurrent);
			fecha = format.parse(stFecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	    return (current.after(fecha));
	}
	
	/**
	 * @param Date, int, int, int
	 * @return devuelve el la fecha con la nueva hora
	 */
	public static Date cambiarTiempo(Date fecha, int hora, int minutos, int segundos, int miliseguntos){
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(fecha);
    	calendar.set(Calendar.HOUR, hora);
    	calendar.set(Calendar.MINUTE, minutos);
    	calendar.set(Calendar.SECOND, segundos);
    	calendar.set(Calendar.MILLISECOND, miliseguntos);
		return calendar.getTime();
	}
	
	/**
	 * @param String fecha
	 * @return devuelve el String parseado a Date
	 */
	public static Date stringToDate_ddMMyyyy(String strFecha){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    Date fecha = null;
	    try {
	    	fecha = format.parse(strFecha);
	    }catch (ParseException ex){
	    	ex.printStackTrace();
    	}
	    return fecha;
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

}

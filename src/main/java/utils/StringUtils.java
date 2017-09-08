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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Named;

import model.ComboEntity;
import model.Usuario;

import org.jsoup.Jsoup;

/**
 * @author mazzca
 *
 */
@Named
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * Devuelve true si es nula o vacia la cadena
	 * */
	public static boolean isEmpty(String cadena){
		
		if (cadena == null)
			return true;
		if (cadena.trim().equals(""))
			return true;
		
		return false;
	}
	
	/**
	 * Permite Caracteres alfanumericos
	 * No permite una cadena vacia ni signo "_"
	 * */
	public static boolean alfanumerico(String cadena) {
		boolean result = false;

//		No permite una cadena vacia
		String expression = "^[A-Za-z0-9]+";
		
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
	    Matcher matcher = pattern.matcher(cadena);  
	    if(matcher.matches()){  
				result = true;
	    }else 
	    	result = false;
	    
	    return result;		
	}
	
	
	/**
	 * Valida correo electrónico
	 * 
	 * */
	public static boolean email(String cadena) {
		boolean result = false;

		String expression = "^[\\w-]+(\\.[\\w-]+)*@([a-z0-9-]+(\\.[a-z0-9-]+)*?\\.[a-z]{2,6}|(\\d{1,3}\\.){3}\\d{1,3})(:\\d{4})?$";
				
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
	    Matcher matcher = pattern.matcher(cadena);  
	    if(matcher.matches()){  
			result = true;
	    }else 
	    	result = false;
	    
	    return result;		
	}
	
	/**
	 * Valida una fecha, formato aceptado: dd/MM/AAAA
	 * 
	 * */
	public static boolean fecha(String cadena) {
		try {
		    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		    formatoFecha.setLenient(false);
		    formatoFecha.parse(cadena);
		    if (cadena.length() > 10 || cadena.length() < 10) {
		          return false;
		    }
		}catch (Exception e) {
			return false;
		}     
      return true;
	}
	
	
	/*Genera una password, no encriptada, de 8 caracteres aleatorios (pueden ser letras mayusculas, minusculas y 
	    * numeros [0-9]), siempre contiene al menos 1 minuscula, 1 mayuscula o 1 numero.
	    * */
	   public static String generarPassword(int longi){
		   
		   boolean num= false;
		   boolean min= false;
		   boolean may= false;
		   
//		   int longitudes[]={8,9,10,11,12,13,14,15};					//longitudes posibles de la password
//		   int l = (int)((double)Math.random()*8); 					//Determina la longitud
//		   int longitud = longitudes[l];
		   int longitud = longi;								//Solo de esta longitud
		   
		   String password="";
		   String letrasMin[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t",
				   "u","v","w","x","y","z"};
		   String letrasMay[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
				   "U","V","W","X","Y","Z"};
		   String numeros[] = {"0","1","2","3","4","5","6","7","8","9"};
	     
		   for (int i = 0; i < longitud-2; i++) {			//genera un password de largo = longitud menos dos caracteres
			   
			   int cual = (int)((double)Math.random()*3);
		   
			   if (cual==0){
				   min=true;
				   int caracter = (int)((double)Math.random()*letrasMin.length); //elige una letra minuscula
				   password+=letrasMin[caracter];
			   }else{
				   	if (cual==1){
				   		may=true;
				   		int caracter = (int)((double)Math.random()*letrasMay.length); //elige una letra mayuscula
						password+=letrasMay[caracter];
				   	}else{
				   		num=true;
				   		int caracter = (int)((double)Math.random()*numeros.length); //elige un numero
						password+=numeros[caracter];
				   	}
			   }
		   }

		   //Se verifica que contenga al menos 1 minuscula, 1 mayuscula y 1 numero
		   if(min==false){
			   int caracter = (int)((double)Math.random()*letrasMin.length); //elige una letra minuscula
			   password+=letrasMin[caracter];
		   }
		   
		   if(may==false){
			   int caracter = (int)((double)Math.random()*letrasMay.length); //elige una letra mayuscula
			   password+=letrasMay[caracter];
		   }
		   if(num==false){
			   int caracter = (int)((double)Math.random()*numeros.length); //elige un numero
			   password+=numeros[caracter];
		   }
		
		   //Se verifica la longitud de la palabra y corrige si fuera necesario
		   if(longitud!=password.length()){
			   
			   for (int i = 0; i <= (longitud-password.length()); i++) {
				   int cual = (int)((double)Math.random()*3);
				   if (cual==0){
					   int caracter = (int)((double)Math.random()*letrasMin.length); //elige una letra minuscula
					   password+=letrasMin[caracter];
				   }else{
					   	if (cual==1){
					   		int caracter = (int)((double)Math.random()*letrasMay.length); //elige una letra mayuscula
							password+=letrasMay[caracter];
					   	}else{
					   		int caracter = (int)((double)Math.random()*numeros.length); //elige un numero
							password+=numeros[caracter];
					   	}
				   }
			   }
		   }
		   return password;
	   	}
	   
	   
	   /**
	    * Función que elimina acentos y caracteres especiales de
	    * una cadena de texto.
	    * @param input
	    * @return cadena de texto limpia de acentos y caracteres especiales.
	    */
	   public static String limpiarString(String input) {
		   if(input == null)
			   return null;
	       // Cadena de caracteres original a sustituir.
		   String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
	       // Cadena de caracteres ASCII que reemplazaron los originales.
	       String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	       String output = input;
	       for (int i=0; i<original.length(); i++) {
	           // Reemplazamos los caracteres especiales.
	           output = output.replace(original.charAt(i), ascii.charAt(i));
	       }//for i
	       return output;
	   }

	   public static boolean isBlank(String input){
		   return org.apache.commons.lang3.StringUtils.isBlank(input);
	   }
	   
	   
	   /**
	    * @param input
	    * @return devuelve si la cadena input tiene al menos una letra y al menos un n�mero, 
	    * 	sin importar si adem�s tiene otros caracteres.
	    */
	   public static boolean tieneNumerosYLetras(String input) {		   
		   boolean tieneNumero = false;	
		   boolean tieneLetra = false;
	
		   for(int i=0; i<input.length(); i++){
			   if( org.apache.commons.lang3.StringUtils.isNumeric( Character.toString(input.charAt(i)) ) ){
				   tieneNumero = true;
			   }
			   else if(  org.apache.commons.lang3.StringUtils.isAlpha( Character.toString(input.charAt(i)) ) ){
				   tieneLetra = true;
			   }
		   }		   
		   return (tieneNumero && tieneLetra);
	   }

	   public static Long asLong(String value){
		   return new Long(value);
	   }
	   
	   public static String blankIfNull(String string){
		   return string == null ? "" : string; 
	   }
	   
	   /**
	    * @param codigo html
	    * @return devuelve el texto del del html
	    * 
	    */
	   public static String htmlToText(String html) {
		    return Jsoup.parse(html).text();
		}

	   public static String armarNombre(Usuario usuario){
		   return usuario.getApellido() + ", " + usuario.getNombre();
	   }
	   
	   /**
	    * @param longitud de la cadena aleatoria a generar
	    * @return cadena alfanumérica random
	    * 
	    */
	   public static String getCadenaAlfanumAleatoria(int longitud){
		   String cadenaAleatoria = "";
		   long milis = new java.util.GregorianCalendar().getTimeInMillis();
		   Random r = new Random(milis);
		   int i = 0;
		   while ( i < longitud){
			   char c = (char)r.nextInt(255);
			   if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
				   cadenaAleatoria += c;
				   i ++;
			   }
		   }
		   return cadenaAleatoria;
	   }
	   
	   /**
	    * @param cadena
	    * @param longitud máxima
	    * @return cadena limitada por la longitud máxima en caso de superarla
	    * 
	    */
		public String previewTexto(String string, int max){
			return StringUtils.length(string) > max ? StringUtils.substring(string, 0, max) + "...." : string;
		}
		
		/**
		 * @param lista de Objects ComboEntity
		 * @return cadena separada por comas de los labels
		 */		
		public String listaLabelsCombos(List<ComboEntity> combosEntity){
			String result = "";
			for(int i=0; combosEntity.size()>i; i++){
				result = result.concat(combosEntity.get(i).getLabel());
				if(combosEntity.size() != (i+1) ){
					result = result.concat(", ");
				}
			}
			return result;
		}

		/**
		 * @param1 texto original
		 * @param2 caracteres permitidos por renglón
		 * @return Lista de renglones sin cortar palabras
		 */
		public static List<String> armarRenglonesList(String texto, int caracteresXRenglon){
			List<String> renglones = new ArrayList<String>();
			String[] vectorPalabras = texto.trim().split(" ");
			List<String> listaPalabras = new ArrayList<String>();
			listaPalabras.addAll(Arrays.asList(vectorPalabras));
			
			int caracteresPuestos = 0;
			
			String renglon = "";
			while(listaPalabras.size()>0){
				int caracteresDeLaPalabra = listaPalabras.get(0).length()+1;
				if((StringUtils.isEmpty(renglon) && listaPalabras.size()==1) || caracteresDeLaPalabra>=caracteresXRenglon){
					renglon = listaPalabras.get(0);
					renglones.add(renglon);
					listaPalabras.remove(listaPalabras.get(0));
					break;
				}
				if((caracteresPuestos + caracteresDeLaPalabra)<=caracteresXRenglon){
					caracteresPuestos += caracteresDeLaPalabra;
					renglon += listaPalabras.get(0)+" ";
					listaPalabras.remove(listaPalabras.get(0));
					if(listaPalabras.size()==0)
						renglones.add(renglon);
				}else{
					renglones.add(renglon);
					renglon = "";
					caracteresPuestos = 0;
				}
			}
			
			return renglones;
		}
		
		
		/**
		 * @param usuario
		 * @return delvuelve el nombre con el siguiente formato: [Prefijo][Nombre][Apellido]
		 */
	   public static String armarNombreConPrefijo_formato1(Usuario usuario){
		   String nombreLabel="";
		   if(isNotEmpty(usuario.getPrefijo()))
			   nombreLabel = usuario.getPrefijo()+" ";
		   nombreLabel += usuario.getNombre()+" "+usuario.getApellido();
		   return nombreLabel;
	   }
	   
}

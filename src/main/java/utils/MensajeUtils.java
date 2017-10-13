package utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import model.Mensaje;
import model.MensajeDestino;
import model.MensajeRespuesta;
import model.MensajeRespuestaDestino;
import model.Usuario;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang3.StringUtils;

import dao.MensajeRespuestaDao;


@Named
public class MensajeUtils {

	@Inject MensajeRespuestaDao mensajeRespuestaList;
	
	public static Predicate getMensajeDestino(final Long codigoUsuario){
		Predicate mensajeDestinoPredicate = new Predicate() {
			
			public boolean evaluate(Object arg0) {
				MensajeDestino mensajeDestino = (MensajeDestino) arg0;
				return mensajeDestino.getUsuarioDestino().getCodigo().equals(codigoUsuario);
			}
		};
		return mensajeDestinoPredicate;
	}	

	public static Predicate filtarMensajesConRespuestasLeidas(){
		Predicate mensajesConRespuestasLeidasPredicate = new Predicate() {
			
			public boolean evaluate(Object arg0) {
				Mensaje mensaje = (Mensaje) arg0;
				return mensaje.getMensajeRespuestas() == null || mensaje.getMensajeRespuestas().isEmpty() || CollectionUtils.find(mensaje.getMensajeRespuestas(), respuestaLeida()) == null;
			}
		};
		return mensajesConRespuestasLeidasPredicate;
	}	

	private  static Predicate respuestaLeida(){
		Predicate respuestaLeidaPredicate = new Predicate() {
			public boolean evaluate(Object arg0) {
				MensajeRespuesta mensajeRespuesta = (MensajeRespuesta) arg0;
				//return mensajeRespuesta.getEstadoMensaje().getCodigo().equals(EstadoMensaje.ESTADO_LEIDO);
				return false;
			}
		};
		return respuestaLeidaPredicate;
	}
	
	
    @SuppressWarnings("unchecked")
	public static List<Mensaje> ordenarListaParaMostrar(List<Mensaje> listaMensajes){
    	Set<Mensaje> mensajesFiltrados = new HashSet<Mensaje>(listaMensajes);
    	for (Mensaje mensaje : mensajesFiltrados) {
			BeanComparator orderBeanComparator = new BeanComparator("codigo", new ComparableComparator());
/*			if(mensaje.getMensajeRespuestas() != null ){
				Collections.sort(mensaje.getMensajeRespuestas(), orderBeanComparator);
			}
*/		}
    	BeanComparator reverseOrderBeanComparator = new BeanComparator("codigo", new ReverseComparator());
    	List<Mensaje> listaFinal = (new ArrayList<Mensaje>(mensajesFiltrados));
    	Collections.sort(listaFinal, reverseOrderBeanComparator);
    	return listaFinal;
    	
    }

	public static Predicate getDestinatarioRespuesta(final Long codigo) {
		Predicate getDestinatarioRespuestaPredicate = new Predicate() {
			
			public boolean evaluate(Object arg0) {
				MensajeRespuestaDestino destino = (MensajeRespuestaDestino) arg0;
				return destino.getUsuarioDestino().getCodigo().equals(codigo) ;
			}
		};
		return getDestinatarioRespuestaPredicate;	}

	public String previewMensaje(String mensajeHtml){
		String textoMensaje = utils.StringUtils.htmlToText(mensajeHtml);
		return StringUtils.length(textoMensaje) > 40 ? StringUtils.substring(textoMensaje, 0, 40) + "...." : textoMensaje;
	}
	
	public int cantidadMensajes(Mensaje mensaje){
		return mensajeRespuestaList.cantidadRespuestasMensaje(mensaje.getCodigo()) + 1; 
	}
	
	public static boolean respuestaCalificable(MensajeRespuesta mensajeRespuesta, Usuario usuarioDestino){
		//si la respuesta no la dio un profesional o el usuario no es paciente, retorno false
		if(mensajeRespuesta.getUsuarioOrigen().getProfesional() == null || usuarioDestino.getPaciente() == null)
			return false;
		
		return mensajeRespuesta.getCalificacionRespuesta() == null || mensajeRespuesta.getCalificacionRespuesta().isEmpty();
	}
	public static boolean respuestaCalificada(MensajeRespuesta mensajeRespuesta, Usuario usuarioDestino){
		
/*		//si la respuesta no la dio un profesional o el usuario no es paciente, retorno false
		if(mensajeRespuesta.getUsuarioOrigen().getProfesional() == null || usuarioDestino.getPaciente() == null)
			return false;
*/

		return mensajeRespuesta.getCalificacionRespuesta() != null && !mensajeRespuesta.getCalificacionRespuesta().isEmpty();
	}

	public static Predicate getDestinatariosMensaje(final List<Usuario> destinatarios){
		Predicate mensajeDestinoPredicate = new Predicate() {
			
			public boolean evaluate(Object arg0) {
				MensajeDestino mensajeDestino = (MensajeDestino) arg0;
				return destinatarios.contains(mensajeDestino.getUsuarioDestino());
			}
		};
		return mensajeDestinoPredicate;
	}
		
	public static Usuario getUsuarioDestinoMensaje(List<MensajeDestino> mensajesDestino){
		Usuario destino = null;
		for (MensajeDestino mensajeDestino : mensajesDestino)
			if(mensajeDestino.getUsuarioDestino().getCodigo() != mensajeDestino.getUsuarioOrigen().getCodigo())
				destino = mensajeDestino.getUsuarioDestino();
		return destino;			
	}
	
	public static String armarNombreUsuarioDestino(Mensaje mensaje){
		Usuario destino = getUsuarioDestinoMensaje(mensaje.getMensajeDestinos());
		return destino.getNombre()+" "+destino.getApellido();
	}

}

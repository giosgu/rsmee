package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.EstadoMensaje;
import model.Mensaje;
import model.MensajeDestino;
import model.Prestador;
import model.Usuario;

public class MensajeDestinoDao extends BaseDao<MensajeDestino, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 200064125644885151L;

	public List<MensajeDestino> getMensajeDestino(Long codigoMensaje, Long codigoUsuarioDestino){

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MensajeDestino> cq =  cb.createQuery(getEntityClass());
		Root<MensajeDestino> mensajeDestino = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(mensajeDestino.get("mensaje").get("codigo"), codigoMensaje));
		predicates.add(cb.equal(mensajeDestino.get("usuarioDestino").get("codigo"), codigoUsuarioDestino));
		
		cq.select(mensajeDestino).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getResultList();

	  }

	public Integer getCountMensajesPendientes(Usuario usuarioLogueado){    	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq =  cb.createQuery(Long.class);
		Root<MensajeDestino> mensajeDestino = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();

		Join<MensajeDestino, Mensaje> mensaje = mensajeDestino.join("mensaje", JoinType.INNER);
		Join<Mensaje, Usuario> usuarioOrigen = mensaje.join("origen", JoinType.INNER);
		Join<Usuario, Prestador> prestador = usuarioOrigen.join("prestador", JoinType.INNER);
		
		predicates.add(cb.equal(prestador.get("codigo"), usuarioLogueado.getPrestador().getCodigo()));
		predicates.add(cb.equal(mensajeDestino.get("estadoMensaje").get("codigo"), EstadoMensaje.PENDIENTE));
		
		cq.select(cb.countDistinct(mensajeDestino)).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getSingleResult().intValue();
		
	}

//    TODO
//	public Integer getCountDestinatariosMensaje(Long codMensaje){    	    	
//    	Criteria criteria = this.createCriteria();
//    	criteria.createAlias("mensaje", "mensaje");    	
//    	criteria.add(Restrictions.eq("mensaje.codigo", codMensaje));
//    	criteria.setProjection(Projections.rowCount());    	
//        List result = criteria.list();
//      	return result.isEmpty() ? new Integer(0) : (Integer)result.get(0)-1;
//    }
//	

	public EstadoMensaje estadoPorMensajeYUsuarioDestino(Long codigoMensaje, Long codigoUsuarioDestino){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EstadoMensaje> cq =  cb.createQuery(EstadoMensaje.class);
		Root<MensajeDestino> mensajeDestino = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<MensajeDestino, EstadoMensaje> estadoMensaje = mensajeDestino.join("estadoMensaje", JoinType.INNER);
		
		predicates.add(cb.equal(mensajeDestino.get("mensaje").get("codigo"), codigoMensaje));
		predicates.add(cb.equal(mensajeDestino.get("usuarioDestino").get("codigo"), codigoUsuarioDestino));
		
		cq.select(estadoMensaje).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getSingleResult();

	}


		public List<MensajeDestino> getMensajesDestinosByCodigosUsuarios(Long codigoMensaje, List<Long> codigosUsuariosDestino){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MensajeDestino> cq =  cb.createQuery(MensajeDestino.class);
			Root<MensajeDestino> mensajeDestino = cq.from(getEntityClass());
			List<Predicate> predicates = new ArrayList<Predicate>();
			Join<MensajeDestino, Usuario> usuarioDestino = mensajeDestino.join("usuarioDestino", JoinType.INNER);
			
			predicates.add(cb.equal(mensajeDestino.get("mensaje").get("codigo"), codigoMensaje));
			predicates.add(usuarioDestino.get("codigo").in(codigosUsuariosDestino));
			
			cq.select(mensajeDestino).where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).getResultList();
    }


		/**
		 * Adutoría de consultas
		 */
		public List<Long> codPacientesQueConsultaronA(List<Long> codMedicos){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq =  cb.createQuery(Long.class);
			Root<MensajeDestino> mensajeDestino = cq.from(getEntityClass());
			List<Predicate> predicates = new ArrayList<Predicate>();
			Join<MensajeDestino, Usuario> usuarioDestino = mensajeDestino.join("usuarioDestino", JoinType.INNER);
			Join<MensajeDestino, Usuario> usuarioOrigen = mensajeDestino.join("usuarioOrigen", JoinType.INNER);
			
			predicates.add(cb.isNotNull(usuarioDestino.get("profesional")));
			predicates.add(usuarioDestino.get("codigo").in(codMedicos));
			predicates.add(cb.isNotNull(usuarioOrigen.get("paciente")));
			
			cq.select(usuarioOrigen.<Long>get("codigo")).distinct(true).where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).getResultList();
			
		}

		/**
		 * Checkea si el mensaje esta en estado pendiente
		 */
		public boolean esMensajePendiente(Long codMensaje){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MensajeDestino> cq = cb.createQuery(MensajeDestino.class);
			Root<MensajeDestino> mensajeDestino = cq.from(getEntityClass());
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(mensajeDestino.get("mensaje").get("codigo"), codMensaje));
			predicates.add(cb.equal(mensajeDestino.get("estadoMensaje").get("codigo"), EstadoMensaje.ESTADO_EPENDIENTE.getCodigo()));
			cq.select(mensajeDestino).where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).setMaxResults(1).getResultList().isEmpty() ? false : true; 
		}
	

		
//	/**
//	 * Checkea si el mensaje esta en estado pendiente
//	 */
	//TODO
//	public boolean esMensajePendiente(Long codMensaje){
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("mensaje.codigo", codMensaje));
//		criteria.add(Restrictions.eq("estadoMensaje.codigo", EstadoMensaje.ESTADO_EPENDIENTE.getCodigo()));
//		criteria.setMaxResults(1);
//        List result = criteria.list();
//      	return result.isEmpty() ? false : true;
//	}
//
	
}

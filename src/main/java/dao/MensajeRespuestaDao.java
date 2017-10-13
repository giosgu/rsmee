package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Mensaje;
import model.MensajeRespuesta;

public class MensajeRespuestaDao extends BaseDao<MensajeRespuesta, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1687690792813854681L;

	public Integer cantidadRespuestasMensaje(Long codigoMensaje){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq =  cb.createQuery(Long.class);
		Root<MensajeRespuesta> mensajeRespuesta = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(mensajeRespuesta.get("mensaje").get("codigo"), codigoMensaje));
		
		cq.select(cb.count(mensajeRespuesta)).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getSingleResult().intValue();
	}

	public List<MensajeRespuesta> getMensajesRespuesta(Mensaje mensaje){

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MensajeRespuesta> cq =  cb.createQuery(MensajeRespuesta.class);
		Root<MensajeRespuesta> mensajeRespuesta = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(mensajeRespuesta.get("mensaje").get("codigo"), mensaje.getCodigo()));
		
		cq.select(mensajeRespuesta).where(predicates.toArray(new Predicate[]{})).orderBy(cb.asc(mensajeRespuesta.get("codigo")));
		return em.createQuery(cq).getResultList();

	}
	
}

package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Relacion;

public class RelacionDao extends BaseDao<Relacion, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7116778083063135995L;

	public Relacion obtenerRelacion(Long codigoMedico , Long codigoPaciente){
//		Criteria c = this.createCriteria();
//		c.createAlias("destinoUsuario", "destinoUsuario");
//		c.createAlias("origenUsuario", "origenUsuario");
//		c.add(Restrictions.eq("destinoUsuario.codigo", codigoMedico));
//		c.add(Restrictions.eq("origenUsuario.codigo", codigoPaciente));
//		return (Relacion) c.uniqueResult();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());

		Root<Relacion> relacion = cq.from(getEntityClass());
		
//		usuario.join(Usuario_.profesional);
//		usuario.join(profesional);
//		Metamodel metamodel = em.getMetamodel();
//		EntityType<Relacion> Relacion_ = metamodel.entity(Relacion.class);
//		Join<Relacion, ?> origenUsuario = relacion.join(Relacion_.getSingularAttribute("origenUsuario"));
//		cq.where(cb.equal(Relacion_.get("origenUsuario"), codigoPaciente));
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(relacion.get("destinoUsuario").get("codigo"), codigoMedico));
		predicates.add(cb.equal(relacion.get("origenUsuario").get("codigo"), codigoPaciente));
		cq.select(relacion).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	

	}

	public boolean envieSolicitudPendiente(Long codigoUsuarioDestino,
			Long codigo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}

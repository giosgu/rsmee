package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Invitacion;


public class InvitacionDao extends  BaseDao<Invitacion, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1791795240232260263L;
	
	public InvitacionDao(){
    	this.entityType = Invitacion.class;
    	this.idType = Long.class;

	}

	
    public Invitacion getInvitacion(String email){
    	CriteriaQuery<Invitacion> criteriaQuery = this.getCriteriaQuery();
		Root<Invitacion> parametro = criteriaQuery.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(em.getCriteriaBuilder().equal(parametro.get("email"), email));
		criteriaQuery.select(parametro).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(criteriaQuery).getSingleResult();
    	
 //    	Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("email", email ));
//		return (Invitacion) criteria.uniqueResult();
    }

    public Invitacion getUltimaInvitacion(String email){
    	CriteriaQuery<Invitacion> criteriaQuery = this.getCriteriaQuery();
		Root<Invitacion> parametro = criteriaQuery.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(em.getCriteriaBuilder().equal(parametro.get("email"), email));
		criteriaQuery.select(parametro).where(predicates.toArray(new Predicate[]{})).orderBy(em.getCriteriaBuilder().desc(parametro.get("codigo")));
		return em.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();

//    	Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("email", email ));
//		criteria.setMaxResults(1);
//		criteria.addOrder(Order.desc("codigo"));
//		return (Invitacion) criteria.uniqueResult();
    }

    
    public Invitacion getInvitacion(String email, String codigoActivacion, String codigoEstado){
    	CriteriaQuery<Invitacion> criteriaQuery = this.getCriteriaQuery();
		Root<Invitacion> parametro = criteriaQuery.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(em.getCriteriaBuilder().equal(parametro.get("email"), email));
		predicates.add(em.getCriteriaBuilder().equal(parametro.get("codigoActivacion"), codigoActivacion));
		predicates.add(em.getCriteriaBuilder().equal(parametro.get("estado.codigo"), codigoEstado));
		criteriaQuery.select(parametro).where(predicates.toArray(new Predicate[]{})).orderBy(em.getCriteriaBuilder().desc(parametro.get("codigo")));
		return em.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
//    	Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("email", email ));
//		criteria.add(Restrictions.eq("codigoActivacion", codigoActivacion ));
//		criteria.add(Restrictions.eq("estado.codigo", codigoEstado ));
//		criteria.setMaxResults(1);
//		criteria.addOrder(Order.desc("codigo"));
//		return (Invitacion) criteria.uniqueResult();
    }

	

}

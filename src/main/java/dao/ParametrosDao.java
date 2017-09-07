package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Parametros;

public class ParametrosDao extends BaseDao<Parametros, String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParametrosDao(){
    	this.entityType = Parametros.class;
    	this.idType = String.class;
    	
    }
	
	public String descripcionParametroPorId(String idParametro){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Parametros> cq = cb.createQuery(entityType);
		Root<Parametros> parametro = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(parametro.get("codigo"), idParametro));
		cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
		//criteria.add(Restrictions.eq("codigo", idParametro));
		//Parametros param = (Parametros) criteria.uniqueResult();
		Parametros param = em.createQuery(cq).getSingleResult();
		return param.getDescripcion();
	}

}

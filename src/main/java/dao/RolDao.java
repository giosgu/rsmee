package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Rol;

public class RolDao extends BaseDao<Rol, Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5597082400040205162L;


	public RolDao(){
    	this.entityType = Rol.class;
    	this.idType = Long.class;
    	
    }


    public Rol getRolByDescripcion(String descrip){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rol> cq = cb.createQuery(entityType);
		Root<Rol> parametro = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(parametro.get("descripcion"), descrip));
		cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
		//criteria.add(Restrictions.eq("codigo", idParametro));
		//Parametros param = (Parametros) criteria.uniqueResult();
		return em.createQuery(cq).getSingleResult();
    }
	
}

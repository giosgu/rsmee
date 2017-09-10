package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Afiliado;
import model.TipoPaciente;

public class AfiliadoDao extends BaseDao<Afiliado, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5656713214204639554L;
	
	public AfiliadoDao(){
		this.entityType = Afiliado.class;
    	this.idType = Long.class;
	}
	
    
    public List<Afiliado> obtenerAfiliados(String numeroAfiliado, String numeroDocumento){
    	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Afiliado> cq =  this.getCriteriaQuery(); 
		Root<Afiliado> parametro = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(parametro.get("numeroAfiliado"), numeroAfiliado));
		predicates.add(cb.equal(parametro.get("numDocumento"), numeroDocumento));
		cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getResultList();

    	
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("numeroAfiliado", numeroAfiliado));
//    	criteria.add(Restrictions.eq("numeroDocumento", numeroDocumento));
//    	return criteria.list();
    }
    
    public boolean existeAfiliado(String nroDocumento, String nroAfiliado){
    	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Afiliado> cq =  this.getCriteriaQuery(); 
		Root<Afiliado> parametro = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(parametro.get("numeroAfiliado"), nroDocumento));
		predicates.add(cb.equal(parametro.get("numDocumento"), nroAfiliado));
		cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
		return !em.createQuery(cq).getResultList().isEmpty();

		
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("numeroDocumento", nroDocumento));
//    	criteria.add(Restrictions.eq("numeroAfiliado", nroAfiliado));
//    	List result = criteria.list();
//    	return result.isEmpty() ? false : true;
    }
    
    /**
     * Devuelve tipo de paciente, si no existe devuelve null
     */
    public TipoPaciente obtenerTipoPaciente(String nroDocumento, String nroAfiliado){
    	Afiliado afiliado = this.obtenerAfiliado(nroAfiliado, nroDocumento);
    	return afiliado.getTipoPaciente();
    	//TODO projections....
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("numeroDocumento", nroDocumento));
//    	criteria.add(Restrictions.eq("numeroAfiliado", nroAfiliado));
//    	ProjectionList projectionList = Projections.projectionList();
//    	projectionList.add(Projections.property("tipoPaciente"));
//    	criteria.setProjection(projectionList);
//    	return (TipoPaciente) criteria.uniqueResult();
//    	return null;
    }
    
 public Afiliado obtenerAfiliado(String numeroAfiliado, String numeroDocumento){
    	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Afiliado> cq =  this.getCriteriaQuery(); 
		Root<Afiliado> parametro = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(parametro.get("numeroAfiliado"), numeroAfiliado));
		predicates.add(cb.equal(parametro.get("numeroDocumento"), numeroDocumento));
		cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getSingleResult();
    	
    }
	
}

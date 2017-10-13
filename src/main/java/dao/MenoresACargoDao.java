package dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.EstadoMenoresACargo;
import model.EstadoUsuario;
import model.MenoresACargo;
import model.Usuario;

public class MenoresACargoDao extends BaseDao<MenoresACargo, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6069745233326604381L;

	@Inject UsuarioDao usuarioDao;
	
	public List<MenoresACargo> misFamiliaresACargo(Usuario usuarioLogueado){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MenoresACargo> cq =  cb.createQuery(MenoresACargo.class);
		Root<MenoresACargo> menoresACargo = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<MenoresACargo, EstadoMenoresACargo> estado = menoresACargo.join("estado", JoinType.INNER);
		predicates.add(cb.equal(estado.get("codigo"), EstadoMenoresACargo.ESTADO_ACTIVO.getCodigo()));
		predicates.add(cb.equal(menoresACargo.get("adultoResponsable").get("codigo"), usuarioLogueado.getCodigo()));
		predicates.add(cb.or(
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_ACTIVO.getCodigo()), 
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_EMANCIPANDOSE.getCodigo())
				));
		cq.select(menoresACargo).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getResultList();
		}catch(NoResultException e){
			return null;
		}
	}

	
	public boolean tieneAlgunFamiliarACargo(Usuario adulto){

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq =  cb.createQuery(Long.class);
		Root<MenoresACargo> menoresACargo = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<MenoresACargo, EstadoMenoresACargo> estado = menoresACargo.join("estado", JoinType.INNER);
		predicates.add(cb.equal(estado.get("codigo"), EstadoMenoresACargo.ESTADO_ACTIVO.getCodigo()));
		predicates.add(cb.equal(menoresACargo.get("adultoResponsable").get("codigo"), adulto.getCodigo()));
		predicates.add(cb.or(
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_ACTIVO.getCodigo()), 
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_EMANCIPANDOSE.getCodigo())
				));
		cq.select(cb.count(menoresACargo)).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getSingleResult().intValue() > 0; 
	}

	public List<Long> codigosMisFamiliaresACargo(Usuario usuarioLogueado){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq =  cb.createQuery(Long.class);
		Root<MenoresACargo> menoresACargo = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<MenoresACargo, EstadoMenoresACargo> estado = menoresACargo.join("estado", JoinType.INNER);
		predicates.add(cb.equal(estado.get("codigo"), EstadoMenoresACargo.ESTADO_ACTIVO.getCodigo()));
		predicates.add(cb.equal(menoresACargo.get("adultoResponsable").get("codigo"), usuarioLogueado.getCodigo()));
		predicates.add(cb.or(
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_ACTIVO.getCodigo()), 
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_EMANCIPANDOSE.getCodigo())
				));
		cq.select(menoresACargo.get("menor").<Long>get("codigo")).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getResultList();
		}catch(NoResultException e){
			return null;
		}

	}

	public List<Long> codigosFamiliaresAcargoByMayor(Long codigoAdulto){
		return codigosMisFamiliaresACargo(usuarioDao.getObjectByID(codigoAdulto));
		}

	public MenoresACargo getMenoresACargoByMenor(Long codigoMenor) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MenoresACargo> cq =  cb.createQuery(MenoresACargo.class);
		Root<MenoresACargo> menoresACargo = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(menoresACargo.get("menor").get("codigo"), codigoMenor));
		cq.select(menoresACargo).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}


	public Usuario getAdultoResponsableByMenor(Long codigoMenor){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq =  cb.createQuery(Usuario.class);
		Root<MenoresACargo> menoresACargo = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(menoresACargo.get("menor").get("codigo"), codigoMenor));
		cq.select(menoresACargo.<Usuario>get("adultoResponsable")).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getSingleResult();
		}catch(NoResultException e){
			return null;
		}

	}

	public List<MenoresACargo> getMenoresACargoByMayor(Long codigoMayor){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MenoresACargo> cq =  cb.createQuery(MenoresACargo.class);
		Root<MenoresACargo> menoresACargo = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<MenoresACargo, EstadoMenoresACargo> estado = menoresACargo.join("estado", JoinType.INNER);
		predicates.add(cb.equal(menoresACargo.get("adultoResponsable").get("codigo"), codigoMayor));
		predicates.add(cb.or(
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_ACTIVO.getCodigo()), 
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_EMANCIPANDOSE.getCodigo())
				));
		cq.select(menoresACargo).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getResultList();
	}

	public Boolean esAdultoResponsableDeMenor(Long codigoUsuarioMayor, Long codigoUsuarioMenor){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq =  cb.createQuery(Long.class);
		Root<MenoresACargo> menoresACargo = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<MenoresACargo, EstadoMenoresACargo> estado = menoresACargo.join("estado", JoinType.INNER);
		predicates.add(cb.equal(estado.get("codigo"), EstadoMenoresACargo.ESTADO_ACTIVO.getCodigo()));
		predicates.add(cb.equal(menoresACargo.get("adultoResponsable").get("codigo"),codigoUsuarioMayor));
		predicates.add(cb.equal(menoresACargo.get("menor").get("codigo"),codigoUsuarioMenor));
		predicates.add(cb.or(
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_ACTIVO.getCodigo()), 
				cb.equal(estado.get("codigo"), EstadoUsuario.ESTADO_EMANCIPANDOSE.getCodigo())
				));
		cq.select(cb.count(menoresACargo)).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getSingleResult() >  0;
		}catch(NoResultException e){
			return false;
		}
}
		
	/**
	 * @return Codigos de usuarios familiares incluyendo al del paciente logueado
	 */
	public List<Long> codigosGrupoFamiliarPaciente(Usuario usuarioLogueado){
		Long codAdulto = usuarioLogueado.getCodigo();
		List<Long> result = codigosFamiliaresAcargoByMayor(codAdulto);
		result.add(codAdulto);
		return result;
	}

}

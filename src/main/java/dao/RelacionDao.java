package dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Especialidad;
import model.EstadoRelacion;
import model.Profesional;
import model.Relacion;
import model.Usuario;

public class RelacionDao extends BaseDao<Relacion, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7116778083063135995L;
	private List<Relacion> listaSolicitudesPendientes;
	
	
	public Relacion obtenerRelacion(Long codigoMedico , Long codigoPaciente){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());
		Root<Relacion> relacion = cq.from(getEntityClass());
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

	public Boolean envieSolicitudPendiente(Long codigoMedico, Long codigoUsuarioOrigen){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());
		Root<Relacion> relacion = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(relacion.get("destinoUsuario").get("codigo"), codigoMedico));
		predicates.add(cb.equal(relacion.get("origenUsuario").get("codigo"), codigoUsuarioOrigen));
		predicates.add(cb.equal(relacion.get("estadoRelacion").get("codigo"), EstadoRelacion.INICIAL));
		cq.select(relacion).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getSingleResult() != null;
		}catch(NoResultException e){
			return Boolean.FALSE;
		}
	}


	//Bidireccional
	public Boolean tienenRelacionAceptada(Long codigoMedico, Long codigoUsuarioOrigen){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());
		Root<Relacion> relacion = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(relacion.get("estadoRelacion").get("codigo"), EstadoRelacion.ESTADO_ACEPTADA.getCodigo()));
		predicates.add(cb.or(cb.and(cb.equal(relacion.get("destinoUsuario").get("codigo"), codigoMedico), 
						cb.equal(relacion.get("origenUsuario").get("codigo"), codigoUsuarioOrigen)),
						cb.and(cb.equal(relacion.get("destinoUsuario").get("codigo"), codigoUsuarioOrigen), 
						cb.equal(relacion.get("origenUsuario").get("codigo"), codigoMedico))));
		cq.select(relacion).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getSingleResult() != null;
		}catch(NoResultException e){
			return Boolean.FALSE;
		}
	}

	public List<Relacion> obtenerRelacionesDestino(Long codigoUsuario) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());
		Root<Relacion> relacion = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(relacion.get("estadoRelacion").get("codigo"), EstadoRelacion.ESTADO_INICIAL.getCodigo()));
		predicates.add(cb.equal(relacion.get("destinoUsuario").get("codigo"), codigoUsuario));
		cq.select(relacion).where(predicates.toArray(new Predicate[]{}));
		this.listaSolicitudesPendientes = em.createQuery(cq).getResultList();
		return listaSolicitudesPendientes;
	}

	public Relacion obtenerRelacionPorNotificacion(Long codNotificacion){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());
		Root<Relacion> relacion = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(relacion.get("notificacion").get("codigo"), codNotificacion));
		cq.select(relacion).where(predicates.toArray(new Predicate[]{}));
		try{
			return em.createQuery(cq).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}


	public Relacion obtenerRelacion(Long codigoRelacion) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());
		Root<Relacion> relacion = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(relacion.get("codigo"), codigoRelacion));
		cq.select(relacion).where(predicates.toArray(new Predicate[]{}));

		try{
			return em.createQuery(cq).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public boolean tieneSolicitudParaMedicoConEspecialidad(Long codUsuario, Long codEspecialidad){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());
		Root<Relacion> relacion = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.isNotNull(relacion.get("destinoUsuario").get("profesional")));
		predicates.add(cb.equal(relacion.get("origenUsuario").get("codigo"), codUsuario));
		predicates.add(cb.equal(relacion.get("estadoRelacion").get("codigo"), EstadoRelacion.ESTADO_INICIAL.getCodigo()));
		
		Join<Relacion, Usuario> usuarioDestino = relacion.join("destinoUsuario", JoinType.INNER);
		Join<Usuario, Profesional> profesional = usuarioDestino.join("profesional", JoinType.INNER);
		Join<Profesional, Especialidad> especialidad = profesional.join("especialidades", JoinType.INNER);
		predicates.add(cb.equal(especialidad.get("codigo"), codEspecialidad));

		cq.select(relacion).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getResultList().size() != 0;
}

	
//	public boolean tieneSolicitudParaMedicoConEspecialidad(Long codUsuario, Long codEspecialidad){
//		Criteria criteria = this.createCriteria();
//		criteria.createAlias("origenUsuario", "origenUsuario");
//		criteria.createAlias("destinoUsuario", "destinoUsuario");
//		criteria.add(Restrictions.isNotNull("destinoUsuario.profesional"));
//		criteria.add(Restrictions.eq("origenUsuario.codigo", codUsuario));
//		criteria.add(Restrictions.eq("estadoRelacion.codigo", EstadoRelacion.INICIAL));
//		criteria.createAlias("destinoUsuario.profesional", "profesional");
//		criteria.createAlias("profesional.especialidades", "especialidades");
//		criteria.add(Restrictions.eq("especialidades.codigo", codEspecialidad));
//		return criteria.list().size()!=0;
//	}

	public List<Relacion> getRelacionesActivas(Usuario usuarioSolicitante){
		Object[] estadosValidos = (EstadoRelacion.ESTADO_INICIAL.getCodigo() + ";" + EstadoRelacion.ESTADO_ACEPTADA.getCodigo()).split(";");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relacion> cq =  cb.createQuery(getEntityClass());
		Root<Relacion> relacion = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(relacion.get("origenUsuario").get("codigo"), usuarioSolicitante.getCodigo()));
		Expression<String> parentExpression = relacion.get("estadoRelacion").get("codigo");
		predicates.add(parentExpression.in(estadosValidos));
		cq.select(relacion).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getResultList();
		
	}

	public List<Relacion> getListaSolicitudesPendientes() {
		return listaSolicitudesPendientes;
	}

	public void setListaSolicitudesPendientes(
			List<Relacion> listaSolicitudesPendientes) {
		this.listaSolicitudesPendientes = listaSolicitudesPendientes;
	}

}

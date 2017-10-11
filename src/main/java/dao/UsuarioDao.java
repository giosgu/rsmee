package dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import model.EstadoUsuario;
import model.Profesional;
import model.Usuario;
import utils.StringUtils;

public class UsuarioDao extends BaseDao<Usuario, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8262209039280216671L;
	@Inject
	public static SingularAttribute<Usuario, Profesional> profesional;
	
	public UsuarioDao(){
    	this.entityType = Usuario.class;
    	this.idType = Long.class;

	}


	public List<Usuario> getUsuariosCodigoIn(List<Long> codigos){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq =  cb.createQuery(getEntityClass());
		Root<Usuario> usuario = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Expression<String> parentExpression = usuario.get("codigo");
		predicates.add(parentExpression.in(codigos));
		cq.select(usuario).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getResultList();

	}
	
	//TODO usar el metamodel!!!
	//https://www.programcreek.com/java-api-examples/index.php?api=javax.persistence.metamodel.Metamodel
	public List<Usuario> obtenerMedicos(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq =  cb.createQuery(entityType);

		Root<Usuario> usuario = cq.from(entityType);
//		usuario.join(Usuario_.profesional);
//		usuario.join(profesional);
		Metamodel metamodel = em.getMetamodel();
		EntityType<Usuario> Usuario_ = metamodel.entity(Usuario.class);
		Join profesional = usuario.join(Usuario_.getSingularAttribute("profesional"));
//	    profesional.
//		address.on(qb.equal(address.get(entityAddr_.getSingularAttribute("city")), "Ottawa"));
		
		return em.createQuery(cq).getResultList();
		//(Fetch<Usuario, Profesional> profesional = usuario.fetch("profesional");
//		usuario.fetch(Profesional_);
//		cq.select(usuario);
//		profesional.

	}
	
//	TODO
//	public List<Usuario> buscarRelacionesMedicas(Usuario usuarioLogueado, String apellido, 
//				Especialidad especialidad, String modoBusqueda, List<Long> especialidadesPermitidas){
//				
//		Criteria c = this.createCriteria();
//		c.createAlias("profesional", "profesional");
//		
//		if(especialidad!= null || !especialidadesPermitidas.isEmpty())
//			c.createAlias("profesional.especialidades", "especialidades");
//		
//		if(!especialidadesPermitidas.isEmpty())
//			c.add(Restrictions.in("especialidades.codigo", especialidadesPermitidas));
//		
//		c.add(Restrictions.not(Restrictions.eq("codigo", usuarioLogueado.getCodigo())));
//		
//		Criterion c1 = null;
//		Criterion c2 = null;
//		if(StringUtils.isNotBlank(apellido)){
//			String sqlRest = "TRANSLATE(UPPER (apellido), 'ÁÉÍÓÚ','AEIOU') like '%"+apellido.toUpperCase()+"%'";
//			c1 = Restrictions.sqlRestriction(sqlRest);
//		}
//		if(especialidad!= null)
//			c2 = Restrictions.eq("especialidades.codigo", especialidad.getCodigo());
//		
//		if(c1!=null && c2==null){
//			c.add(c1);
//		}
//		if(c1==null && c2!=null){
//			c.add(c2);
//		}
//				
//		if(modoBusqueda == null || modoBusqueda.equals("or") ){ 
//			if(c1!=null && c2!=null){
//				LogicalExpression orExp = Restrictions.or(c1,c2);
//				c.add(orExp);
//			}
//		}
//		else{
//			if(c1!=null && c2!=null){
//				LogicalExpression andExp = Restrictions.and(c1, c2);
//				c.add(andExp);
//			}
//		}
//		List usuarios = c.list();
//		//this.listaResultadosMedicos = usuarios;
//		return usuarios;
//	}
//
//	
//	@SuppressWarnings("unchecked")
//	public List<Usuario> obtenerMedicos(String clave, Long codUsuarioOrigenSeleccionado){
//		Criteria criteria = this.createCriteria();
//		
//		criteria.createAlias("profesional", "profesional", CriteriaSpecification.LEFT_JOIN );
//		criteria.createAlias("profesional.especialidades", "especialidades", CriteriaSpecification.LEFT_JOIN);
//		criteria.createAlias("contactos", "contactos");
//		criteria.add(Restrictions.eq("contactos.destino.codigo", codUsuarioOrigenSeleccionado));
//		criteria.add(Restrictions.ne("estadoUsuario.codigo", "LIC"));
//		
//		clave = com.octomind.rsm.util.StringUtils.limpiarString(clave);
//		
//		String sqlRestApellido = "TRANSLATE(UPPER (apellido), 'ÁÉÍÓÚ','AEIOU') like '%"+clave.toUpperCase()+"%'";
//		String sqlRestNombre = "TRANSLATE(UPPER (nombre), 'ÁÉÍÓÚ','AEIOU') like '%"+clave.toUpperCase()+"%'";
//		
//		Criterion criterion;
//		criterion = Restrictions.sqlRestriction(sqlRestApellido);
//		criterion = Restrictions.or(criterion, Restrictions.sqlRestriction(sqlRestNombre));
//		criterion = Restrictions.or(criterion, Restrictions.like("especialidades.descripcion", clave, MatchMode.ANYWHERE).ignoreCase());
//		
//		criteria.add(criterion);
//		return criteria.list();
//	}
//	

		public Usuario buscarUsuarioPorCodigo(Long codigoUsuario){
		return super.findBy(codigoUsuario);
		
	}

		
		public List<SelectItem> obtenerUsuariosItemSegunPrestador(Usuario usuarioLogueado){

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq =  cb.createQuery(Usuario.class);
			Root<Usuario> usuario = cq.from(getEntityClass());
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(usuario.get("prestador").get("codigo"), usuarioLogueado.getPrestador().getCodigo()));
			predicates.add(cb.equal(usuario.get("estadoUsuario").get("codigo"), EstadoUsuario.ESTADO_ACTIVO.getCodigo()));
			predicates.add(cb.not(cb.equal(usuario.get("codigo"), usuarioLogueado.getCodigo())));
			predicates.add(cb.not(cb.equal(usuario.get("administrador"), true)));
			cq.select(usuario).where(predicates.toArray(new Predicate[]{}));
			List<Usuario> usuarios = em.createQuery(cq).getResultList();
			return this.asSelectItems(new ArrayList<Usuario>(usuarios));
			
		}
		
		public Integer cantidadUsuariosPacientesPendientes(Usuario administrador){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq =  cb.createQuery(Long.class);
			Root<Usuario> usuario = cq.from(getEntityClass());
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.isNotNull(usuario.get("paciente")));
			predicates.add(cb.equal(usuario.get("estadoUsuario").get("codigo"), EstadoUsuario.ESTADO_PENDIENTE.getCodigo()));
			predicates.add(cb.equal(usuario.get("prestador").get("codigo"), administrador.getPrestador().getCodigo()));

			cq.select(cb.count(usuario)).where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).getSingleResult().intValue();
			
		}

		public List<Usuario> listarUsuarios(Usuario administrador, int cantidadResultados, int offSet){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq =  cb.createQuery(Usuario.class);
			Root<Usuario> usuario = cq.from(getEntityClass());
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.isNotNull(usuario.get("paciente")));
			predicates.add(cb.equal(usuario.get("estadoUsuario").get("codigo"), EstadoUsuario.ESTADO_PENDIENTE.getCodigo()));
			predicates.add(cb.equal(usuario.get("prestador").get("codigo"), administrador.getPrestador().getCodigo()));

			cq.select(usuario)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(cb.asc(usuario.get("codigo")));

			return em.createQuery(cq).setFirstResult(offSet).setMaxResults(cantidadResultados).getResultList();
		}


//		public Criteria obtenerCriteriaUsuariosPorCodigos(List<Long> listaCodigosUsuarios){
//			if(listaCodigosUsuarios == null || listaCodigosUsuarios.isEmpty())
//				return null;
//	    	Criteria criteria = this.createCriteria();
//			criteria.createAlias("prestador", "prestador");
//	    	criteria.add(Restrictions.eq("prestador.codigo", this.getUsuarioLogueado().getPrestador().getCodigo()));
//	    	criteria.add(Restrictions.in("codigo", listaCodigosUsuarios));
//	    	return criteria;
//	    }
//		

		public String obtenerCvPorCodUsuario(Long codigoUsuario){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq =  cb.createQuery(Object.class);
			Root<Usuario> usuario = cq.from(getEntityClass());
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(usuario.get("codigo"), codigoUsuario));

			cq.select(usuario.get("profesional").get("pathCV"))
				.where(predicates.toArray(new Predicate[]{}));
			try{
				return em.createQuery(cq).getSingleResult().toString();
			}catch(NoResultException e){
				return null;
			}
			
		}
		
		public List<Usuario> getByNumeroAfiliadoYDocumento(String numeroAfiliado, String numeroDocumento){
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq =  this.getCriteriaQuery(); 
			Root<Usuario> parametro = cq.from(entityType);
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(parametro.get("numeroAfiliado"), numeroAfiliado));
			predicates.add(cb.equal(parametro.get("numDocumento"), numeroDocumento));
			cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).getResultList();
		}

		public List<Usuario> getUsuariosPacientesPorEstado(Collection<String> codigosEstado){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq =  this.getCriteriaQuery(); 
			Root<Usuario> usuario = cq.from(entityType);
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.isNotNull(usuario.get("paciente")));
			Expression<String> parentExpression = usuario.get("estadoUsuario").get("codigo");
			predicates.add(parentExpression.in(codigosEstado));
			cq.select(usuario)
				.where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).getResultList();

		}

	    public boolean existeUsuarioAfiliado(String numDocumento, String numeroAfiliado){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq =  this.getCriteriaQuery(); 
			Root<Usuario> usuario = cq.from(entityType);
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(usuario.get("numDocumento"), numDocumento));
			predicates.add(cb.equal(usuario.get("numeroAfiliado"), numeroAfiliado));
			cq.select(usuario)
			.where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).getResultList().isEmpty() ? false : true;
	    }


		
//		TODO	
//	    public List<Usuario> colegasConMismaEspecialidad(String codigoPrestador, List<Long> codigosEspecialidades){
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.isNotNull("profesional"));
//		criteria.add(Restrictions.not(Restrictions.eq("codigo", this.getCodigoUsuario())));
//		criteria.add(Restrictions.eq("prestador.codigo", codigoPrestador));
//		criteria.add(Restrictions.ne("estadoUsuario.codigo", EstadoUsuario.ESTADO_LICENCIA.getCodigo()));			
//		if(!codigosEspecialidades.isEmpty()){
//			criteria.createAlias("profesional", "profesional", CriteriaSpecification.INNER_JOIN);
//			criteria.createAlias("profesional.especialidades", "especialidades", CriteriaSpecification.LEFT_JOIN);
//			criteria.add(Restrictions.in("especialidades.codigo", codigosEspecialidades));
//		}
//		return criteria.list();
//	}
//	
//	TODO
//	public List<Usuario> usuariosMedicosPorPrestadorConEspecialidad(String codigoPrestador, List<Long> especialidades){
//		Criteria criteria = this.createCriteria();
//		criteria.createAlias("profesional", "profesional");
//		criteria.add(Restrictions.eq("prestador.codigo", codigoPrestador));
//		if(especialidades!=null && !especialidades.isEmpty()){
//			criteria.createAlias("profesional.especialidades", "especialidades");
//			criteria.add(Restrictions.in("especialidades.codigo", especialidades));
//		}
//		return criteria.list();
//	}
//	
//	@SuppressWarnings("unchecked") TODO
//	public List<Usuario> usuariosPacientesContactadosCon(List<Long> codigosMedicos){
//		if(codigosMedicos==null || codigosMedicos.isEmpty())
//			return new ArrayList<Usuario>();
//		
//		MensajeDestinoList mensajeDestinoList = (MensajeDestinoList) Component.getInstance(MensajeDestinoList.class);		
//		List<Long> codPacientesQueConsultaronMedicos = new ArrayList<Long>();
//		codPacientesQueConsultaronMedicos.addAll(mensajeDestinoList.codPacientesQueConsultaronA(codigosMedicos));
//		if(codPacientesQueConsultaronMedicos.isEmpty())
//			return new ArrayList<Usuario>();
//		
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.in("codigo", codPacientesQueConsultaronMedicos));		
//		return criteria.list();
//	}
//	

		public List<Usuario> usuariosPacientesPorClavePrestador(String codPrestador, String clave) {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq =  this.getCriteriaQuery(); 
			Root<Usuario> usuario = cq.from(entityType);
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(usuario.get("prestador").get("codigo"), codPrestador));
			predicates.add(cb.isNotNull(usuario.get("paciente")));
			clave = StringUtils.limpiarString(clave);
			Predicate likeApellido = cb.like(functionAccentInsensitivePostgres(cb, cb.upper(usuario.get("apellido").as(String.class))), clave.toUpperCase());
			Predicate likeNombre =cb.like(functionAccentInsensitivePostgres(cb, cb.upper(usuario.get("nombre").as(String.class))), clave.toUpperCase());
			predicates.add(cb.or(likeApellido, likeNombre));
			cq.select(usuario)
			.where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).getResultList();
		}

	    
//	TODO
//	public List<Long> getCodigosProfesionalesQueTenganEspecilidad(List<Long> codEspecialidades){
//		Criteria criteria = this.createCriteria();
//		criteria.createAlias("profesional", "profesional");
//		criteria.createAlias("profesional.especialidades", "especialidades");		
//		criteria.add(Restrictions.in("especialidades.codigo", codEspecialidades));
//		PropertyProjection propertyProjection = Projections.property("codigo");
//		criteria.setProjection(propertyProjection);
//		return criteria.list();
//	}
//	

		public List<Long> getCodigosProfesionalesPorPrestador(String codPrestador){

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq =  cb.createQuery(Long.class); 
			Root<Usuario> usuario = cq.from(entityType);
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(usuario.get("prestador").get("codigo"), codPrestador));
			predicates.add(cb.isNotNull(usuario.get("profesional")));

			cq.select(usuario.<Long>get("codigo"))
			.where(predicates.toArray(new Predicate[]{}));
			return em.createQuery(cq).getResultList();
			
	}

	public Usuario getUsuario(String idUsuario){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq =  cb.createQuery(entityType);
		Root<Usuario> parametro = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(parametro.get("idUsuario"), idUsuario));
		cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getSingleResult();
		
	}
	
	public boolean existeUsuarioByDocumentoONroAfiliado(String nroDocumento, String nroAfiliado){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq =  cb.createQuery(entityType);
		Root<Usuario> parametro = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.or(cb.equal(parametro.get("numDocumento"), nroDocumento)
				,cb.equal(parametro.get("numeroAfiliado"), nroAfiliado)));
		
		cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
		return !em.createQuery(cq).getResultList().isEmpty();
		
	}

	public List<Usuario> getUsuariosEstadoPre(Date fechaDesde){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq =  cb.createQuery(entityType);
		Root<Usuario> usuario = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(usuario.get("estadoUsuario").get("codigo"), EstadoUsuario.ESTADO_PRE_ACEPTADO.getCodigo()));
		predicates.add(cb.greaterThanOrEqualTo(usuario.<Date>get("fechaCreacion"), fechaDesde));
		cq.select(usuario).where(predicates.toArray(new Predicate[]{}));
		return em.createQuery(cq).getResultList();

	}

}

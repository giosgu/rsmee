package dao;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import model.Profesional;
import model.Usuario;

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

//	@SuppressWarnings("unchecked")
//	public List<Usuario> getUsuariosCodigoIn(List<Long> codigos){
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.in("codigo", codigos));
//		return criteria.list();
//	}
//
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
	
//	@SuppressWarnings("unchecked")
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
//	public Usuario buscarUsuarioPorCodigo(Long codigoUsuario){
//		Criteria c = this.createCriteria();
//		c.add(Restrictions.eq("codigo", codigoUsuario));
//		return (Usuario) c.uniqueResult();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Usuario> obtenerCriteriaSegunPrestador(){
//		Criteria criteria = this.createCriteria();
//		criteria.createAlias("prestador", "prestador");
//		criteria.add(Restrictions.eq("prestador.codigo", this.getUsuarioLogueado().getPrestador().getCodigo()));
//		criteria.add(Restrictions.eq("estadoUsuario.codigo", EstadoUsuario.ESTADO_ACTIVO.getCodigo()));
//		criteria.add(Restrictions.not(Restrictions.eq("codigo", new Long(this.getUsuarioLogueado().getPrestador().getCodigo()))));
//		criteria.add(Restrictions.not(Restrictions.eq("administrador", true)));
//		
//		return criteria.list();
//	}
//	
//	public List<SelectItem> obtenerUsuariosItemSegunPrestador(){
//    	List<Usuario> usuarios = this.obtenerCriteriaSegunPrestador();
//    	return this.asSelectItems(new ArrayList<Usuario>(usuarios));
//    }
//	
//	
//		//Administracion de Pacientes
//		public Criteria getCriteriaUsuariosPacientesDisponibles(Usuario administrador){
//			Criteria criteria = this.createCriteria();
//			criteria.add(Restrictions.isNotNull("paciente"));
//			criteria.add(Restrictions.eq("estadoUsuario.codigo", EstadoUsuario.ESTADO_PENDIENTE.getCodigo()));
//			criteria.add(Restrictions.eq("prestador.codigo", administrador.getPrestador().getCodigo()));
//			return criteria;
//		}
//		@SuppressWarnings("unchecked")
//		public Integer cantidadUsuariosPacientesPendientes(Usuario administrador){
//			Criteria criteria = this.getCriteriaUsuariosPacientesDisponibles(administrador);
//			criteria.setProjection(Projections.rowCount());
//		  	List result = criteria.list();
//		  	return result.isEmpty() ? new Integer(0) : (Integer)result.get(0);
//		}
//		@SuppressWarnings("unchecked")
//		public List<Usuario> listarUsuarios(Usuario administrador, int cantidadResultados, int offSet){
//			Criteria criteria = this.getCriteriaUsuariosPacientesDisponibles(administrador);
//			criteria.addOrder(Order.desc("codigo"));
//			criteria.setFirstResult(offSet);
//			criteria.setMaxResults(cantidadResultados);
//			
//			return criteria.list();
//		}
//	    
//		public Criteria obtenerCriteriaUsuariosCampaniaAdministrador(CampaniaForm campaniaForm){
//			
//			//Ningun Perfil
//	      	if( PerfilCampania.STR_NINGUNO.compareToIgnoreCase(campaniaForm.getPerfil())==0 )
//	      		return null;
//	      	
//	      	Criteria criteria = this.createCriteria();			
//			criteria.createAlias("prestador", "prestador");
//	    	criteria.add(Restrictions.eq("prestador.codigo", this.getUsuarioLogueado().getPrestador().getCodigo()));
//	    	criteria.add(Restrictions.not( Restrictions.eq("codigo", this.getUsuarioLogueado().getCodigo()) ));
//	    	criteria.add(Restrictions.not(Restrictions.and(Restrictions.isNull("paciente"), Restrictions.isNull("profesional"))));
//	    	criteria.add(Restrictions.eq("estadoUsuario.codigo", EstadoUsuario.ESTADO_ACTIVO.getCodigo()));
//	    	
//	    	boolean perfilPaciente=false;
//	    	boolean perfilMedico=false;	    	
//	    	
//	    	//si es paciente
//	    	if(PerfilCampania.STR_PACIENTE.equals(campaniaForm.getPerfil())){
//				criteria.createAlias("paciente", "paciente");
//				perfilPaciente=true;
//	    	}
//	    	//si es profesional
//	    	if(PerfilCampania.STR_MEDICO.equals(campaniaForm.getPerfil())){
//	    		criteria.createAlias("profesional", "profesional");
//	    		perfilMedico=true;
//	    	}
//	    	
//	    	//tipo paciente
//	    	if(!perfilMedico && StringUtils.isNotEmpty(campaniaForm.getTipoPaciente())){
//	    		if(perfilPaciente){
//	    			criteria.createAlias("paciente.tipoPaciente", "tipoPaciente");
//	    			criteria.add(Restrictions.eq("tipoPaciente.codigo", campaniaForm.getTipoPaciente()));
//	    		}else if (!perfilMedico){
//	    			criteria.createAlias("paciente", "paciente", CriteriaSpecification.LEFT_JOIN);
//	    			criteria.createAlias("paciente.tipoPaciente", "tipoPaciente");
//					criteria.add(Restrictions.eq("tipoPaciente.codigo", campaniaForm.getTipoPaciente()));
//	    		}
//	    	}
//	    	
//			if(perfilPaciente && campaniaForm.getPatologias()!=null && campaniaForm.getPatologias().size()!=0){
//				criteria.createAlias("paciente.patologias", "patologias");
//				criteria.add(Restrictions.in("patologias.codigo", campaniaForm.getPatologias()));
//			}
//			if(perfilMedico && campaniaForm.getEspecialidades()!=null && campaniaForm.getEspecialidades().size()!=0){
//				criteria.createAlias("profesional.especialidades", "especialidades");
//				criteria.add(Restrictions.in("especialidades.codigo", campaniaForm.getEspecialidades()));
//			}
//			if(campaniaForm.getGenero()!=null){
//				criteria.createAlias("genero", "genero");
//				if(Genero.STR_MASCULINO.equals(campaniaForm.getGenero())){
//					criteria.add(Restrictions.eq("genero.codigo", Genero.STR_MASCULINO));
//				}else if(Genero.STR_FEMENINO.equals(campaniaForm.getGenero())){
//					criteria.add(Restrictions.eq("genero.codigo", Genero.STR_FEMENINO));
//				}
//			}
//			if(campaniaForm.getDesdeEdad()!=null && campaniaForm.getHastaEdad()!=null){
//				criteria.add(Restrictions.between("fechaNacimiento", DateUtils.fechaNacimientoPara(campaniaForm.getHastaEdad()), DateUtils.fechaNacimientoPara(campaniaForm.getDesdeEdad()) ));  
//			}
//			if(campaniaForm.getUsuariosSeleccionados()!=null && campaniaForm.getUsuariosSeleccionados().size()!=0){
//				criteria.add(Restrictions.not(Restrictions.in("codigo", campaniaForm.getUsuariosSeleccionados())));
//			}
//			
//	    	return criteria;
//		}
//
//		@SuppressWarnings("unchecked")
//		public Integer obtenerCantidadUsuariosCampaniaAdministrador(CampaniaAction campaniaAction) {
//			
//			Integer cantUsuariosSeleccionados=0;
//	      	if(campaniaAction.getCampaniaForm().getUsuariosSeleccionados()!=null && campaniaAction.getCampaniaForm().getUsuariosSeleccionados().size()!=0)
//	      		cantUsuariosSeleccionados = campaniaAction.getCampaniaForm().getUsuariosSeleccionados().size();
//			
//			Criteria criteria = this.obtenerCriteriaUsuariosCampaniaAdministrador(campaniaAction.getCampaniaForm());
//			if(criteria==null)
//				return cantUsuariosSeleccionados;
//			ProjectionList projList = Projections.projectionList();
//	        projList.add(Projections.countDistinct("codigo"));
//	        criteria.setProjection(projList);
//	      	List result = criteria.list();
//	      	
//	      	return result.isEmpty() ? new Integer(0) : (Integer)result.get(0)+cantUsuariosSeleccionados;
//		}
//	    
//		@SuppressWarnings("unchecked")
//		public List<Usuario> obtenerUsuariosPorFiltroCampania(CampaniaAction campaniaAction){
//			Criteria criteria = this.obtenerCriteriaUsuariosCampaniaAdministrador(campaniaAction.getCampaniaForm());
//			if (criteria==null)
//				return new ArrayList<Usuario>();
//	    	List<Usuario> usuarios = (List<Usuario>) criteria.list();
//	    	return (new ArrayList<Usuario>(usuarios));
//	    }
//		
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
//		public String obtenerCvPorCodUsuario(Long codigoUsuario){
//			Criteria criteria = this.createCriteria();
//			criteria.add(Restrictions.eq("codigo", codigoUsuario));
//			criteria.createAlias("profesional", "profesional");			
//			ProjectionList projectionList = Projections.projectionList();
//			projectionList.add(Projections.property("profesional.pathCV"));
//			criteria.setProjection(projectionList);
//			return (String) criteria.uniqueResult();
//		}
//		
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
//		
//		public List<Usuario> getUsuariosPacientesPorEstado(Collection codigosEstado){
//			Criteria criteria = this.createCriteria();
//			criteria.add(Restrictions.isNotNull("paciente"));
//			criteria.add(Restrictions.in("estadoUsuario.codigo", codigosEstado));
//			
//			return criteria.list();
//		}
//		
//    public boolean existeUsuarioAfiliado(String numDocumento, String numeroAfiliado){
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("numDocumento", numDocumento));
//    	criteria.add(Restrictions.eq("numeroAfiliado", numeroAfiliado));
//    	List result = criteria.list();
//    	return result.isEmpty() ? false : true;
//    }
//
//	public List<Usuario> colegasConMismaEspecialidad(String codigoPrestador, List<Long> codigosEspecialidades){
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
//	@SuppressWarnings("unchecked")
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
//	@SuppressWarnings("unchecked")
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
//	@SuppressWarnings("unchecked")
//	public List<Usuario> usuariosPacientesPorClavePrestador(String codPrestador, String clave) {
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("prestador.codigo", codPrestador));
//		criteria.add(Restrictions.isNotNull("paciente"));
//
//		clave = StringUtils.limpiarString(clave);
//		String sqlRestApellido = "TRANSLATE(UPPER (apellido), 'ÁÉÍÓÚ','AEIOU') like '%"+clave.toUpperCase()+"%'";
//		String sqlRestNombre = "TRANSLATE(UPPER (nombre), 'ÁÉÍÓÚ','AEIOU') like '%"+clave.toUpperCase()+"%'";		
//		Criterion criterion;
//		criterion = Restrictions.or(Restrictions.sqlRestriction(sqlRestApellido), Restrictions.sqlRestriction(sqlRestNombre));		
//		criteria.add(criterion);
//		
//		return criteria.list();
//	}
//	
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
//	public List<Long> getCodigosProfesionalesPorPrestador(String codPrestador){
//		Criteria criteria = this.createCriteria();
//		criteria.createAlias("profesional", "profesional");
//		criteria.add(Restrictions.eq("prestador.codigo", codPrestador));
//		PropertyProjection propertyProjection = Projections.property("codigo");
//		criteria.setProjection(propertyProjection);
//		return criteria.list();
//	}
//
		
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
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.or(
//			Restrictions.eq("numDocumento", nroDocumento),
//			Restrictions.eq("numeroAfiliado", nroAfiliado)
//		));
//		return criteria.list().isEmpty() ? false : true;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq =  cb.createQuery(entityType);
		Root<Usuario> parametro = cq.from(entityType);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.or(cb.equal(parametro.get("numDocumento"), nroDocumento)
				,cb.equal(parametro.get("numeroAfiliado"), nroAfiliado)));
		
		cq.select(parametro).where(predicates.toArray(new Predicate[]{}));
		return !em.createQuery(cq).getResultList().isEmpty();
		
	}
//	
//	public List<Usuario> getUsuariosEstadoPre(Date fechaDesde){
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("estadoUsuario.codigo", EstadoUsuario.ESTADO_PRE_ACEPTADO.getCodigo()));
//		criteria.add(Restrictions.ge("fechaCreacion", fechaDesde));
//		return criteria.list();
//	}
//
	
}

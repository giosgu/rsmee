package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import utils.MensajeUtils;
import model.EstadoMensaje;
import model.Mensaje;
import model.MensajeDestino;
import model.TipoMensaje;
import model.Usuario;

public class MensajeDao extends BaseDao<Mensaje, Long> {

	private static final long serialVersionUID = -5274161441673199702L;

	public List<Mensaje> getMensajes(Long codigoUsuario){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mensaje> cq =  cb.createQuery(Mensaje.class);
		Root<Mensaje> mensaje = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();

		Join<Mensaje, MensajeDestino> mensajeDestino = mensaje.join("mensajeDestinos", JoinType.INNER);
		mensaje.join("mensajeRespuestas", JoinType.LEFT);
		Join<MensajeDestino, Usuario> usuarioDestino= mensajeDestino.join("usuarioDestino", JoinType.INNER);

		predicates.add(cb.equal(usuarioDestino.get("codigo"), codigoUsuario));
		
		cq.select(mensaje).where(predicates.toArray(new Predicate[]{})).orderBy(cb.asc(mensaje.get("codigo")));
		return em.createQuery(cq).getResultList();
    }

	public List<Mensaje> getMensajesUsuario(Usuario usuarioLogueado){
    	List<Mensaje> mensajes = this.getMensajes(usuarioLogueado.getCodigo());
    	return MensajeUtils.ordenarListaParaMostrar(mensajes);
    }

	/*@Deprecated usar getMensajesOrdenarListaParaMostrar y pasar código de usuario
	 * 
	 */
	@Deprecated 
	public List<Mensaje> getMensajesUsuario(){
		//al menos por ahora, para no romper los tests, los dao no tendrán acceso al contexto del usuario logueado
		//se reemplazó por método
    	//List<Mensaje> mensajes = this.getMensajes(this.getCodigoUsuario());
    	//return MensajeUtils.ordenarListaParaMostrar(mensajes);
		return null;
    }

	//TODO este método no se probó porque me da paja
	public List<Mensaje> getMensajesOrdenarListaParaMostrar(Long codigoUsuarioLogueado){
		List<Mensaje> mensajes = this.getMensajes(codigoUsuarioLogueado);
    	return MensajeUtils.ordenarListaParaMostrar(mensajes);
    }


  public CriteriaQuery<?> getCriteriaBusquedaMensajesNoLeidos(List<Long> codigosMisFamiliaresAcargo, Class<?> clazz, Long codigoUsuarioLogueado){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<?> cq =  cb.createQuery(clazz);
		Root<Mensaje> mensaje = cq.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<Mensaje, MensajeDestino> mensajeDestino = mensaje.join("mensajeDestinos", JoinType.INNER);
		Join<Mensaje, TipoMensaje> tipoMensaje = mensaje.join("tipoMensaje", JoinType.INNER);
		Join<MensajeDestino, Usuario> usuarioDestino = tipoMensaje.join("usuarioDestino", JoinType.INNER);
		
		predicates.add(cb.equal(tipoMensaje.get("codigo"), TipoMensaje.STR_MENSAJE));
		predicates.add(cb.equal(mensajeDestino.get("estadoMensaje").get("codigo"), EstadoMensaje.NO_LEIDO));
	  
		if(codigosMisFamiliaresAcargo.isEmpty()){
			predicates.add(cb.equal(usuarioDestino.get("codigo"), codigoUsuarioLogueado));
		}else{
			predicates.add(cb.or(cb.equal(usuarioDestino.get("codigo"), codigoUsuarioLogueado), 
					usuarioDestino.get("codigo").in(codigosMisFamiliaresAcargo)));
		}
		return cq.where(predicates.toArray(new Predicate[]{}));
}

	
//    public Criteria getCriteriaBusquedaMensajesNoLeidos(List<Long> codigosMisFamiliaresAcargo){
//    	HibernateSessionProxy proxy = (HibernateSessionProxy) this.getEntityManager().getDelegate();
//    	Criteria criteria = proxy.createCriteria(Mensaje.class, "m");
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//    	criteria.createAlias("tipoMensaje", "tipoMensaje");
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino");
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//    	criteria.add(Restrictions.eq("mensajeDestinos.estadoMensaje.codigo", EstadoMensaje.NO_LEIDO));
//    	if(codigosMisFamiliaresAcargo.isEmpty())
//    		criteria.add(Restrictions.eq("usuarioDestino.codigo", this.getCodigoUsuario()));
//    	else
//    		criteria.add(Restrictions.or(
//				Restrictions.eq("usuarioDestino.codigo", this.getCodigoUsuario()),    				
//				Restrictions.in("usuarioDestino.codigo", codigosMisFamiliaresAcargo)
//			));
//    	return criteria;
//    }
//
//	TODO
//    public Criteria getCriteriaBusquedaMensajesLeidos(List<Long> codigosMisFamiliaresAcargo){    	
//    	HibernateSessionProxy proxy = (HibernateSessionProxy) this.getEntityManager().getDelegate();
//    	Criteria criteria = proxy.createCriteria(Mensaje.class, "m");
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino");
//    	criteria.createAlias("tipoMensaje", "tipoMensaje");
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//    	criteria.add(Restrictions.eq("mensajeDestinos.estadoMensaje.codigo", EstadoMensaje.LEIDO));
//    	if(codigosMisFamiliaresAcargo.isEmpty())
//    		criteria.add(Restrictions.eq("usuarioDestino.codigo", this.getCodigoUsuario()));
//    	else
//    		criteria.add(Restrictions.or(
//				Restrictions.eq("usuarioDestino.codigo", this.getCodigoUsuario()),    				
//				Restrictions.in("usuarioDestino.codigo", codigosMisFamiliaresAcargo)
//			));
//    	return criteria;
//    }
//    


//	public List<Mensaje> getMensajesNoLeidos(Usuario usuario){
//		List<Long> codigosMisFamiliaresAcargo = new ArrayList<Long>();
//		if(usuario.getPaciente()!=null){
//			MenoresACargoList menoresACargoList = (MenoresACargoList) Component.getInstance(MenoresACargoList.class);			
//			codigosMisFamiliaresAcargo.addAll(menoresACargoList.codigosMisFamiliaresACargo());
//		}
//  	Criteria criteria = this.getCriteriaBusquedaMensajesNoLeidos(codigosMisFamiliaresAcargo);
//  	List<Mensaje> listaMensajesNoLeidos = criteria.list();
//  	return MensajeUtils.ordenarListaParaMostrar(listaMensajesNoLeidos);
//  }
 
 //	TODO
//	public List<Mensaje> getMensajesNoLeidos(){
//		List<Long> codigosMisFamiliaresAcargo = new ArrayList<Long>();
//		if(this.getUsuario().getPaciente()!=null){
//			MenoresACargoList menoresACargoList = (MenoresACargoList) Component.getInstance(MenoresACargoList.class);			
//			codigosMisFamiliaresAcargo.addAll(menoresACargoList.codigosMisFamiliaresACargo());
//		}
//    	Criteria criteria = this.getCriteriaBusquedaMensajesNoLeidos(codigosMisFamiliaresAcargo);
//    	List<Mensaje> listaMensajesNoLeidos = criteria.list();
//    	return MensajeUtils.ordenarListaParaMostrar(listaMensajesNoLeidos);
//    }
//	
//	TODO
//	public List<Mensaje> getMensajesLeidos(){
//		List<Long> codigosMisFamiliaresAcargo = new ArrayList<Long>();
//		if(this.getUsuario().getPaciente()!=null){
//			MenoresACargoList menoresACargoList = (MenoresACargoList) Component.getInstance(MenoresACargoList.class);			
//			codigosMisFamiliaresAcargo.addAll(menoresACargoList.codigosMisFamiliaresACargo());
//		}
//    	Criteria criteria = this.getCriteriaBusquedaMensajesLeidos(codigosMisFamiliaresAcargo);
//    	List<Mensaje> listaMensajesNoLeidos = criteria.list();
//    	//filtro los que tienen respuesta leida
//    	List<Mensaje> listaFinal = (List<Mensaje>) CollectionUtils.select(listaMensajesNoLeidos, MensajeUtils.filtarMensajesConRespuestasLeidas());
//    	return MensajeUtils.ordenarListaParaMostrar(listaFinal);
//    }
//	
//	TODO
//	public Integer cantidadMensajesNoLeidos(){
//		List<Long> codigosMisFamiliaresAcargo = new ArrayList<Long>();
//		if(this.getUsuario().getPaciente()!=null){
//			MenoresACargoList menoresACargoList = (MenoresACargoList) Component.getInstance(MenoresACargoList.class);			
//			codigosMisFamiliaresAcargo.addAll(menoresACargoList.codigosMisFamiliaresACargo());
//		}
//    	Criteria criteria = this.getCriteriaBusquedaMensajesNoLeidos(codigosMisFamiliaresAcargo);
//      	criteria.setProjection(Projections.rowCount());
//      	List result = criteria.list();
//      	return result.isEmpty() ? new Integer(0) : (Integer)result.get(0);
//    }
//	
//	TODO
//	public Integer cantidadCampaniasNoLeidas(){
//    	Criteria criteria = this.getCriteriaCampaniasNoLeidas();
//      	criteria.setProjection(Projections.rowCount());
//      	List result = criteria.list();
//      	return result.isEmpty() ? new Integer(0) : (Integer)result.get(0);
//    }
//	
//	TODO
//	public Integer cantidadCampaniasNoLeidasPaciente(List<Long> codigosUsuarios){
//    	Criteria criteria = this.getCriteriaCampaniasNoLeidasUsuarios(codigosUsuarios);
//    	criteria.setProjection(Projections.countDistinct("codigo"));
//      	List result = criteria.list();
//      	return result.isEmpty() ? new Integer(0) : (Integer)result.get(0);
//    }
//
//	TODO
//    public Mensaje getMensaje(Long codigoMensaje){
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("codigo", codigoMensaje));
//    	Mensaje mensaje = (Mensaje)criteria.uniqueResult();
//    	MensajeRespuestaList mensajeRespuestaList = (MensajeRespuestaList) Component.getInstance(MensajeRespuestaList.class);
//    	List<MensajeRespuesta> respuestas =  mensajeRespuestaList.getMensajesRespuesta(mensaje);
//    	mensaje.setMensajeRespuestas(new HashSet<MensajeRespuesta>(respuestas));
//		return mensaje;
//    }
//
//	TODO
//    public Mensaje getCampania(Long codigoCampania){
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("codigo", codigoCampania));
//    	Mensaje mensaje = (Mensaje)criteria.uniqueResult();
//		return mensaje;
//    }
//    
//	TODO
//    public String getComentarioAdministrador(Long codigoMensaje){
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("codigo", codigoMensaje));
//    	Mensaje mensaje = (Mensaje)criteria.uniqueResult();
//    	return mensaje.getComenatrioAdministrador();
//    }
//
//	TODO
//	public Mensaje getObjectByID(Long id) {
//		Criteria criteria = this.createCriteria();
//		return (Mensaje) criteria.add(Restrictions.eq("codigo", id)).uniqueResult();
//	}
//	
//	TODO
//	public Criteria getCriteriaObjectByID(Long id) {
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("codigo", id));
//		return criteria;
//	}
//	
//	TODO
//	//Campanias No Leidas
//    public Criteria getCriteriaCampaniasNoLeidas(){
//    	HibernateSessionProxy proxy = (HibernateSessionProxy) this.getEntityManager().getDelegate();
//    	Criteria criteria = proxy.createCriteria(Mensaje.class, "m");
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//    	criteria.createAlias("tipoMensaje", "tipoMensaje");
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino");    	
//    	criteria.add(Restrictions.eq("usuarioDestino.codigo", this.getCodigoUsuario()));
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_CAMPANIA));
//    	criteria.add(Restrictions.eq("mensajeDestinos.estadoMensaje.codigo", EstadoMensaje.NO_LEIDO));
//    	return criteria;
//    }
	
//	TODO
//    //Campanias No Leidas Grupo Familiar
//    public Criteria getCriteriaCampaniasNoLeidasUsuarios(List<Long> codigosUsuarios){
//    	Criteria criteria = this.createCriteria();
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//    	criteria.createAlias("tipoMensaje", "tipoMensaje");
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino");
//    	criteria.add(Restrictions.in("usuarioDestino.codigo", codigosUsuarios));
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_CAMPANIA));
//    	criteria.add(Restrictions.eq("mensajeDestinos.estadoMensaje.codigo", EstadoMensaje.NO_LEIDO));
//    	return criteria;    	
//    }    
//    
//	TODO
//	public List<Long> getCodigosCampaniasNoLeidasByCodigosUsuarios(List<Long> codigosUsuarios){
//    	Criteria criteria = getCriteriaCampaniasNoLeidasUsuarios(codigosUsuarios);
//    	criteria.setProjection(Projections.distinct(Projections.property("codigo")));
//    	return (List<Long>) criteria.list();
//    }
	
//	TODO
//    public Criteria getCriteriaCampaniasNoLeidasByCodigosUsuarios(List<Long> codigosUsuarios){
//    	List<Long> codigosMensajes = getCodigosCampaniasNoLeidasByCodigosUsuarios(codigosUsuarios);
//    	Criteria criteria = this.createCriteria();
//    	if(codigosMensajes.isEmpty()){
//        	criteria = getCriteriaCampaniasNoLeidasUsuarios(codigosUsuarios);
//    		return criteria;
//    	}else
//    		criteria.add(Restrictions.in("codigo", codigosMensajes));
//    	return criteria;
//    }
//    //	TODO
//    //Campanias Leidas
//    public Criteria getCriteriaCampaniasLeidas(){
//    	HibernateSessionProxy proxy = (HibernateSessionProxy) this.getEntityManager().getDelegate();
//    	Criteria criteria = proxy.createCriteria(Mensaje.class, "m");
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//    	criteria.createAlias("tipoMensaje", "tipoMensaje");
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino");
//    	criteria.createAlias("mensajeDestinos.usuarioOrigen", "usuarioOrigen");
//    	criteria.add(Restrictions.eq("usuarioDestino.codigo", this.getCodigoUsuario()));
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_CAMPANIA));    
//    	//Campaña Leida u Origen Rechazada
//    	criteria.add(Restrictions.or(Restrictions.eq("mensajeDestinos.estadoMensaje.codigo", EstadoMensaje.LEIDO), 
//    			Restrictions.and(Restrictions.eq("mensajeDestinos.estadoMensaje.codigo", EstadoMensaje.RECHAZADO),
//    			Restrictions.eq("usuarioOrigen.codigo", this.getCodigoUsuario()))));    	
//    	return criteria;
//    }
	
//	TODO
//    //Campanias Leidas Grupo Familiar
//    public Criteria criteriaCampaniasLeidasUsuarios(List<Long> codigosUsuarios){
//    	Criteria criteria = this.createCriteria();
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//    	criteria.createAlias("tipoMensaje", "tipoMensaje");
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino");
//    	criteria.add(Restrictions.in("usuarioDestino.codigo", codigosUsuarios));
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_CAMPANIA));
//    	criteria.add(Restrictions.eq("mensajeDestinos.estadoMensaje.codigo", EstadoMensaje.LEIDO));
//    	return criteria;
//    }
//	
//	TODO
//	public List<Long> getCodigosCampaniasLeidasByCodigosUsuarios(List<Long> codigosUsuarios){
//    	Criteria criteria = criteriaCampaniasLeidasUsuarios(codigosUsuarios);
//    	criteria.setProjection(Projections.distinct(Projections.property("codigo")));
//    	return (List<Long>) criteria.list();
//    }
	
//	TODO
//    public Criteria getCriteriaCampaniasLeidasByCodigosUsuarios(List<Long> codigosUsuarios){
//    	List<Long> codigosMensajes = getCodigosCampaniasLeidasByCodigosUsuarios(codigosUsuarios);
//    	Criteria criteria = this.createCriteria();
//    	if(codigosMensajes.isEmpty()){
//        	criteria = criteriaCampaniasLeidasUsuarios(codigosUsuarios);
//    		return criteria;
//    	}else
//    		criteria.add(Restrictions.in("codigo", codigosMensajes));
//    	return criteria;
//    }
//    
//	TODO
//    //Auditoría
//    public Integer getCantidadMensajesAuditoria(AuditoriaDTO auditoriaDTO){
//    	Criteria criteria = getCriteriaMensajesAuditoria(auditoriaDTO);
//    	if(criteria==null)
//    		return 0;
//    	ProjectionList projectionList = Projections.projectionList();
//    	projectionList.add(Projections.countDistinct("codigo"));
//    	criteria.setProjection(projectionList);
//    	List result = criteria.list();
//    	return result.isEmpty() ? 0 : (Integer) result.get(0);
//    }
	
//	TODO
//	public Integer getTotalAuditadasAditoria(AuditoriaDTO auditoriaDTO) {
//		boolean auditadaEnDTO = auditoriaDTO.getAuditada();//fix cambio valor
//		auditoriaDTO.setAuditada(true);
//		Criteria criteria = getCriteriaMensajesAuditoria(auditoriaDTO);
//		auditoriaDTO.setAuditada(auditadaEnDTO);
//    	if(criteria==null)
//    		return 0;
//    	criteria.add(Restrictions.isNotNull("auditoriaConsulta"));
//    	ProjectionList projectionList = Projections.projectionList();
//    	projectionList.add(Projections.countDistinct("codigo"));
//    	criteria.setProjection(projectionList);
//    	List result = criteria.list();
//    	return result.isEmpty() ? 0 : (Integer) result.get(0);
//	}
//	
//	TODO
//    public Criteria getCriteriaMensajesAuditoria(AuditoriaDTO auditoriaDTO){
//    	System.out.println("mensajeList -> getCriteriaMensajesAuditoria(AuditoriaDTO auditoriaDTO)");
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//    	criteria.add(Restrictions.eq("noEsConsultaMedica", false));    	    	
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos" );
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino" );
//    	criteria.createAlias("origen", "origen");
//    	criteria.add(Restrictions.isNotNull("origen.paciente"));
//    	criteria.add(Restrictions.isNotNull("usuarioDestino.profesional"));
//    	criteria.createAlias("mensajeRespuestas", "mensajeRespuestas");
//    	criteria.add(Restrictions.between("mensajeRespuestas.fecha", 
//    		DateUtils.cambiarTiempo(auditoriaDTO.getFechaDesde(), 00, 00, 00, 000), 
//    		DateUtils.cambiarTiempo(auditoriaDTO.getFechaHasta(), 23, 59, 59, 999)
//		));
//    	
//	TODO
//    	//Respuesta del médico al paciente
//    	DetachedCriteria dc = DetachedCriteria.forClass(MensajeRespuesta.class, "mr2");
//    	dc.setProjection(Projections.projectionList().add(Projections.min("mr2.codigo")));
//    	dc.createAlias("mr2.mensaje", "mr2Mensaje");
//    	dc.add(Restrictions.eqProperty("mr2Mensaje.codigo", criteria.getAlias()+".codigo"));
//    	dc.createAlias("mr2.mensajeRespuestaDestinos", "mensajeRespuestaDestinos");
//    	dc.createAlias("mensajeRespuestaDestinos.usuarioOrigen", "usuarioOrigenRta");
//    	dc.createAlias("mensajeRespuestaDestinos.usuarioDestino", "usuarioDestinoRta");
//    	dc.add(Restrictions.isNotNull("usuarioOrigenRta.profesional"));
//    	dc.add(Restrictions.isNotNull("usuarioDestinoRta.paciente"));
//    	criteria.add(Subqueries.propertyEq("mensajeRespuestas.codigo", dc));
//    	
//    	if(!auditoriaDTO.getModoPacienteUnico()){
//			if(!auditoriaDTO.getEspecialidades().isEmpty()){
//				criteria.createAlias("usuarioDestino.profesional", "profesionalDestino");
//				criteria.createAlias("profesionalDestino.especialidades", "especialidadesProfesionalDestino");
//				criteria.add(Restrictions.in("especialidadesProfesionalDestino.codigo", auditoriaDTO.getEspecialidades()));
//			}
//			if(!auditoriaDTO.getUsuariosMedicos().isEmpty())
//				criteria.add(Restrictions.in("usuarioDestino.codigo", auditoriaDTO.getUsuariosMedicos()));
//			if(!auditoriaDTO.getUsuariosMedicos().isEmpty() && !auditoriaDTO.getUsuariosPacientes().isEmpty())
//				criteria.add(Restrictions.in("origen.codigo", auditoriaDTO.getUsuariosPacientes()));
//		}else//paciente único
//			criteria.add(Restrictions.eq("origen.codigo", auditoriaDTO.getUnicoPaciente().getCodigo()));
//    	
//		if(!auditoriaDTO.getAuditada())
//			criteria.add(Restrictions.isNull("auditoriaConsulta"));
//    	
//    	return criteria;
//    }
//    
//	TODO
//    //Reportar Auditoria - Créditos
//	public List<Mensaje> getMsjAuditoriaCreditos(Date fechaDesde, Date fechaHasta, Long codMedico){
//    	System.out.println("mensajeList -> getMsjAuditoriaCreditos(Date fechaDesde, Date fechaHasta, Long codMedico)");
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//    	criteria.add(Restrictions.eq("noEsConsultaMedica", false));
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos" );
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino" );
//    	criteria.createAlias("origen", "origen");
//    	criteria.add(Restrictions.isNotNull("origen.paciente"));
//    	criteria.add(Restrictions.isNotNull("usuarioDestino.profesional"));    	
//    	criteria.createAlias("mensajeRespuestas", "mensajeRespuestas");
//    	criteria.add(Restrictions.between("mensajeRespuestas.fecha", 
//    		DateUtils.cambiarTiempo(fechaDesde, 00, 00, 00, 000),
//    		DateUtils.cambiarTiempo(fechaHasta, 23, 59, 59, 999)
//		));
//    	criteria.createAlias("auditoriaConsulta", "auditoriaConsulta", CriteriaSpecification.LEFT_JOIN);
//    	criteria.createAlias("auditoriaConsulta.estadoAuditoriaConsulta", "estadoAuditoriaConsulta", CriteriaSpecification.LEFT_JOIN);
//    	/*criteria.add(Restrictions.or(
//			Restrictions.isNull("auditoriaConsulta"),
//			Restrictions.in("estadoAuditoriaConsulta.codigo", new String[]{EstadoAuditoriaConsulta.ACEPTADA.getCodigo(),EstadoAuditoriaConsulta.RECHAZADA.getCodigo()})
//		));*/
//    	//Respuesta del médico al paciente
//    	DetachedCriteria dc = DetachedCriteria.forClass(MensajeRespuesta.class, "mr2");
//    	dc.setProjection(Projections.projectionList().add(Projections.min("mr2.codigo")));
//    	dc.createAlias("mr2.mensaje", "mr2Mensaje");
//    	dc.add(Restrictions.eqProperty("mr2Mensaje.codigo", criteria.getAlias()+".codigo"));
//    	dc.createAlias("mr2.mensajeRespuestaDestinos", "mensajeRespuestaDestinos");
//    	dc.createAlias("mensajeRespuestaDestinos.usuarioOrigen", "usuarioOrigenRta");
//    	dc.createAlias("mensajeRespuestaDestinos.usuarioDestino", "usuarioDestinoRta");
//    	dc.add(Restrictions.isNotNull("usuarioOrigenRta.profesional"));
//    	dc.add(Restrictions.isNotNull("usuarioDestinoRta.paciente"));
//    	criteria.add(Subqueries.propertyEq("mensajeRespuestas.codigo", dc));
//    	
//		criteria.add(Restrictions.eq("usuarioDestino.codigo", codMedico));
//		
//		return criteria.list();
//    }
//	
	
//	TODO
//    //Reportar Auditoria - Débitos
//	public List<Mensaje> getMsjAuditoriaDebitos(Date fechaDesde, Date fechaHasta, Long codMedico){
//    	System.out.println("mensajeList -> getMsjAuditoriaDebitos(Date fechaDesde, Date fechaHasta, Long codMedico)");
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//    	criteria.add(Restrictions.eq("noEsConsultaMedica", false)); //TODO ver esto
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino");
//    	criteria.createAlias("origen", "origen");
//    	criteria.add(Restrictions.isNotNull("origen.paciente"));
//    	criteria.add(Restrictions.isNotNull("usuarioDestino.profesional"));    	
//    	//Auditadas rechazadas
//    	criteria.createAlias("auditoriaConsulta", "auditoriaConsulta");
//    	criteria.createAlias("auditoriaConsulta.estadoAuditoriaConsulta", "estadoAuditoriaConsulta");
//    	criteria.add(Restrictions.eq("estadoAuditoriaConsulta.codigo", EstadoAuditoriaConsulta.RECHAZADA.getCodigo()));			
//    	criteria.add(Restrictions.between("auditoriaConsulta.fecha", 
//    		DateUtils.cambiarTiempo(fechaDesde, 00, 00, 00, 000),
//    		DateUtils.cambiarTiempo(fechaHasta, 23, 59, 59, 999)
//		));
//    	
//    	criteria.add(Restrictions.eq("usuarioDestino.codigo", codMedico));
//		
//		return criteria.list();
//    }
//
//	TODO
//    //Reportar Auditoria - No Médicas
//	public List<Mensaje> getMsjAuditoriaNoMedicas(Date fechaDesde, Date fechaHasta, Long codMedico){
//    	System.out.println("mensajeList -> getMsjAuditoriaNoMedicas(Date fechaDesde, Date fechaHasta, Long codMedico)");
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//    	criteria.add(Restrictions.eq("noEsConsultaMedica", true));//No médica
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos" );
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino" );
//    	criteria.createAlias("origen", "origen");
//    	criteria.add(Restrictions.isNotNull("origen.paciente"));
//    	criteria.add(Restrictions.isNotNull("usuarioDestino.profesional"));    	
//    	criteria.createAlias("mensajeRespuestas", "mensajeRespuestas");
//    	criteria.add(Restrictions.between("mensajeRespuestas.fecha", 
//    		DateUtils.cambiarTiempo(fechaDesde, 00, 00, 00, 000),
//    		DateUtils.cambiarTiempo(fechaHasta, 23, 59, 59, 999)
//		));
//    	//Respuesta del médico al paciente
//    	DetachedCriteria dc = DetachedCriteria.forClass(MensajeRespuesta.class, "mr2");
//    	dc.setProjection(Projections.projectionList().add(Projections.min("mr2.codigo")));
//    	dc.createAlias("mr2.mensaje", "mr2Mensaje");
//    	dc.add(Restrictions.eqProperty("mr2Mensaje.codigo", criteria.getAlias()+".codigo"));
//    	dc.createAlias("mr2.mensajeRespuestaDestinos", "mensajeRespuestaDestinos");
//    	dc.createAlias("mensajeRespuestaDestinos.usuarioOrigen", "usuarioOrigenRta");
//    	dc.createAlias("mensajeRespuestaDestinos.usuarioDestino", "usuarioDestinoRta");
//    	dc.add(Restrictions.isNotNull("usuarioOrigenRta.profesional"));
//    	dc.add(Restrictions.isNotNull("usuarioDestinoRta.paciente"));
//    	criteria.add(Subqueries.propertyEq("mensajeRespuestas.codigo", dc));
//    	
//		criteria.add(Restrictions.eq("usuarioDestino.codigo", codMedico));
//		
//		return criteria.list();
//    }
//
//	TODO
//    //Reportar Auditoria - Otras
//	public List<Mensaje> getMsjAuditoriaOtras(Date fechaDesde, Date fechaHasta, Long codMedico){
//    	System.out.println("mensajeList -> getMsjAuditoriaOtras(Date fechaDesde, Date fechaHasta, Long codMedico)");
//    	Criteria criteria = this.createCriteria();
//    	criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//    	criteria.add(Restrictions.eq("noEsConsultaMedica", false));
//    	
//    	criteria.add(Restrictions.between("fecha", 
//    		DateUtils.cambiarTiempo(fechaDesde, 00, 00, 00, 000),
//    		DateUtils.cambiarTiempo(fechaHasta, 23, 59, 59, 999)
//		));
//    	
//    	//A) De médico a médico
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos" );
//    	criteria.createAlias("mensajeDestinos.usuarioDestino", "usuarioDestino");
//    	criteria.createAlias("origen", "origen");    	
//    	Criterion medicoAMedico;
//    	medicoAMedico = Restrictions.isNotNull("origen.profesional");
//    	medicoAMedico = Restrictions.and(medicoAMedico, Restrictions.isNotNull("usuarioDestino.profesional"));
//    	//B) Paciente a medico sin respuesta
//    	Criterion pacienteAMedicoSinRta;
//    	pacienteAMedicoSinRta = Restrictions.isNotNull("origen.paciente");
//    	pacienteAMedicoSinRta = Restrictions.and(pacienteAMedicoSinRta, Restrictions.isNotNull("usuarioDestino.profesional"));
//    	//B1) Sin respuesta
//    	DetachedCriteria dc = DetachedCriteria.forClass(MensajeRespuesta.class, "mr2");
//    	dc.setProjection(Projections.projectionList().add(Projections.count("mr2.codigo")));
//    	dc.createAlias("mr2.mensaje", "mr2Mensaje");
//    	dc.add(Restrictions.eqProperty("mr2Mensaje.codigo", criteria.getAlias()+".codigo"));
//    	dc.createAlias("mr2.mensajeRespuestaDestinos", "mensajeRespuestaDestinos");
//    	dc.createAlias("mensajeRespuestaDestinos.usuarioOrigen", "usuarioOrigenRta");
//    	dc.createAlias("mensajeRespuestaDestinos.usuarioDestino", "usuarioDestinoRta");
//    	dc.add(Restrictions.isNotNull("usuarioOrigenRta.profesional"));
//    	dc.add(Restrictions.isNotNull("usuarioDestinoRta.paciente"));
//    	pacienteAMedicoSinRta = Restrictions.and(pacienteAMedicoSinRta, Subqueries.eq(0, dc));
//    	//A o B
//    	criteria.add(Restrictions.or(medicoAMedico, pacienteAMedicoSinRta));
//    	
//		criteria.add(Restrictions.eq("usuarioDestino.codigo", codMedico));
//		
//		return criteria.list();
//    }
//	
//	TODO
//	//Búsqueda consultas, usuarios que enviaron una consulta a X.
//	public List<Usuario> usuariosQueConsultaronA(Long codUsuarioConsultado){
//		Criteria criteria = this.createCriteria();
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//		criteria.createAlias("mensajeDestinos.usuarioDestino", "mensajeDestinoUsuarioDestino");
//		criteria.createAlias("mensajeDestinos.usuarioOrigen", "mensajeDestinoUsuarioOrigen");
//		criteria.add(Restrictions.eq("mensajeDestinoUsuarioDestino.codigo", codUsuarioConsultado));
//		criteria.createAlias("origen", "origenMensaje");
//		criteria.add(Restrictions.eqProperty("origenMensaje.codigo", "mensajeDestinoUsuarioOrigen.codigo"));
//		ProjectionList projectionList = Projections.projectionList();
//		projectionList.add(Projections.distinct(Projections.property("origen")));
//		criteria.setProjection(projectionList);
//		return criteria.list();
//	}
//	
//	TODO
//	public Criteria buscarConsultasFiltro(Long codUsuarioDestino, List<Long> codUsuariosOrigen, 
//			Date fechaDesde, Date fechaHasta, boolean cerradas, boolean noMedicas) {
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//    	criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//		criteria.createAlias("mensajeDestinos.usuarioDestino", "mensajeDestinoUsuarioDestino");
//		criteria.createAlias("mensajeDestinos.usuarioOrigen", "mensajeDestinoUsuarioOrigen");
//		criteria.add(Restrictions.eq("mensajeDestinoUsuarioDestino.codigo", codUsuarioDestino));
//		criteria.createAlias("origen", "origenMensaje");
//		criteria.add(Restrictions.eqProperty("origenMensaje.codigo", "mensajeDestinoUsuarioOrigen.codigo"));
//		
//    	criteria.add(Restrictions.between("fecha", 
//    		DateUtils.cambiarTiempo(fechaDesde, 00, 00, 00, 000),
//    		DateUtils.cambiarTiempo(fechaHasta, 23, 59, 59, 999)
//		));
//		if(!codUsuariosOrigen.isEmpty())
//			criteria.add(Restrictions.in("mensajeDestinoUsuarioOrigen.codigo", codUsuariosOrigen));
//		if(cerradas)
//			criteria.add(Restrictions.eq("finalizado", true));
//		if(noMedicas)
//			criteria.add(Restrictions.eq("noEsConsultaMedica", true));
//		
//		return criteria;
//	}
//	
//	TODO
//	//Obtener destinatario mensaje
//	public Usuario destinoPorCodigoMensaje(Long codMensaje){
//		Criteria criteria = this.createCriteria();
//		criteria.add(Restrictions.eq("codigo", codMensaje));
//		criteria.add(Restrictions.eq("tipoMensaje.codigo", TipoMensaje.STR_MENSAJE));
//		criteria.createAlias("mensajeDestinos", "mensajeDestinos");
//		criteria.createAlias("mensajeDestinos.usuarioDestino", "mensajeDestinoUsuarioDestino");
//		criteria.createAlias("mensajeDestinos.usuarioOrigen", "mensajeDestinoUsuarioOrigen");
//		criteria.add(Restrictions.not(Restrictions.eqProperty("mensajeDestinoUsuarioDestino", "mensajeDestinoUsuarioOrigen")));
//		return (Usuario) criteria.list();
//	}

}

package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.AuditoriaConsulta;
import model.EstadoAuditoriaConsulta;
import model.MotivoRechazoConsultaAudicion;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuditoriaConsulta.class)
public abstract class AuditoriaConsulta_ {

	public static volatile SingularAttribute<AuditoriaConsulta, String> comentarioAuditor;
	public static volatile SingularAttribute<AuditoriaConsulta, Long> codigo;
	public static volatile SingularAttribute<AuditoriaConsulta, EstadoAuditoriaConsulta> estadoAuditoriaConsulta;
	public static volatile SingularAttribute<AuditoriaConsulta, MotivoRechazoConsultaAudicion> motivoRechazoConsultaAudicion;
	public static volatile SingularAttribute<AuditoriaConsulta, Usuario> auditor;
	public static volatile SingularAttribute<AuditoriaConsulta, Date> fecha;

}


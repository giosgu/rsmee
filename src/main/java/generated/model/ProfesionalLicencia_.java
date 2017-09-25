package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.ProfesionalLicencia;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfesionalLicencia.class)
public abstract class ProfesionalLicencia_ {

	public static volatile SingularAttribute<ProfesionalLicencia, Long> codigo;
	public static volatile SingularAttribute<ProfesionalLicencia, Date> fechaDesde;
	public static volatile SingularAttribute<ProfesionalLicencia, Date> fechaHasta;
	public static volatile SingularAttribute<ProfesionalLicencia, Usuario> suplente;
	public static volatile SingularAttribute<ProfesionalLicencia, Usuario> titular;

}


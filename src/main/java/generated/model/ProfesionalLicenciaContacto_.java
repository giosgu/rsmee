package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Contacto;
import model.ProfesionalLicencia;
import model.ProfesionalLicenciaContacto;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfesionalLicenciaContacto.class)
public abstract class ProfesionalLicenciaContacto_ {

	public static volatile SingularAttribute<ProfesionalLicenciaContacto, Long> codigo;
	public static volatile SingularAttribute<ProfesionalLicenciaContacto, Contacto> contacto;
	public static volatile SingularAttribute<ProfesionalLicenciaContacto, ProfesionalLicencia> profesionalLicencia;

}


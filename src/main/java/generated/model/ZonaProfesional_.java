package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Profesional;
import model.Zona;
import model.ZonaProfesional;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ZonaProfesional.class)
public abstract class ZonaProfesional_ {

	public static volatile SingularAttribute<ZonaProfesional, Long> codigo;
	public static volatile SingularAttribute<ZonaProfesional, Profesional> codigoProfesional;
	public static volatile SingularAttribute<ZonaProfesional, Zona> codigoZona;

}


package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.OlvidoClave;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OlvidoClave.class)
public abstract class OlvidoClave_ extends CreateAuditoryDataEntity_{

	public static volatile SingularAttribute<OlvidoClave, Long> codigo;
	public static volatile SingularAttribute<OlvidoClave, String> hash;
	public static volatile SingularAttribute<OlvidoClave, Usuario> usuario;
	public static volatile SingularAttribute<OlvidoClave, Date> fechaGeneracion;
	public static volatile SingularAttribute<OlvidoClave, Boolean> activado;

}


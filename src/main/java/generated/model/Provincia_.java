package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Pais;
import model.Provincia;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Provincia.class)
public abstract class Provincia_ extends CreateAuditoryDataEntity_ {

	public static volatile SingularAttribute<Provincia, String> codigo;
	public static volatile SingularAttribute<Provincia, String> value;
	public static volatile SingularAttribute<Provincia, String> descripcion;
	public static volatile SingularAttribute<Provincia, String> label;
	public static volatile SingularAttribute<Provincia, Pais> codigoPais;

}


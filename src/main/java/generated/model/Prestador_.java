package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Localidad;
import model.Pais;
import model.Prestador;
import model.Provincia;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prestador.class)
public abstract class Prestador_ extends CreateAuditoryDataEntity_ {

	public static volatile SingularAttribute<Prestador, String> codigo;
	public static volatile SingularAttribute<Prestador, String> urlWeb;
	public static volatile SingularAttribute<Prestador, Localidad> localidad;
	public static volatile SingularAttribute<Prestador, Integer> cantidadMaximaEspecialidades;
	public static volatile SingularAttribute<Prestador, String> denominacion;
	public static volatile SingularAttribute<Prestador, String> cuit;
	public static volatile SingularAttribute<Prestador, Pais> pais;
	public static volatile SingularAttribute<Prestador, String> domicilio;
	public static volatile SingularAttribute<Prestador, String> descripcion;
	public static volatile SingularAttribute<Prestador, Provincia> provincia;

}


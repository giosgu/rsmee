package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.EstadoSucursal;
import model.Farmacia;
import model.Localidad;
import model.Sucursal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sucursal.class)
public abstract class Sucursal_ {

	public static volatile SingularAttribute<Sucursal, String> altitud;
	public static volatile SingularAttribute<Sucursal, Long> codigo;
	public static volatile SingularAttribute<Sucursal, EstadoSucursal> estadoSucursal;
	public static volatile SingularAttribute<Sucursal, String> pathImagen;
	public static volatile SingularAttribute<Sucursal, Localidad> localidad;
	public static volatile SingularAttribute<Sucursal, String> direccion;
	public static volatile SingularAttribute<Sucursal, String> email;
	public static volatile SingularAttribute<Sucursal, Farmacia> farmacia;
	public static volatile SingularAttribute<Sucursal, String> telefono;
	public static volatile SingularAttribute<Sucursal, String> longitud;
	public static volatile SingularAttribute<Sucursal, String> atencionHorario;

}


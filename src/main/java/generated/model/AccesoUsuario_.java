package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.AccesoUsuario;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccesoUsuario.class)
public abstract class AccesoUsuario_ {

	public static volatile SingularAttribute<AccesoUsuario, String> navegador;
	public static volatile SingularAttribute<AccesoUsuario, Long> codigo;
	public static volatile SingularAttribute<AccesoUsuario, String> dispositivo;
	public static volatile SingularAttribute<AccesoUsuario, String> sistemaOperativo;
	public static volatile SingularAttribute<AccesoUsuario, Usuario> usuario;
	public static volatile SingularAttribute<AccesoUsuario, Date> fechaEgreso;
	public static volatile SingularAttribute<AccesoUsuario, Date> fechaIngreso;
	public static volatile SingularAttribute<AccesoUsuario, String> navegadorDetalle;

}


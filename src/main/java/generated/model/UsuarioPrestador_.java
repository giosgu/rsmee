package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Prestador;
import model.Usuario;
import model.UsuarioPrestador;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuarioPrestador.class)
public abstract class UsuarioPrestador_ {

	public static volatile SingularAttribute<UsuarioPrestador, Long> codigo;
	public static volatile SingularAttribute<UsuarioPrestador, Date> fechaVigenciaDesde;
	public static volatile SingularAttribute<UsuarioPrestador, Date> fechaVigenciaHasta;
	public static volatile SingularAttribute<UsuarioPrestador, Long> primario;
	public static volatile SingularAttribute<UsuarioPrestador, Prestador> codigoPrestador;
	public static volatile SingularAttribute<UsuarioPrestador, Usuario> codigoUsuario;

}


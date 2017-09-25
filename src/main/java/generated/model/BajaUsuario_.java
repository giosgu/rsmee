package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.ArchivoCabecera;
import model.ArchivoDetalle;
import model.BajaUsuario;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BajaUsuario.class)
public abstract class BajaUsuario_ {

	public static volatile SingularAttribute<BajaUsuario, Long> codigo;
	public static volatile SingularAttribute<BajaUsuario, Date> fecha;
	public static volatile SingularAttribute<BajaUsuario, ArchivoCabecera> archivoCabecera;
	public static volatile SingularAttribute<BajaUsuario, ArchivoDetalle> archivoDetalle;
	public static volatile SingularAttribute<BajaUsuario, Usuario> usuario;

}


package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.ArchivoCabecera;
import model.ArchivoDetalle;
import model.ErrorArchivo;
import model.EstadoProcesamiento;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ArchivoDetalle.class)
public abstract class ArchivoDetalle_ {

	public static volatile SingularAttribute<ArchivoDetalle, Long> codigo;
	public static volatile SingularAttribute<ArchivoDetalle, EstadoProcesamiento> estado;
	public static volatile SingularAttribute<ArchivoDetalle, ArchivoCabecera> archivoCabecera;
	public static volatile SingularAttribute<ArchivoDetalle, Date> fechaProceso;
	public static volatile SingularAttribute<ArchivoDetalle, Usuario> usuario;
	public static volatile SingularAttribute<ArchivoDetalle, String> detalle;
	public static volatile ListAttribute<ArchivoDetalle, ErrorArchivo> errores;

}


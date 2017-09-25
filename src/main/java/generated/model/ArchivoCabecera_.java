package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.ArchivoCabecera;
import model.ErrorArchivo;
import model.EstadoProcesamiento;
import model.TipoArchivo;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ArchivoCabecera.class)
public abstract class ArchivoCabecera_ {

	public static volatile SingularAttribute<ArchivoCabecera, String> nombre;
	public static volatile SingularAttribute<ArchivoCabecera, Long> codigo;
	public static volatile SingularAttribute<ArchivoCabecera, EstadoProcesamiento> estado;
	public static volatile SingularAttribute<ArchivoCabecera, ErrorArchivo> error;
	public static volatile SingularAttribute<ArchivoCabecera, Usuario> usuario;
	public static volatile SingularAttribute<ArchivoCabecera, Date> fechaProceso;
	public static volatile SingularAttribute<ArchivoCabecera, Integer> registrosRechazados;
	public static volatile SingularAttribute<ArchivoCabecera, TipoArchivo> tipoArchivo;
	public static volatile SingularAttribute<ArchivoCabecera, Integer> registrosAceptados;
	public static volatile SingularAttribute<ArchivoCabecera, Integer> cantidadRegistros;

}


package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Afiliado;
import model.ArchivoDetalle;
import model.TipoDocumentoConversion;
import model.TipoPaciente;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Afiliado.class)
public abstract class Afiliado_ {

	public static volatile SingularAttribute<Afiliado, Long> codigo;
	public static volatile SingularAttribute<Afiliado, ArchivoDetalle> archivoDetalle;
	public static volatile SingularAttribute<Afiliado, TipoPaciente> tipoPaciente;
	public static volatile SingularAttribute<Afiliado, String> numeroDocumento;
	public static volatile SingularAttribute<Afiliado, TipoDocumentoConversion> tipoDocumento;
	public static volatile SingularAttribute<Afiliado, String> numeroAfiliado;

}


package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.FullAuditoryDataEntity;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FullAuditoryDataEntity.class)
public abstract class FullAuditoryDataEntity_ {

	public static volatile SingularAttribute<FullAuditoryDataEntity, Usuario> usuarioModificacion;
	public static volatile SingularAttribute<FullAuditoryDataEntity, Date> fechaCreacion;
	public static volatile SingularAttribute<FullAuditoryDataEntity, Date> fechaModificacion;
	public static volatile SingularAttribute<FullAuditoryDataEntity, Usuario> usuarioCreacion;

}


package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.EstadoMenoresACargo;
import model.MenoresACargo;
import model.Usuario;
import model.VinculoFamiliar;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MenoresACargo.class)
public abstract class MenoresACargo_ {

	public static volatile SingularAttribute<MenoresACargo, Long> codigo;
	public static volatile SingularAttribute<MenoresACargo, Usuario> menor;
	public static volatile SingularAttribute<MenoresACargo, Usuario> adultoResponsable;
	public static volatile SingularAttribute<MenoresACargo, EstadoMenoresACargo> estado;
	public static volatile SingularAttribute<MenoresACargo, VinculoFamiliar> vinculoFamiliar;

}


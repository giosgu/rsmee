package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Paciente;
import model.PacientePatologia;
import model.Patologia;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PacientePatologia.class)
public abstract class PacientePatologia_ {

	public static volatile SingularAttribute<PacientePatologia, Long> codigo;
	public static volatile SingularAttribute<PacientePatologia, Paciente> codigoPaciente;
	public static volatile SingularAttribute<PacientePatologia, String> value;
	public static volatile SingularAttribute<PacientePatologia, Patologia> patologia;
	public static volatile SingularAttribute<PacientePatologia, String> label;

}


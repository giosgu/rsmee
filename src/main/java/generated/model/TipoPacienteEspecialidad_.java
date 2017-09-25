package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Especialidad;
import model.TipoPaciente;
import model.TipoPacienteEspecialidad;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoPacienteEspecialidad.class)
public abstract class TipoPacienteEspecialidad_ {

	public static volatile SingularAttribute<TipoPacienteEspecialidad, Long> codigo;
	public static volatile SingularAttribute<TipoPacienteEspecialidad, TipoPaciente> tipoPaciente;
	public static volatile SingularAttribute<TipoPacienteEspecialidad, Especialidad> especialidad;

}


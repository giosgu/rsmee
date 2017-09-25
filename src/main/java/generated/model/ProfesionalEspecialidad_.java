package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Especialidad;
import model.Profesional;
import model.ProfesionalEspecialidad;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfesionalEspecialidad.class)
public abstract class ProfesionalEspecialidad_ {

	public static volatile SingularAttribute<ProfesionalEspecialidad, Profesional> codigo_profesional;
	public static volatile SingularAttribute<ProfesionalEspecialidad, Long> codigo;
	public static volatile SingularAttribute<ProfesionalEspecialidad, Long> anioEspecialidad;
	public static volatile SingularAttribute<ProfesionalEspecialidad, String> institucion;
	public static volatile SingularAttribute<ProfesionalEspecialidad, Especialidad> codigoEspecialidad;

}


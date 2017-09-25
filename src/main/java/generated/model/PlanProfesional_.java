package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Plan;
import model.PlanProfesional;
import model.Profesional;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PlanProfesional.class)
public abstract class PlanProfesional_ {

	public static volatile SingularAttribute<PlanProfesional, Long> codigo;
	public static volatile SingularAttribute<PlanProfesional, Plan> plan;
	public static volatile SingularAttribute<PlanProfesional, Profesional> profesional;

}


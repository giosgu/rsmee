package generated.model;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Medicamento;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medicamento.class)
public abstract class Medicamento_ {

	public static volatile SingularAttribute<Medicamento, String> nombre;
	public static volatile SingularAttribute<Medicamento, Long> codigo;
	public static volatile SingularAttribute<Medicamento, Date> fechaDesde;
	public static volatile SingularAttribute<Medicamento, String> laboratorio;
	public static volatile SingularAttribute<Medicamento, Date> fechaHasta;
	public static volatile SingularAttribute<Medicamento, String> presentacion;
	public static volatile SingularAttribute<Medicamento, String> dosis;

}


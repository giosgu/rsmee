package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Medicamento;
import model.Paciente;
import model.PacienteMedicamento;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PacienteMedicamento.class)
public abstract class PacienteMedicamento_ {

	public static volatile SingularAttribute<PacienteMedicamento, Long> codigo;
	public static volatile SingularAttribute<PacienteMedicamento, Paciente> codigoPaciente;
	public static volatile SingularAttribute<PacienteMedicamento, Medicamento> codigoMedicamento;

}


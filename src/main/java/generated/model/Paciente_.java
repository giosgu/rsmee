package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Paciente;
import model.Patologia;
import model.TipoPaciente;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile SingularAttribute<Paciente, String> peso;
	public static volatile SingularAttribute<Paciente, Long> codigo;
	public static volatile ListAttribute<Paciente, Patologia> patologias;
	public static volatile SingularAttribute<Paciente, TipoPaciente> tipoPaciente;
	public static volatile SingularAttribute<Paciente, Integer> codHistoriaClinica;
	public static volatile SingularAttribute<Paciente, String> altura;
	public static volatile SingularAttribute<Paciente, Usuario> codigoUsuario;

}


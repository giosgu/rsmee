package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Especialidad;
import model.Profesional;
import model.Provincia;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profesional.class)
public abstract class Profesional_ {

	public static volatile SingularAttribute<Profesional, String> pathFirmaPerfil;
	public static volatile SingularAttribute<Profesional, String> sloganPresentacion;
	public static volatile SingularAttribute<Profesional, String> tweeter;
	public static volatile SingularAttribute<Profesional, String> matriculaNacional;
	public static volatile SingularAttribute<Profesional, String> diasAtencion;
	public static volatile ListAttribute<Profesional, Especialidad> especialidades;
	public static volatile SingularAttribute<Profesional, Long> anioTitulo;
	public static volatile SingularAttribute<Profesional, Long> codigo;
	public static volatile SingularAttribute<Profesional, String> titulo;
	public static volatile SingularAttribute<Profesional, String> institucion;
	public static volatile SingularAttribute<Profesional, String> pathCV;
	public static volatile SingularAttribute<Profesional, String> matriculaProvincial;
	public static volatile SingularAttribute<Profesional, String> horarioAtencion;
	public static volatile SingularAttribute<Profesional, String> lugarAtencion;
	public static volatile SingularAttribute<Profesional, String> facebook;
	public static volatile SingularAttribute<Profesional, Provincia> codigoProvinciaMatriculal;

}


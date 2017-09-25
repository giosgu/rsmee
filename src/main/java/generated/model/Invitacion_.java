package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.EstadoInvitacion;
import model.Invitacion;
import model.PerfilCampania;
import model.Prestador;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Invitacion.class)
public abstract class Invitacion_ {

	public static volatile SingularAttribute<Invitacion, String> apellido;
	public static volatile SingularAttribute<Invitacion, String> nombre;
	public static volatile SingularAttribute<Invitacion, Long> codigo;
	public static volatile SingularAttribute<Invitacion, EstadoInvitacion> estado;
	public static volatile SingularAttribute<Invitacion, Date> fecha;
	public static volatile SingularAttribute<Invitacion, Prestador> prestador;
	public static volatile SingularAttribute<Invitacion, String> email;
	public static volatile SingularAttribute<Invitacion, PerfilCampania> perfil;
	public static volatile SingularAttribute<Invitacion, String> codigoActivacion;

}


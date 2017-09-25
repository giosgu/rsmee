package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.PreguntaRespuestaUsuario;
import model.PreguntaSeguridad;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PreguntaRespuestaUsuario.class)
public abstract class PreguntaRespuestaUsuario_ extends CreateAuditoryDataEntity_{

	public static volatile SingularAttribute<PreguntaRespuestaUsuario, Long> codigo;
	public static volatile SingularAttribute<PreguntaRespuestaUsuario, PreguntaSeguridad> pregunta;
	public static volatile SingularAttribute<PreguntaRespuestaUsuario, Usuario> usuario;
	public static volatile SingularAttribute<PreguntaRespuestaUsuario, String> respuesta;

}


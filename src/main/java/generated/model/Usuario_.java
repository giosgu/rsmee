package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Contacto;
import model.EstadoUsuario;
import model.Farmacia;
import model.Genero;
import model.Localidad;
import model.OlvidoClave;
import model.Paciente;
import model.Pais;
import model.PerfilCampania;
import model.Prestador;
import model.Profesional;
import model.Provincia;
import model.Rol;
import model.TipoDocumento;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ extends FullAuditoryDataEntity_ {

	public static volatile SingularAttribute<Usuario, String> codigoPostal;
	public static volatile SingularAttribute<Usuario, String> apellido;
	public static volatile SingularAttribute<Usuario, Paciente> paciente;
	public static volatile SingularAttribute<Usuario, Pais> pais;
	public static volatile ListAttribute<Usuario, Contacto> contactos;
	public static volatile SingularAttribute<Usuario, Boolean> administrador;
	public static volatile SingularAttribute<Usuario, Date> fechaNacimiento;
	public static volatile SingularAttribute<Usuario, EstadoUsuario> estadoUsuario;
	public static volatile SingularAttribute<Usuario, String> nombre;
	public static volatile SingularAttribute<Usuario, Long> codigo;
	public static volatile ListAttribute<Usuario, OlvidoClave> preguntas;
	public static volatile SingularAttribute<Usuario, String> idUsuario;
	public static volatile SingularAttribute<Usuario, Farmacia> farmacia;
	public static volatile SingularAttribute<Usuario, Provincia> codigoProvincia;
	public static volatile SingularAttribute<Usuario, String> value;
	public static volatile SingularAttribute<Usuario, PerfilCampania> perfil;
	public static volatile SingularAttribute<Usuario, TipoDocumento> tipoDocumento;
	public static volatile SingularAttribute<Usuario, String> contrasenia;
	public static volatile SingularAttribute<Usuario, String> telefono;
	public static volatile SingularAttribute<Usuario, String> prefijo;
	public static volatile SingularAttribute<Usuario, String> label;
	public static volatile SingularAttribute<Usuario, Date> fechaUltimoLogin;
	public static volatile SingularAttribute<Usuario, Profesional> profesional;
	public static volatile SingularAttribute<Usuario, Boolean> aceptoTerminosCondiciones;
	public static volatile SingularAttribute<Usuario, Boolean> activo;
	public static volatile SingularAttribute<Usuario, String> pathImagenPerfil;
	public static volatile SingularAttribute<Usuario, String> correoAlternativo;
	public static volatile SingularAttribute<Usuario, Genero> genero;
	public static volatile SingularAttribute<Usuario, Prestador> prestador;
	public static volatile SetAttribute<Usuario, Rol> roles;
	public static volatile SingularAttribute<Usuario, Localidad> codigoLocalidad;
	public static volatile SingularAttribute<Usuario, String> numDocumento;
	public static volatile SingularAttribute<Usuario, String> numeroAfiliado;

}


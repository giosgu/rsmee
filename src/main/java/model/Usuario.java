/*
 *******************************************************************************
 **  Archivo    : Usuario.java
 **  Paquete    : com.octomind.rsm.model
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versi�n)
 **  Autor      : mazzca
 **  Fecha      : 18/01/2012 12:57:37
 *******************************************************************************
 **  Modificaciones/Correcciones
 **               Fecha       Responsable     Comentario
 **
 *******************************************************************************
 */
package model;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import listener.EntityAuditorListener;

import org.apache.commons.lang3.StringUtils;

/*
import org.jboss.seam.annotations.security.management.UserFirstNameName;
import org.jboss.seam.annotations.security.management.UserLastName;
import org.jboss.seam.annotations.security.management.UserPassword;
import org.jboss.seam.annotations.security.management.UserPrincipal;
import org.jboss.seam.annotations.security.management.UserRoles;
import org.jboss.seam.security.management.PasswordHash;
*/
/**
 * @author mazzca
 *
 */
@Entity
@Named
@EntityListeners(EntityAuditorListener.class)
@Table(name = "usuario")
public class Usuario extends FullAuditoryDataEntity implements ComboEntity {	
	private static final long serialVersionUID = 1L;
	
	public static final Long COD_ADMINISTRADOR_AUTRAL_SALUD = new Long(1);
	
	private Long codigo;
	private String idUsuario;
	private String contrasenia;
	private String nombre;
	private String apellido;
	private boolean activo;
	private boolean administrador;
	private Date fechaUltimoLogin;
	private Date fechaNacimiento;
	private String codigoPostal;
	private Pais pais;
	private Provincia codigoProvincia;
	private Localidad codigoLocalidad;
	private String telefono;
	private String correoAlternativo;
	private Genero genero;
	private List<OlvidoClave> preguntas;
	private PerfilCampania perfil;
	private Paciente paciente;
	private Profesional profesional;
	private Farmacia farmacia;
	private TipoDocumento tipoDocumento;
	private String numDocumento;
	private Prestador prestador;
	private EstadoUsuario estadoUsuario;
	private String numeroAfiliado;
	private String pathImagenPerfil;
	private Set<Rol> roles;
	private String prefijo;
	private boolean aceptoTerminosCondiciones;

	private List<Contacto> contactos;

	
	/*	private Date fechaBaja;
	private boolean logueado;
	private List<Permiso> permisos;
	private List<UsuarioRol> roles;*/

	public Usuario() {
		super();

	}
	
	public Usuario(Long codigo){
		super();
		this.codigo = codigo;
	}

    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="UsuarioSeqOra")
    @SequenceGenerator(name="UsuarioSeqOra",sequenceName="USUARIO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "id_usuario", nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
//	@UserPrincipal
	public String getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "contrasenia", length = 100)
//	@UserPassword(hash=PasswordHash.ALGORITHM_SHA)
	public String getContrasenia() {
		return this.contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Column(name = "nombre", length = 30)
	@Size(max = 30)
//	@UserFirstName
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre.trim();
	}

	@Column(name = "apellido", length = 30)
	@Size(max = 30)
//	@UserLastName
	public String getApellido() {
		return this.apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido.trim();
	}
	
	@Column(name = "activo", nullable = false)
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	@Column(name = "administrador", nullable = false)
	public boolean isAdministrador() {
		return this.administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	@Column(name="fecha_ultimo_login")
	@Temporal(TemporalType.DATE) 
	public Date getFechaUltimoLogin() {
		return fechaUltimoLogin;
	}
	public void setFechaUltimoLogin(Date fechaUltimoLogin) {
		this.fechaUltimoLogin = fechaUltimoLogin;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_perfil", referencedColumnName="codigo")
	public PerfilCampania getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilCampania perfil) {
		this.perfil = perfil;
	}

	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE) 
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Column(name = "codigo_postal", length = 10)
	@Size(max = 10)
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_pais", referencedColumnName="codigo")
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_provincia", referencedColumnName="codigo")
	public Provincia getCodigoProvincia() {
		return codigoProvincia;
	}
	public void setCodigoProvincia(Provincia codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_localidad", referencedColumnName="codigo")
	public Localidad getCodigoLocalidad() {
		return codigoLocalidad;
	}
	public void setCodigoLocalidad(Localidad codigoLocalidad) {
		this.codigoLocalidad = codigoLocalidad;
	}

	@Column(name = "telefono", length = 30)
	@Size(max = 30)
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "correo_alternativo", length = 50, nullable = true)
	@Size(max = 50)
	public String getCorreoAlternativo() {
		return correoAlternativo;
	}
	public void setCorreoAlternativo(String correoAlternativo) {
		this.correoAlternativo = correoAlternativo;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_genero", referencedColumnName="codigo")
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public List<OlvidoClave> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<OlvidoClave> preguntas) {
		this.preguntas = preguntas;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_paciente", referencedColumnName="codigo")
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_profesional", referencedColumnName="codigo")
	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}


	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipo_documento", referencedColumnName="codigo")
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	@Column(name = "numero_documento")
	@Size(max = 15)
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_prestador", referencedColumnName="codigo")
	public Prestador getPrestador() {
		return prestador;
	}
	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}
	
	@Column(name = "numero_afiliado")
	@Size(max = 16)
	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}
	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_estado_usuario", referencedColumnName="codigo")
	public EstadoUsuario getEstadoUsuario() {
		return estadoUsuario;
	}
	public void setEstadoUsuario(EstadoUsuario estado) {
		this.estadoUsuario = estado;
	}

	@Column(name = "path_imagen_perfil")
	@Size(max = 256)
	public String getPathImagenPerfil() {
		return pathImagenPerfil;
	}
	public void setPathImagenPerfil(String pathImagenPerfil) {
		this.pathImagenPerfil = pathImagenPerfil;
	}	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="origen", targetEntity=Contacto.class, fetch=FetchType.LAZY)
	public List<Contacto> getContactos() {
		return contactos;
	}
	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	@Column(name = "prefijo", length=10 )
	public String getPrefijo() {
		return prefijo;
	}
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}
	
//	 @UserRoles
	  @ManyToMany(targetEntity = Rol.class)
	  @JoinTable(name = "Usuario_Rol", 
	    joinColumns = @JoinColumn(name = "codigo_usuario"),
	    inverseJoinColumns = @JoinColumn(name = "codigo_rol"))
	  public Set<Rol> getRoles() {
		 return roles; 
	}

	  public void setRoles(Set<Rol> roles) { 
		  this.roles = roles; 
	  }

	
	public String getLabel() {
		String label="";
		if(StringUtils.isNotBlank( getPrefijo() ))
			label= getPrefijo()+" ";
		return label.concat( getApellido()+" "+getNombre() );
	}
	
	public String getValue() {
		return getCodigo().toString();
	}
	
	public void setLabel(String label) {}	
	public void setValue(String label) {}
	
	@Column(name = "acepto_terminos_condiciones", nullable = false)
	public boolean isAceptoTerminosCondiciones() {
		return aceptoTerminosCondiciones;
	}
	public void setAceptoTerminosCondiciones(boolean aceptoTerminosCondiciones) {
		this.aceptoTerminosCondiciones = aceptoTerminosCondiciones;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_farmacia", referencedColumnName="codigo")
	public Farmacia getFarmacia() {
		return farmacia;
	}
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
}

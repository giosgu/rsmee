package model;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * @author montieln
 *
 */

@Entity
@Table(name = "invitacion")
@Named
public class Invitacion implements Serializable{
	
	private Long codigo;
	private String email;
	private String nombre;
	private String apellido;
	private Prestador prestador;
	private EstadoInvitacion estado;
	private Date fecha;
	private PerfilCampania perfil;
	private String codigoActivacion;
	
	
	public Invitacion(){
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="InvitacionSeqOra")
    @SequenceGenerator(name="InvitacionSeqOra",sequenceName="INVITACION_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "email", nullable = false, length = 50)
	@Size(max = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "nombre",length = 50)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "apellido",length = 50)
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_prestador", referencedColumnName="codigo", nullable=false)
	public Prestador getPrestador() {
		return prestador;
	}
	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_estado", referencedColumnName="codigo", nullable=false)
	public EstadoInvitacion getEstado() {
		return estado;
	}
	public void setEstado(EstadoInvitacion estado) {
		this.estado = estado;
	}
	
	@Column(name = "fecha", nullable = false, length = 10)
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_perfil", referencedColumnName="codigo", nullable=false)
	public PerfilCampania getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilCampania perfil) {
		this.perfil = perfil;
	}
	
	@Column(name = "codigo_activacion",	length = 128)
	public String getCodigoActivacion() {
		return codigoActivacion;
	}
	public void setCodigoActivacion(String codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}
	
	
    
}

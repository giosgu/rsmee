package model;

import java.io.Serializable;

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


@Entity
@Table(name="sucursal")
public class Sucursal implements ComboEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SucursalSeqOra")
    @SequenceGenerator(name="SucursalSeqOra",sequenceName="SUCURSAL_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;

	@Column(name="atencion_horario")
	private String atencionHorario;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_farmacia", referencedColumnName="codigo")
	private Farmacia farmacia;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_localidad", referencedColumnName="codigo")
	private Localidad localidad;
	
	@Column(name="altitud")
	private String altitud;
	
	@Column(name="longitud")
	private String longitud;
	
	@Column(name="path_imagen")
	@Size(max=256)
	private String pathImagen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado_sucursal", referencedColumnName="codigo")
	private EstadoSucursal estadoSucursal;
		
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getAtencionHorario() {
		return atencionHorario;
	}
	public void setAtencionHorario(String atencionHorario) {
		this.atencionHorario = atencionHorario;
	}
	
	public Farmacia getFarmacia() {
		return farmacia;
	}
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public String getAltitud() {
		return altitud;
	}
	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}
	
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	public String getPathImagen() {
		return pathImagen;
	}
	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}
	
	public EstadoSucursal getEstadoSucursal() {
		return estadoSucursal;
	}
	public void setEstadoSucursal(EstadoSucursal estadoSucursal) {
		this.estadoSucursal = estadoSucursal;
	}
	
	//Combo
	@Override
	public void setLabel(String label) {}
	@Override
	public String getLabel() {
		return farmacia.getDescripcion()+" ("+getDireccion()+")";
	}
	@Override
	public void setValue(String label) {}
	@Override
	public String getValue() {
		return getCodigo().toString();
	}

}
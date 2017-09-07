package model;

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
@Table(name = "prestador")
public class Prestador extends CreateAuditoryDataEntity{

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	private String cuit;
	private String domicilio;
	private Localidad localidad;
	private Pais pais;
	private Provincia provincia;
	private String urlWeb;
	private int cantidadMaximaEspecialidades;
	private String denominacion;
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PrestadorSeqOra")
    @SequenceGenerator(name="PrestadorSeqOra",sequenceName="PRESTADOR_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public String getCodigo() {
		return codigo;
	}    
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion", nullable = false, length = 30)
	@Size(max = 30)
	public String getDescripcion() {
		return descripcion;
	}	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "cuit", length = 14)
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}	
	
	@Column(name = "domicilio")
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_localidad", referencedColumnName="codigo")
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_pais", referencedColumnName="codigo")
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_provincia", referencedColumnName="codigo")
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	@Column(name = "url_web", nullable = true, length = 128)
	@Size(max = 128)
	public String getUrlWeb() {
		return urlWeb;
	}	
	public void setUrlWeb(String urlWeb) {
		this.urlWeb = urlWeb;
	}
	
	@Column(name = "cantidad_maxima_especialidades", nullable = false)
	public int getCantidadMaximaEspecialidades() {
		return cantidadMaximaEspecialidades;
	}
	public void setCantidadMaximaEspecialidades(int cantidadMaximaEspecialidades) {
		this.cantidadMaximaEspecialidades = cantidadMaximaEspecialidades;
	}
	
	@Column(name="denominacion")
	@Size(max=2)
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Named
@Table(name = "archivo_cabecera")
public class ArchivoCabecera implements Serializable {
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ArchivoCabeceraSeq")
    @SequenceGenerator(name="ArchivoCabeceraSeq",sequenceName="ARCHIVOCABECERA_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_tipo_archivo", referencedColumnName="codigo", nullable=false)
	private TipoArchivo tipoArchivo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_estado", referencedColumnName="codigo", nullable=false)
	private EstadoProcesamiento estado;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(name = "cantidad_registros" , nullable=false)
	private Integer cantidadRegistros;
	
	@Column(name = "registros_aceptados")
	private Integer registrosAceptados;
	
	@Column(name = "registros_rechazados")
	private Integer registrosRechazados; 

	@Column(name="fecha_proceso", nullable=false)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaProceso;
	
	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name="codigo_usuario", referencedColumnName="codigo", nullable =false)
	private Usuario usuario;

	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name="codigo_error", referencedColumnName="codigo", nullable =true)
	private ErrorArchivo error;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public TipoArchivo getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(TipoArchivo tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public EstadoProcesamiento getEstado() {
		return estado;
	}

	public void setEstado(EstadoProcesamiento estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	public Integer getRegistrosAceptados() {
		return registrosAceptados;
	}

	public void setRegistrosAceptados(Integer registrosAceptados) {
		this.registrosAceptados = registrosAceptados;
	}

	public Integer getRegistrosRechazados() {
		return registrosRechazados;
	}

	public void setRegistrosRechazados(Integer registrosRechazados) {
		this.registrosRechazados = registrosRechazados;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ErrorArchivo getError() {
		return error;
	}

	public void setError(ErrorArchivo error) {
		this.error = error;
	}
	
	
	
}

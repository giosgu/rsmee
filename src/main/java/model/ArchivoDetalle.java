package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@Named
@Table(name = "archivo_detalle")
public class ArchivoDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227001844953837501L;

	public ArchivoDetalle(){
		super();
		estado = EstadoProcesamiento.PENDIENTE;
	}
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ArchivoDetalleSeq")
    @SequenceGenerator(name="ArchivoDetalleSeq",sequenceName="ARCHIVODETALLE_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_archivo_cabecera", referencedColumnName="codigo", nullable=false)
    private ArchivoCabecera archivoCabecera;
	
	@Column(name = "detalle", nullable=false, columnDefinition="TEXT")
	private String detalle; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_estado", referencedColumnName="codigo", nullable=false)
	private EstadoProcesamiento estado;
	
	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name="codigo_usuario", referencedColumnName="codigo", nullable =false)
	private Usuario usuario;
	
	@Column(name="fecha_proceso", nullable=false)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaProceso;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "error_detalle", 
				joinColumns = { @JoinColumn(name = "codigo_archivo_detalle") }, 
				inverseJoinColumns = { @JoinColumn(name = "codigo_error") }
	)
	private List<ErrorArchivo> errores;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public ArchivoCabecera getArchivoCabecera() {
		return archivoCabecera;
	}

	public void setArchivoCabecera(ArchivoCabecera archivoCabecera) {
		this.archivoCabecera = archivoCabecera;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public EstadoProcesamiento getEstado() {
		return estado;
	}

	public void setEstado(EstadoProcesamiento estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public List<ErrorArchivo> getErrores() {
		return errores;
	}

	public void setErrores(List<ErrorArchivo> errores) {
		this.errores = errores;
	}

}

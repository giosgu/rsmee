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
@Table(name = "baja_usuario")
public class BajaUsuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="BajaUsuarioSeqOra")
    @SequenceGenerator(name="BajaUsuarioSeqOra",sequenceName="BAJAUSUARIO_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario", referencedColumnName="codigo", nullable=false)
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_archivo_detalle", referencedColumnName="codigo", nullable=false)
	private ArchivoDetalle archivoDetalle;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_archivo_cabecera", referencedColumnName="codigo", nullable=false)
	private ArchivoCabecera archivoCabecera;
	
	@Column(name="fecha", nullable=false)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fecha;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArchivoDetalle getArchivoDetalle() {
		return archivoDetalle;
	}

	public void setArchivoDetalle(ArchivoDetalle archivoDetalle) {
		this.archivoDetalle = archivoDetalle;
	}

	public ArchivoCabecera getArchivoCabecera() {
		return archivoCabecera;
	}

	public void setArchivoCabecera(ArchivoCabecera archivoCabecera) {
		this.archivoCabecera = archivoCabecera;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}

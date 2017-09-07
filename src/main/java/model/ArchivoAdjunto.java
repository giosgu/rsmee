package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Size;

@Entity
@Table(name="archivo_adjunto")
public class ArchivoAdjunto implements Serializable{

	private Long codigo;
	private String pathArchivo;
	private Date fechaCreacion;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ArchivoAdjuntoSeqOra")
    @SequenceGenerator(name="ArchivoAdjuntoSeqOra",sequenceName="ARCHIVO_ADJUNTO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "path_archivo")
	@Size(max = 256)
	public String getPathArchivo() {
		return pathArchivo;
	}
	public void setPathArchivo(String pathArchivo) {
		this.pathArchivo = pathArchivo;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}

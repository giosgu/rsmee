package model;

import java.io.Serializable;

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


@Entity
@Table(name = "error_detalle")
@Named
public class ErrorDetalle implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ErrorDetalleSeq")
	@SequenceGenerator(name="ErrorDetalleSeq",sequenceName="ERROR_DETALLE_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_archivo_detalle", referencedColumnName="codigo")
	private ArchivoDetalle archivoDetalle;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_error", referencedColumnName="codigo")
	private ErrorArchivo errorArchivo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public ArchivoDetalle getArchivoDetalle() {
		return archivoDetalle;
	}

	public void setArchivoDetalle(ArchivoDetalle archivoDetalle) {
		this.archivoDetalle = archivoDetalle;
	}

	public ErrorArchivo getErrorArchivo() {
		return errorArchivo;
	}

	public void setErrorArchivo(ErrorArchivo errorArchivo) {
		this.errorArchivo = errorArchivo;
	}
	
}

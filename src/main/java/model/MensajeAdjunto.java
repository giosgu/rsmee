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

import javax.validation.constraints.NotNull;


@Entity
@Table(name = "estado_adjunto")
public class MensajeAdjunto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MensajeAdjuntoSeqOra")
    @SequenceGenerator(name="MensajeAdjuntoSeqOra",sequenceName="MENSAJE_ADJUNTO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_Mensaje", referencedColumnName="codigo")
	@NotNull
	private Mensaje mensaje;
	
	@Column(name = "path_archivo_adjunto", length=256)
	private String pathArchivoAdjunto;

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public String getPathArchivoAdjunto() {
		return pathArchivoAdjunto;
	}
	public void setPathArchivoAdjunto(String pathArchivoAdjunto) {
		this.pathArchivoAdjunto = pathArchivoAdjunto;
	}
	
	

}

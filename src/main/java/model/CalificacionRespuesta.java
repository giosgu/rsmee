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
@Named
@Table(name = "calificacion_respuesta")
public class CalificacionRespuesta implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="CalificacionRtaSeqOra")
    @SequenceGenerator(name="CalificacionRtaSeqOra",sequenceName="CALIFICACION_RTA_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_calificacion", referencedColumnName="codigo")
	private Calificacion calificacion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_respuesta", referencedColumnName="codigo")
	private MensajeRespuesta mensajeRespuesta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario", referencedColumnName="codigo")
	private Usuario usuario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	public MensajeRespuesta getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(MensajeRespuesta mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}

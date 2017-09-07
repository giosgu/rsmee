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

import javax.validation.constraints.NotNull;


@Entity
@Table(name = "mensaje_respuesta_destino")
public class MensajeRespuestaDestino implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MensajeRtaDestinoSeqOra")
    @SequenceGenerator(name="MensajeRtaDestinoSeqOra",sequenceName="MENSAJE_RTA_DESTINO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_mensaje_respuesta", referencedColumnName="codigo", nullable=false)
	@NotNull
	private MensajeRespuesta mensajeRespuesta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario_destino", referencedColumnName="codigo", nullable=false)
	@NotNull
	private Usuario usuarioDestino;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_estado_mensaje", referencedColumnName="codigo", nullable=false)
	@NotNull
	private EstadoMensaje estadoMensaje;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario_origen", referencedColumnName="codigo", nullable=false)
	@NotNull
	private Usuario usuarioOrigen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="calificacion_respuesta", referencedColumnName="codigo", nullable=true)
	private Calificacion calificacionRespuesta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_notificacion", referencedColumnName="codigo")
	private Notificacion notificacion;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public MensajeRespuesta getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	public void setMensajeRespuesta(MensajeRespuesta mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}
	public void setUsuarioDestino(Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}
	public EstadoMensaje getEstadoMensaje() {
		return estadoMensaje;
	}
	public void setEstadoMensaje(EstadoMensaje estadoMensaje) {
		this.estadoMensaje = estadoMensaje;
	}
	public Usuario getUsuarioOrigen() {
		return usuarioOrigen;
	}
	public void setUsuarioOrigen(Usuario usuarioOrigen) {
		this.usuarioOrigen = usuarioOrigen;
	}
	public Calificacion getCalificacionRespuesta() {
		return calificacionRespuesta;
	}
	public void setCalificacionRespuesta(Calificacion calificacionRespuesta) {
		this.calificacionRespuesta = calificacionRespuesta;
	}
	public Notificacion getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}
	
}

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
@Table(name = "mensaje_destino")
public class MensajeDestino implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8718815708399871245L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MensajeDestinoSeqOra")
    @SequenceGenerator(name="MensajeDestinoSeqOra",sequenceName="MENSAJEDESTINO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_Mensaje", referencedColumnName="codigo")
	@NotNull
	private Mensaje mensaje;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_estado", referencedColumnName="codigo")
	@NotNull
	private EstadoMensaje estadoMensaje;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario_origen", referencedColumnName="codigo")
	@NotNull
	private Usuario usuarioOrigen;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario_destino", referencedColumnName="codigo")
	@NotNull
	private Usuario usuarioDestino;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_notificacion", referencedColumnName="codigo")
	private Notificacion notificacion;
	
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

	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}

	public void setUsuarioDestino(Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}
	
}

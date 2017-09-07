package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_respuesta")
public class MensajeRespuesta implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MensajeRtaSeqOra")
    @SequenceGenerator(name="MensajeRtaSeqOra",sequenceName="MENSAJE_RTA_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_mensaje", referencedColumnName="codigo")
	private Mensaje mensaje;
	
	@Column(name = "texto", nullable=false)
	@Lob
	private String texto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario_origen", referencedColumnName="codigo")
	private Usuario usuarioOrigen;

	private Date fecha;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="mensajeRespuesta", targetEntity=MensajeRespuestaDestino.class)
	private List<MensajeRespuestaDestino> mensajeRespuestaDestinos;
	
	@OneToMany(mappedBy="mensajeRespuesta", targetEntity=CalificacionRespuesta.class, fetch=FetchType.LAZY )
	private Set<CalificacionRespuesta> calificacionRespuesta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_archivo_adjunto", referencedColumnName="codigo")
	private ArchivoAdjunto archivoAdjunto;

	
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuarioOrigen() {
		return usuarioOrigen;
	}

	public void setUsuarioOrigen(Usuario usuarioOrigen) {
		this.usuarioOrigen = usuarioOrigen;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<MensajeRespuestaDestino> getMensajeRespuestaDestinos() {
		return mensajeRespuestaDestinos;
	}

	public void setMensajeRespuestaDestinos(
			List<MensajeRespuestaDestino> mensajeRespuestaDestinos) {
		this.mensajeRespuestaDestinos = mensajeRespuestaDestinos;
	}

	public Set<CalificacionRespuesta> getCalificacionRespuesta() {
		return calificacionRespuesta;
	}

	public void setCalificacionRespuesta(
			Set<CalificacionRespuesta> calificacionRespuesta) {
		this.calificacionRespuesta = calificacionRespuesta;
	}

	public ArchivoAdjunto getArchivoAdjunto() {
		return archivoAdjunto;
	}

	public void setArchivoAdjunto(ArchivoAdjunto archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}

}

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Named
@Table(name = "prescripcion_medicamento")
public class PrescripcionMedicamento implements Serializable {

	private static final long serialVersionUID = -8712282474563642364L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PrescripcionSeqOra")
    @SequenceGenerator(name="PrescripcionSeqOra",sequenceName="PRESCRIPCION_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_medicamento", referencedColumnName="codigo", nullable=false)
	private Medicamento medicamento;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_estado", referencedColumnName="codigo", nullable=false)
	private EstadoPrescripcion estadoPrescripcion;
	
	@Column(name = "texto", nullable=true)
	@Lob
	private String mensaje;
	
	@Column(name = "texto_respuesta", nullable=true)
	@Lob
	private String respuestaMensaje;
	
	@Column(name = "texto_respuesta_farmacia", nullable=true)
	@Lob
	private String respuestaFarmaciaMensaje;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_origen", referencedColumnName="codigo", nullable=false)
	private Usuario usuarioOrigen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_destino", referencedColumnName="codigo", nullable=false)
	private Usuario usuarioDestino;
	
	@Column(name="fecha_solicitud")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaSolicitud;

	@Column(name="fecha_prescripcion")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaPrescripcion;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_notificacion_solicitud", referencedColumnName="codigo", nullable=true)
	private Notificacion notificacionSolicitud;

	@Column(name = "cantidad", nullable=false, length=5)
	private String cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_archivo_adjunto", referencedColumnName="codigo")
	private ArchivoAdjunto archivoAdjunto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_sucursal", referencedColumnName="codigo")
	private Sucursal sucursal;
	
	@Column(name="telefono_paciente")
	private String telefonoPaciente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_receta", referencedColumnName="codigo", nullable=true)
	private TipoReceta tipoReceta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_diagnostico", referencedColumnName="codigo")
	private Diagnostico diagnostico;
	
	@Column(name="tt_prolongado", nullable=true)
	private Boolean ttProlongado;
	
	@Column(name="fecha_aceptada_farmacia")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaAceptadaFarmacia;
	
	@Column(name="fecha_entregado")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaEntregado;
	
	@Column(name="fecha_rechazada_farmacia")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaRechazadaFarmacia;
	
	@Column(name="fecha_rechazada_medico")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaRechazadaMedico;
	
	@Column(name="fecha_anulada")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaAnulada;
	
	@Column(name="comentario_anulada", nullable=true)
	@Lob
	private String comentarioAnulada;
		
	public Date getFechaAnulada() {
		return fechaAnulada;
	}
	public void setFechaAnulada(Date fechaAnulada) {
		this.fechaAnulada = fechaAnulada;
	}
	public String getComentarioAnulada() {
		return comentarioAnulada;
	}
	public void setComentarioAnulada(String comentarioAnulada) {
		this.comentarioAnulada = comentarioAnulada;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public EstadoPrescripcion getEstadoPrescripcion() {
		return estadoPrescripcion;
	}
	public void setEstadoPrescripcion(
			EstadoPrescripcion estadoPrescripcion) {
		this.estadoPrescripcion = estadoPrescripcion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getRespuestaMensaje() {
		return respuestaMensaje;
	}
	public void setRespuestaMensaje(String respuestaMensaje) {
		this.respuestaMensaje = respuestaMensaje;
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
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public Date getFechaPrescripcion() {
		return fechaPrescripcion;
	}
	public void setFechaPrescripcion(Date fechaPrescripcion) {
		this.fechaPrescripcion = fechaPrescripcion;
	}
	public Notificacion getNotificacionSolicitud() {
		return notificacionSolicitud;
	}
	public void setNotificacionSolicitud(Notificacion notificacionSolicitud) {
		this.notificacionSolicitud = notificacionSolicitud;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public ArchivoAdjunto getArchivoAdjunto() {
		return archivoAdjunto;
	}
	public void setArchivoAdjunto(ArchivoAdjunto archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public String getTelefonoPaciente() {
		return telefonoPaciente;
	}
	public void setTelefonoPaciente(String telefonoPaciente) {
		this.telefonoPaciente = telefonoPaciente;
	}
	public String getRespuestaFarmaciaMensaje() {
		return respuestaFarmaciaMensaje;
	}	
	public void setRespuestaFarmaciaMensaje(String respuestaFarmaciaMensaje) {
		this.respuestaFarmaciaMensaje = respuestaFarmaciaMensaje;
	}
	public TipoReceta getTipoReceta() {
		return tipoReceta;
	}
	public void setTipoReceta(TipoReceta tipoReceta) {
		this.tipoReceta = tipoReceta;
	}
	public Diagnostico getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}
	public Boolean getTtProlongado() {
		return ttProlongado;
	}
	public void setTtProlongado(Boolean ttProlongado) {
		this.ttProlongado = ttProlongado;
	}	
	public Date getFechaAceptadaFarmacia() {
		return fechaAceptadaFarmacia;
	}	
	public void setFechaAceptadaFarmacia(Date fechaAceptadaFarmacia) {
		this.fechaAceptadaFarmacia = fechaAceptadaFarmacia;
	}
	public Date getFechaEntregado() {
		return fechaEntregado;
	}
	public void setFechaEntregado(Date fechaEntregado) {
		this.fechaEntregado = fechaEntregado;
	}
	public Date getFechaRechazadaFarmacia() {
		return fechaRechazadaFarmacia;
	}
	public void setFechaRechazadaFarmacia(Date fechaRechazadaFarmacia) {
		this.fechaRechazadaFarmacia = fechaRechazadaFarmacia;
	}
	public Date getFechaRechazadaMedico() {
		return fechaRechazadaMedico;
	}
	public void setFechaRechazadaMedico(Date fechaRechazadaMedico) {
		this.fechaRechazadaMedico = fechaRechazadaMedico;
	}
}

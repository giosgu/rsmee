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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Mensaje implements Serializable{

	public Mensaje(){
		super();
	}
	public Mensaje(Long codigo){
		super();
		this.codigo = codigo;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MensajeSeqOra")
    @SequenceGenerator(name="MensajeSeqOra",sequenceName="MENSAJE_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="origen", referencedColumnName="codigo")
	private Usuario origen;
	
	@Column(name = "texto", nullable=false)
	@Lob
	private String texto;
	
	@Column(name="comentario_administrador", nullable=true)
	@Lob
	private String comenatrioAdministrador;

	private Date fecha;

	@OneToMany(mappedBy="mensaje", targetEntity=MensajeRespuesta.class, fetch=FetchType.LAZY )
	private Set<MensajeRespuesta> mensajeRespuestas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="mensaje", targetEntity=MensajeDestino.class, fetch=FetchType.LAZY)
	private List<MensajeDestino> mensajeDestinos;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipo_mensaje", referencedColumnName="codigo")
	private TipoMensaje tipoMensaje;
	
	@Column(name="finalizado", nullable=true)
	private Boolean finalizado;
	
	@Column(name="no_Es_Consulta_Medica", nullable=true)
	private Boolean noEsConsultaMedica;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_archivo_adjunto", referencedColumnName="codigo")
	private ArchivoAdjunto archivoAdjunto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_auditoria_consulta", referencedColumnName="codigo", nullable=true)
	private AuditoriaConsulta auditoriaConsulta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_ultimo_cierre", nullable=true)
	private Date fechaUltimoCierre;

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Usuario getOrigen() {
		return origen;
	}
	public void setOrigen(Usuario origen) {
		this.origen = origen;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Set<MensajeRespuesta> getMensajeRespuestas() {
		return mensajeRespuestas;
	}
	public void setMensajeRespuestas(Set<MensajeRespuesta> mensajeRespuestas) {
		this.mensajeRespuestas = mensajeRespuestas;
	}
	public List<MensajeDestino> getMensajeDestinos() {
		return mensajeDestinos;
	}
	public void setMensajeDestinos(List<MensajeDestino> mensajeDestinos) {
		this.mensajeDestinos = mensajeDestinos;
	}
	public boolean equals(Object obj) {
		return (obj instanceof Mensaje) && this.codigo != null && this.codigo.equals(((Mensaje)obj).getCodigo());
	}
	public void setTipoMensaje(TipoMensaje tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	public TipoMensaje getTipoMensaje() {
		return tipoMensaje;
	}
	public String getComenatrioAdministrador() {
		return comenatrioAdministrador;
	}
	public void setComenatrioAdministrador(String comenatrioAdministrador) {
		this.comenatrioAdministrador = comenatrioAdministrador;
	}
	public Boolean getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	public ArchivoAdjunto getArchivoAdjunto() {
		return archivoAdjunto;
	}
	public void setArchivoAdjunto(ArchivoAdjunto archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}
	public Boolean getNoEsConsultaMedica() {
		return noEsConsultaMedica;
	}
	public void setNoEsConsultaMedica(Boolean noEsConsultaMedica) {
		this.noEsConsultaMedica = noEsConsultaMedica;
	}
	public AuditoriaConsulta getAuditoriaConsulta() {
		return auditoriaConsulta;
	}
	public void setAuditoriaConsulta(AuditoriaConsulta auditoriaConsulta) {
		this.auditoriaConsulta = auditoriaConsulta;
	}
	public Date getFechaUltimoCierre() {
		return fechaUltimoCierre;
	}
	public void setFechaUltimoCierre(Date fechaUltimoCierre) {
		this.fechaUltimoCierre = fechaUltimoCierre;
	}
}

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
import javax.validation.constraints.Size;


@Entity
@Named
@Table(name = "notificacion")
public class Notificacion implements Serializable {

    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="NotificacionSeqOra")
    @SequenceGenerator(name="NotificacionSeqOra",sequenceName="NOTIFICACION_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_notificacion", referencedColumnName="codigo")
	private TipoNotificacion tipoNotificacion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado", referencedColumnName="codigo")
	private EstadoNotificacion estadoNotificacion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_origen", referencedColumnName="codigo")
	private Usuario origen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_destino", referencedColumnName="codigo")
	private Usuario destino;
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE) 
	private Date fecha;
	
	@Column(name = "parametro")
	@Size(max = 120)
	private String parametro;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public TipoNotificacion getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public EstadoNotificacion getEstadoNotificacion() {
		return estadoNotificacion;
	}

	public void setEstadoNotificacion(EstadoNotificacion estadoNotificacion) {
		this.estadoNotificacion = estadoNotificacion;
	}

	public Usuario getOrigen() {
		return origen;
	}

	public void setOrigen(Usuario origen) {
		this.origen = origen;
	}

	public Usuario getDestino() {
		return destino;
	}

	public void setDestino(Usuario destino) {
		this.destino = destino;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
}

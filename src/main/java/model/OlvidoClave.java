/*
 *******************************************************************************
 **  Archivo    : OlvidoClave.java
 **  Paquete    : com.octomind.rsm.model
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versión)
 **  Autor      : alessiof
 **  Fecha      : 13/03/2012 15:31:26
 *******************************************************************************
 **  Modificaciones/Correcciones
 **               Fecha       Responsable     Comentario
 **
 *******************************************************************************
 */
package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

import listener.EntityAuditorListener;

/**
 * @author alessiof
 *
 */
@Entity
@EntityListeners(EntityAuditorListener.class)
@Table(name="olvido_clave")
public class OlvidoClave extends CreateAuditoryDataEntity{

	private Long codigo;
	private Usuario usuario;
	private String hash;
	private Date fechaGeneracion;
	private boolean activado;
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="olvido_claveSeqOra")
    @SequenceGenerator(name="olvido_claveSeqOra",sequenceName="olvido_clave_SEQ", allocationSize=1)
    @Column(name = "codigo", nullable = false)
	public Long getCodigo() {
		return codigo;
	}    
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario", nullable=true, referencedColumnName="codigo")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Column(name="fecha_generacion", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	
	@Column(name = "hash",	length = 128, nullable = false)
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	@Column(name = "activado", nullable = false)
	public void setActivado(boolean activado) {
		this.activado = activado;
	}
	public boolean isActivado() {
		return activado;
	}

}

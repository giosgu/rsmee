/*
 *******************************************************************************
 **  Archivo    : FullAuditoryDataEntity.java
 **  Paquete    : com.octomind.rsm.entity
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versi�n)
 **  Autor      : mazzca
 **  Fecha      : 18/01/2012 12:59:58
 *******************************************************************************
 **  Modificaciones/Correcciones
 **               Fecha       Responsable     Comentario
 **
 *******************************************************************************
 */
package model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author mazzca
 *
 */
@MappedSuperclass
public class FullAuditoryDataEntity  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioCreacion;
	private Date fechaCreacion;
	private Usuario usuarioModificacion;
	private Date fechaModificacion;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario_creacion", nullable=true, referencedColumnName="CODIGO")
	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}
	
	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	@Column(name="fecha_creacion", nullable=true)
	@Temporal(TemporalType.TIMESTAMP) 
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario_modificacion", nullable=true, referencedColumnName="CODIGO")
	public Usuario getUsuarioModificacion() {
		return usuarioModificacion;
	}
	
	public void setUsuarioModificacion(Usuario usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	
	@Column(name="fecha_modificacion", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}
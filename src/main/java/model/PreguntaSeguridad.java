/*
 *******************************************************************************
 **  Archivo    : PreguntaSeguridad.java
 **  Paquete    : com.octomind.rsm.model
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versión)
 **  Autor      : mazzca
 **  Fecha      : 25/01/2012 17:37:01
 *******************************************************************************
 **  Modificaciones/Correcciones
 **               Fecha       Responsable     Comentario
 **
 *******************************************************************************
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import listener.EntityAuditorListener;

/**
 * @author mazzca
 *
 */
@Entity
@EntityListeners(EntityAuditorListener.class)
@Table(name = "pregunta_seguridad")
public class PreguntaSeguridad extends CreateAuditoryDataEntity{
	
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String detalle;

    @Id
    @Column(name = "codigo", unique = true, nullable = false)
	public Integer getCodigo() {
		return codigo;
	}
    
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "detalle", length = 150)
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
}

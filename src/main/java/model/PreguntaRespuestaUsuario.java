/*
 *******************************************************************************
 **  Archivo    : PreguntaUsuario.java
 **  Paquete    : com.octomind.rsm.model
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versión)
 **  Autor      : mazzca
 **  Fecha      : 25/01/2012 17:41:26
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import listener.EntityAuditorListener;

/**
 * @author mazzca
 *
 */
@Entity
@EntityListeners(EntityAuditorListener.class)
@Table(name = "pregunta_respuesta_usuario")
public class PreguntaRespuestaUsuario extends CreateAuditoryDataEntity{

	private Long codigo;
	private Usuario usuario;
	private PreguntaSeguridad pregunta;
	private String respuesta;
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pregunta_respuesta_usuarioSeqOra")
    @SequenceGenerator(name="pregunta_respuesta_usuarioSeqOra",sequenceName="pregunta_respuesta_usuario_SEQ", allocationSize=1)
    @Column(name = "codigo")
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_pregunta", nullable=true, referencedColumnName="codigo")
	public PreguntaSeguridad getPregunta() {
		return pregunta;
	}
	
	public void setPregunta(PreguntaSeguridad pregunta) {
		this.pregunta = pregunta;
	}
	
	@Column(name = "respuesta", length = 50)
	@Size(max = 50)	
	public String getRespuesta() {
		return respuesta;
	}
	
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}

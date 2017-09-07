package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;

@Entity
@Named
@Table(name = "estado_notificacion")
public class EstadoNotificacion implements Serializable {

	public static final String NO_LEIDO = "NLE";
	public static final String LEIDO = "LEI";
	public static final String PENDIENTE = "PEN";
	public static final EstadoNotificacion ESTADO_NO_LEIDO = new EstadoNotificacion(NO_LEIDO);
	public static final EstadoNotificacion ESTADO_LEIDO = new EstadoNotificacion(LEIDO);
	public static final EstadoNotificacion ESTADO_PENDIENTE = new EstadoNotificacion(PENDIENTE);

	public EstadoNotificacion(){
		super();
	}
	
	public EstadoNotificacion(String codigo){
		this.codigo = codigo;
	}
	
	@Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	private String codigo;
	
	@Column(name = "descripcion", length = 30)
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}

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
@Table(name = "estado_mensaje")
public class EstadoMensaje implements Serializable
{
	public static final String NO_LEIDO = "NLE";	
	public static final String LEIDO = "LEI";
	public static final String PENDIENTE = "PEN";
	public static final String RECHAZADO = "RCH";
	public static final String ORIGEN_RECHAZADO = "ORC";
	
	public static final EstadoMensaje ESTADO_NO_LEIDO = new EstadoMensaje(NO_LEIDO);
	public static final EstadoMensaje ESTADO_LEIDO = new EstadoMensaje(LEIDO);
	public static final EstadoMensaje ESTADO_EPENDIENTE = new EstadoMensaje(PENDIENTE);
	public static final EstadoMensaje ESTADO_RECHAZADO = new EstadoMensaje(RECHAZADO);
	
	public EstadoMensaje(){
		super();
	}
	
	public EstadoMensaje(String codigo){
		super();
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
	
	public String getRechazado(){
		return RECHAZADO;
	}
	public String getLeido(){
		return LEIDO;
	}
	public String getNoLeido(){
		return NO_LEIDO;
	}

}

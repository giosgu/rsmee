package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "estado_usuario")
@Named
public class EstadoUsuario implements Serializable{


	private static final long serialVersionUID = 1L;
	
	public static final EstadoUsuario ESTADO_PENDIENTE = new EstadoUsuario("PEN");
	public static final EstadoUsuario ESTADO_ACTIVO = new EstadoUsuario("ACT");
	public static final EstadoUsuario ESTADO_RECHAZADO = new EstadoUsuario("RCH");
	public static final EstadoUsuario ESTADO_BAJA = new EstadoUsuario("BAJ");
	public static final EstadoUsuario ESTADO_PRE_ACEPTADO = new EstadoUsuario("PRE");
	public static final EstadoUsuario ESTADO_LICENCIA = new EstadoUsuario("LIC");
	public static final EstadoUsuario ESTADO_EMANCIPANDOSE = new EstadoUsuario("EMA");

	private String codigo;
	private String descripcion;
	
	public EstadoUsuario(String codigoEstado){
		this.codigo = codigoEstado;
	}
	
	public EstadoUsuario(){
		super();
	}	
	
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	public String getCodigo() {
		return codigo;
	}    
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion", length = 30)
	@Size(max = 30)			
	public String getDescripcion() {
		return descripcion;
	}	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static EstadoUsuario getESTADO_ACTIVO() {
		return ESTADO_ACTIVO;
	}
	public static EstadoUsuario getESTADO_LICENCIA() {
		return ESTADO_LICENCIA;
	}
}

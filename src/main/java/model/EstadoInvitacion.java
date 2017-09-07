package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;



@Entity
@Table(name = "estado_invitacion")
public class EstadoInvitacion implements Serializable{
	
	public static final EstadoInvitacion INVITADO = new EstadoInvitacion("INV");
	public static final EstadoInvitacion PENDIETNE = new EstadoInvitacion("PEN");
	public static final EstadoInvitacion ACTIVA = new EstadoInvitacion("ACT");
	
	private String codigo;
	private String descripcion;
	
	public EstadoInvitacion(){
		super();
	}
	
	public EstadoInvitacion(String codigo){
		super();
		this.codigo = codigo;
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
	
	
	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;


@Entity
@Table(name="estado_menores_a_cargo")
public class EstadoMenoresACargo implements Serializable{
	
	public static final EstadoMenoresACargo ESTADO_ACTIVO = new EstadoMenoresACargo("ACT");
	public static final EstadoMenoresACargo ESTADO_BAJA = new EstadoMenoresACargo("BAJ");
		
	public EstadoMenoresACargo(String codigoEstado){
		super();
		this.codigo = codigoEstado;
	}	
	public EstadoMenoresACargo(){
		super();
	}
		
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	
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
	
}

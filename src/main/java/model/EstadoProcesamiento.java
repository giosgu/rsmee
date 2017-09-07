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
@Table(name = "estado_procesamiento")
public class EstadoProcesamiento implements Serializable, ComboEntity {

	private static final long serialVersionUID = 1L;
	public static final EstadoProcesamiento PENDIENTE = new EstadoProcesamiento("P");
	public static final EstadoProcesamiento ACEPTADO = new EstadoProcesamiento("A");
	public static final EstadoProcesamiento RECHAZADO = new EstadoProcesamiento("R");
	
	public EstadoProcesamiento(String codigo){
		super();
		this.codigo = codigo;
	}

	public EstadoProcesamiento(){
		super();
	}
	
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	private String codigo;
    
	@Column(name = "descripcion", nullable = false)
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
	
	//Combo
	public void setValue(String label) {}
	public String getLabel() {
		return getDescripcion();
	}
	public void setLabel(String label) {}
	public String getValue() {
		return getCodigo();
	}

}

package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;


@Entity
@Table(name="vinculo_familiar")
public class VinculoFamiliar implements Serializable, ComboEntity{

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
	
	@Column(name = "descripcion", length = 60)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//Combo	
	@Override
	public String getLabel() {		
		return getDescripcion();
	}
	@Override
	public String getValue() {
		return getCodigo();
	}
	@Override
	public void setLabel(String label) {}
	@Override
	public void setValue(String label) {}
	
	
    
}
